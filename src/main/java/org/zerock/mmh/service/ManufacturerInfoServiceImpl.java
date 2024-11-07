package org.zerock.mmh.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.ManufacturerInfoDTO;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.dto.PageResultDTO;
import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerService;

import org.zerock.mmh.entity.mService;
import org.zerock.mmh.repository.ManufacturerInfoRepository;
import org.zerock.mmh.repository.ManufacturerProductRepository;
import org.zerock.mmh.repository.ManufacturerServiceRepository;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ManufacturerInfoServiceImpl implements ManufacturerInfoService {
    private final ManufacturerInfoRepository repository;
    private final ManufacturerServiceRepository serviceRepository;
    private final ManufacturerProductRepository manufacturerProductRepository;

//    @Override
//    public String register(ManufacturerInfoDTO dto) {
//
//        ManufacturerInfo entity = dtoToEntity(dto);
//        repository.save(entity);
//        return entity.getManuInfoNo();
//    }

    @Transactional
    @Override
    public String register(ManufacturerInfoDTO manufacturerInfoDTO) {

        Map<String, Object> entityMap = dtoToEntity(manufacturerInfoDTO);
        ManufacturerInfo manufacturerInfo = (ManufacturerInfo) entityMap.get("manufacturerInfo");
        List<mService> serviceList = (List<mService>) entityMap.get("serviceList");
        repository.save(manufacturerInfo);
        if (serviceList != null && serviceList.size() > 0) {
            serviceList.forEach(service -> {
                //여기서 주의해야할 것은... service가 아니라 ManufacturerService에 저장해줘야한다.
                ManufacturerService manufacturerService = ManufacturerService.builder()
                        .manufacturerInfo(manufacturerInfo)
                        .service(service)
                        .build();
                serviceRepository.save(manufacturerService);
            });
        }
        return manufacturerInfo.getManuInfoNo();
    }

//서비스 결합전
//@Override
//public PageResultDTO<ManufacturerInfoDTO, ManufacturerInfo> getList(PageRequestDTO requestDTO) {
//    Pageable pageable = requestDTO.getPageable(Sort.by("manuInfoNo").descending());
//    Page<ManufacturerInfo> result = repository.findAll(pageable);
//
//    Function<ManufacturerInfo, ManufacturerInfoDTO> fn = (entity -> entityToDto(entity));
//    return new PageResultDTO<>(result, fn);
//}

    @Override
    public PageResultDTO<ManufacturerInfoDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("manuInfoNo").descending());
        String keyword = getSearch(requestDTO);

        Page<Object[]> result = null;

        if (keyword == null) {
            result = repository.getListPage(pageable);
        } else {
            result = repository.getListPage(keyword, pageable);
        }

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });

        //여기에 쿼리의 결과를 넣는 것이기 때문에 쿼리문과 잘 비교해야한다.
        Function<Object[], ManufacturerInfoDTO> fn = (arr -> entitiesToDTO(
                (ManufacturerInfo) arr[0],
                //문제는 여기에 지금 값이 하나 들어간다는 거다... 값이 여러개 들어가야하는데.
                //entitiesToDTO에서 해결했으니 쿼리문에서는 페이지 리스트만 가져옴에 주의
                (List<mService>) (Arrays.asList((mService) arr[1]))
        ));

        return new PageResultDTO<>(result, fn);
    }

    //getManuInfo로 변환
    @Override
    public ManufacturerInfoDTO read(String ManuInfoNo) {
        Optional<ManufacturerInfo> result = repository.findById(ManuInfoNo);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

@Override
    public ManufacturerInfoDTO getManuInfo(String manuInfoNo) {
        List<Object[]> result = repository.getManuInfoWithAll(manuInfoNo);
        ManufacturerInfo manufacturerInfo = (ManufacturerInfo) result.get(0)[0];
        List<mService> serviceList = new ArrayList<>();
        result.forEach(arr ->{
            mService service = (mService) arr[1];
            serviceList.add(service);
        });
        return entitiesToDTO(manufacturerInfo, serviceList);
    }



    @Transactional
    @Override
    public void remove(String ManuInfoNo) {
        //업체 정보 삭제시 업체 서비스를 먼저 삭제
        serviceRepository.deleteByManuInfoNo(ManuInfoNo);
        //업체 정보 삭제시 업체별 상품을 먼저 삭제
        manufacturerProductRepository.deleteByManuInfoNo(ManuInfoNo);
        //업체 정보 삭제
        repository.deleteById(ManuInfoNo);
    }


    @Override
    public void modify(ManufacturerInfoDTO dto) {
        //업데이트 하는 항목은 '업체 이름', '사이트 주소'
        Optional<ManufacturerInfo> result = repository.findById(dto.getManuInfoNo());
        if (result.isPresent()) {
            ManufacturerInfo entity = result.get();

            entity.changeName(dto.getManu_info_name());
            entity.changeSite(dto.getManu_info_site());

            repository.save(entity);
        }

    }

    @Override
    public void modifyManufacturerMember(ManufacturerInfoDTO dto) {
        //업데이트 하는 항목은 '업체 담당 업체회원
        Optional<ManufacturerInfo> result = repository.findById(dto.getManuInfoNo());
        if (result.isPresent()) {
            ManufacturerInfo entity = result.get();
            entity.changeMemNo(dto.getManuMemNo());
            repository.save(entity);
        }
    }


    private String getSearch(PageRequestDTO requestDTO) {
        String keyword = requestDTO.getKeyword();
        log.info("keyword: " + keyword);
        return keyword;
    }

//서비스에 선언한게 안되서.... 결국 이렇게 했다.
    public ManufacturerInfoDTO entitiesToDTO(ManufacturerInfo entity, List<mService> services) {
        ManufacturerInfoDTO manufacturerInfoDTO = entityToDto(entity);
        List<ManufacturerService> result = serviceRepository.findByManufacturerInfo(entity);
        try {
            if (result != null && result.size() > 0) {
                List<ServiceDTO> serviceDTOList = result.stream().map(manufacturerService -> {
                    return ServiceDTO.builder()
                            .serviceNo(manufacturerService.getService().getServiceNo())
                            .service_name(manufacturerService.getService().getService_name())
                            .service_desc(manufacturerService.getService().getService_desc())
                            .build();
                }).collect(Collectors.toList());
                manufacturerInfoDTO.setServiceDTOList(serviceDTOList);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return manufacturerInfoDTO;
    }
}
