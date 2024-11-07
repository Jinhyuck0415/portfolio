package org.zerock.mmh.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mmh.dto.*;
import org.zerock.mmh.entity.*;
import org.zerock.mmh.repository.ManufacturerProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ManufacturerProductServiceImpl implements ManufacturerProductService {


    private final ManufacturerProductRepository repository;

//    @Transactional
//    @Override
//    public String register(ManufacturerProductDTO dto){
//        Map<String, Object> entityMap = dtoToEntity(dto);
//        ManufacturerProduct manufacturerProduct = (ManufacturerProduct) entityMap.get("manufacturerProduct");
//        List<Option> optionList = (List<Option>) entityMap.get("optionList");
//        manufacturerProductRepository.save(manufacturerProduct);
//        if (optionList != null && optionList.size() > 0) {
//            optionList.forEach(option -> {
//                //여기서 주의해야할 것은... Option이 아니라 ProductOption에 저장해줘야한다.
//                ProductOption productOption = ProductOption.builder()
//                        .manufacturerProduct(manufacturerProduct)
//                        .option(option)
//                        .build();
//                productOptionRepository.save(productOption);
//            });
//        }
//        return manufacturerProduct.getManuProductNo();
//    }


    @Override
    public String register(String manuInfoNo, String productNo) {
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder()
                .manuInfoNo(manuInfoNo)
                .build();
        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .manufacturerInfo(manufacturerInfo)
                .product(Product.builder().productNo(productNo).build())
                .build();
        repository.save(manufacturerProduct);
        return manufacturerProduct.getManuProductNo();
    }

    @Transactional
    @Override
    public void remove(String manuInfoNo, String productNo) {
        repository.deleteByManuInfoNoWithProductNo(manuInfoNo, productNo);
    }


    //일단 전체 리스트를 돌려준다.
    @Override
    public PageResultDTO<ManufacturerProductDTO, ManufacturerProduct> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("manuProductNo").descending());

        //검색조건 처리
        BooleanBuilder booleanBuilder = getWithManuInfoNo(requestDTO);

        //Querydsl 사용
        Page<ManufacturerProduct> result = repository.findAll(booleanBuilder,pageable);

        Function<ManufacturerProduct, ManufacturerProductDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }


    private BooleanBuilder getWithManuInfoNo(PageRequestDTO requestDTO) {
        String manuInfoNo = requestDTO.getManuInfoNo();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QManufacturerProduct qManufacturerProduct = QManufacturerProduct.manufacturerProduct;
        BooleanExpression expression = qManufacturerProduct.manufacturerInfo.manuInfoNo.eq(manuInfoNo);
        booleanBuilder.and(expression);
        return booleanBuilder;
    }

}
