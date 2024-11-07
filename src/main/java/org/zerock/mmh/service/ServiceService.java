package org.zerock.mmh.service;

import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.dto.PageResultDTO;
import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.entity.mService;

import java.util.List;

public interface ServiceService {
    String register(ServiceDTO dto);

    PageResultDTO<ServiceDTO, mService> getList(PageRequestDTO requestDTO);

    void remove(String serviceNo);

    void modify(ServiceDTO dto);

    List<mService> getServiceList();

    //해당 업체의 서비스DTO 리스트 리턴
    List<ServiceDTO> getListWithManuInfoNo(String manuInfoNo);
    List<List<ServiceDTO>> getListWithManuInfoNos(String[] manuInfoNos);


    default mService dtoToEntity(ServiceDTO dto) {
        mService entity = mService.builder()
                .serviceNo(dto.getServiceNo())
                .service_name(dto.getService_name())
                .service_desc(dto.getService_desc())
                .build();
        return entity;
    }

    default ServiceDTO entityToDto(mService entity) {
        ServiceDTO dto = ServiceDTO.builder()
                .serviceNo(entity.getServiceNo())
                .service_name(entity.getService_name())
                .service_desc(entity.getService_desc())
                .build();
        return dto;
    }


}
