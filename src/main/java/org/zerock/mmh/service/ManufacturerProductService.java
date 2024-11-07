package org.zerock.mmh.service;

import org.zerock.mmh.dto.*;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerProduct;
import org.zerock.mmh.entity.Option;
import org.zerock.mmh.entity.mService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ManufacturerProductService {
    //등록
//    String register(ManufacturerProductDTO dto);
    String register(String manuInfoNo, String productNo);

    //삭제
    void remove(String manuInfoNo, String productNo) ;

    //페이징
    PageResultDTO<ManufacturerProductDTO, ManufacturerProduct> getList(PageRequestDTO requestDTO);

    default Map<String, Object> dtoToEntity(ManufacturerProductDTO dto) {

        Map<String, Object> entityMap = new HashMap<>();

        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .manuProductNo(dto.getManuProductNo())
                .manufacturerInfo(dto.getManufacturerInfo())
                .product(dto.getProduct())
                .build();

        entityMap.put("manufacturerProduct", manufacturerProduct);

        List<OptionDTO> optionDTOList = dto.getOptionDTOList();

        //OptionDTO 처리
        if (optionDTOList != null && optionDTOList.size() > 0) {
            List<Option> optionList = optionDTOList.stream().map(optionDTO -> {
                Option option = Option.builder()
                        .optionNo(optionDTO.getOptionNo())
                        .option_name(optionDTO.getOption_name())
                        .option_desc(optionDTO.getOption_desc())
                        .build();
                return option;
            }).collect(Collectors.toList());

            entityMap.put("optionList", optionList);
        }
        return entityMap;
    }

    default ManufacturerProductDTO entityToDto(ManufacturerProduct entity) {
        ManufacturerProductDTO dto = ManufacturerProductDTO.builder()
                .manuProductNo(entity.getManuProductNo())
                .manufacturerInfo(entity.getManufacturerInfo())
                .product(entity.getProduct())
                .build();
        return dto;
    }

}
