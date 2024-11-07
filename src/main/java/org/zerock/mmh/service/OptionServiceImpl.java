package org.zerock.mmh.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.OptionDTO;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.dto.PageResultDTO;
import org.zerock.mmh.entity.Option;
import org.zerock.mmh.repository.OptionRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {


    private final OptionRepository repository;

    @Override
    public String register(OptionDTO dto) {

        Option entity = dtoToEntity(dto);
        repository.save(entity);
        return entity.getOptionNo();
    }

    @Override
    public PageResultDTO<OptionDTO, Option> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("optionNo").descending());
        Page<Option> result = repository.findAll(pageable);

        Function<Option, OptionDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    @Override
    public void remove(String optionNo) {
        //옵션 삭제시 해당 옵션을 가진 업체별 관리 상품과의 연결을 지워야함
        repository.deleteById(optionNo);
    }

    @Override
    public void modify(OptionDTO dto) {
        //업데이트 하는 항목은 '옵션명', '내용'이다.
        Optional<Option> result = repository.findById(dto.getOptionNo());
        if (result.isPresent()) {
            Option entity = result.get();
            entity.changeName(dto.getOption_name());
            entity.changeDesc(dto.getOption_desc());
            repository.save(entity);
        }
    }
}
