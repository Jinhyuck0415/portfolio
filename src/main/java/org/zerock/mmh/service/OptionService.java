package org.zerock.mmh.service;

import org.zerock.mmh.dto.OptionDTO;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.dto.PageResultDTO;
import org.zerock.mmh.entity.Option;

public interface OptionService {
    String register(OptionDTO dto);

    PageResultDTO<OptionDTO, Option> getList(PageRequestDTO requestDTO);

    void remove(String optionNo);

    void modify(OptionDTO dto);

    default Option dtoToEntity(OptionDTO dto) {
        Option entity = Option.builder()
                .optionNo(dto.getOptionNo())
                .option_name(dto.getOption_name())
                .option_desc(dto.getOption_desc())
                .build();
        return entity;
    }

    default OptionDTO entityToDto(Option entity) {
        OptionDTO dto = OptionDTO.builder()
                .optionNo(entity.getOptionNo())
                .option_name(entity.getOption_name())
                .option_desc(entity.getOption_desc())
                .build();
        return dto;
    }
}
