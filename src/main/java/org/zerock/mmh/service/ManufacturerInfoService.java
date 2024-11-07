package org.zerock.mmh.service;

import org.zerock.mmh.dto.ManufacturerInfoDTO;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.dto.PageResultDTO;
import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.mService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ManufacturerInfoService {
    //등록
    String register(ManufacturerInfoDTO dto);

    //리스트
//    PageResultDTO<ManufacturerInfoDTO, ManufacturerInfo> getList(PageRequestDTO requestDTO);
    PageResultDTO<ManufacturerInfoDTO, Object[]> getList(PageRequestDTO requestDTO);


    //읽기
    ManufacturerInfoDTO read(String ManuInfoNo);
    ManufacturerInfoDTO getManuInfo(String manuInfoNo);

    //제거
    void remove(String ManuInfoNo);

    //수정
    void modify(ManufacturerInfoDTO dto);

    //관리업체 수정
    void modifyManufacturerMember(ManufacturerInfoDTO dto);

// 서비스가 추가 안되어있었을때
//    default ManufacturerInfo dtoToEntity(ManufacturerInfoDTO dto) {
//        ManufacturerInfo entity = ManufacturerInfo.builder()
//                .manuInfoNo(dto.getManuInfoNo())
//                .manu_info_name(dto.getManu_info_name())
//                .manu_info_site(dto.getManu_info_site())
//                .manuMemNo(dto.getManuMemNo())
//                .build();
//        return entity;
//    }


    default Map<String, Object> dtoToEntity(ManufacturerInfoDTO dto) {

        Map<String, Object> entityMap = new HashMap<>();

        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder()
                .manuInfoNo(dto.getManuInfoNo())
                .manu_info_name(dto.getManu_info_name())
                .manu_info_site(dto.getManu_info_site())
                .manuMemNo(dto.getManuMemNo())
                .build();

        entityMap.put("manufacturerInfo", manufacturerInfo);

        List<ServiceDTO> serviceDTOList = dto.getServiceDTOList();

        //ServiceDTO 처리
        if (serviceDTOList != null && serviceDTOList.size() > 0) {
            List<mService> serviceList = serviceDTOList.stream().map(serviceDTO -> {
                mService service = mService.builder()
                        .serviceNo(serviceDTO.getServiceNo())
                        .service_name(serviceDTO.getService_name())
                        .service_desc(serviceDTO.getService_desc())
                        .build();
                return service;
            }).collect(Collectors.toList());

            entityMap.put("serviceList", serviceList);
        }
        return entityMap;
    }

    default ManufacturerInfoDTO entityToDto(ManufacturerInfo entity) {
        ManufacturerInfoDTO dto = ManufacturerInfoDTO.builder()
                .manuInfoNo(entity.getManuInfoNo())
                .manu_info_name(entity.getManu_info_name())
                .manu_info_site(entity.getManu_info_site())
                .manuMemNo(entity.getManuMemNo())
                .build();
        return dto;
    }


}
