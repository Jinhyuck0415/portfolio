package org.zerock.mmh.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.dto.PageResultDTO;
import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.repository.ManufacturerServiceRepository;
import org.zerock.mmh.repository.ServiceRepository;
import org.zerock.mmh.entity.mService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository repository;
    private final ManufacturerServiceRepository manufacturerServiceRepository;

    @Override
    public String register(ServiceDTO dto) {
        mService entity = dtoToEntity(dto);
        repository.save(entity);
        return entity.getServiceNo();
    }

    @Override
    public PageResultDTO<ServiceDTO, mService> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("serviceNo").descending());
        Page<mService> result = repository.findAll(pageable);

        Function<mService, ServiceDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    @Override
    public void remove(String serviceNo) {
        //서비스 삭제시 연결되어 있는 업체 서비스를 먼저 삭제
        manufacturerServiceRepository.deleteByServiceNo(serviceNo);

        //서비스 삭제
        repository.deleteById(serviceNo);
    }

    @Override
    public void modify(ServiceDTO dto) {
        //업데이트 하는 항목은 '옵션명', '내용'이다.
        Optional<mService> result = repository.findById(dto.getServiceNo());
        if (result.isPresent()) {
            mService entity = result.get();
            entity.changeName(dto.getService_name());
            entity.changeDesc(dto.getService_desc());
            repository.save(entity);
        }
    }

    @Override
    public List<mService> getServiceList() {
        return repository.findAll();
    }



    @Override
    public List<ServiceDTO> getListWithManuInfoNo(String manuInfoNo){
        List<mService> result = manufacturerServiceRepository.findByManufacturerInfoNo(manuInfoNo);

        List<ServiceDTO> dtos = new ArrayList<>();
        result.forEach(service -> {
            ServiceDTO serviceDTO = entityToDto(service);
            dtos.add(serviceDTO);
        });


        return dtos;
    }


    @Override
    public List<List<ServiceDTO>> getListWithManuInfoNos(String[] manuInfoNos){
        List<List<ServiceDTO>> serviceDTOListList = new ArrayList<>();
        for(int i=0;i<manuInfoNos.length;i++){
            List<mService> result = manufacturerServiceRepository.findByManufacturerInfoNo(manuInfoNos[i]);

            List<ServiceDTO> dtos = new ArrayList<>();
            result.forEach(service -> {
                ServiceDTO serviceDTO = entityToDto(service);
                dtos.add(serviceDTO);
            });
            serviceDTOListList.add(dtos);
        }
        return serviceDTOListList;
    }


}
