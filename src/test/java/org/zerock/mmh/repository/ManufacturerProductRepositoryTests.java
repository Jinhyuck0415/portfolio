package org.zerock.mmh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.mmh.controller.ManufacturerProductController;
import org.zerock.mmh.entity.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ManufacturerProductRepositoryTests {

    @Autowired
    private ManufacturerProductRepository manufacturerProductRepository;
    @Autowired
    private ManufacturerProductController manufacturerProductController;

    @Test
    public void testListPage(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("manuProductNo").descending());
        Page<Object[]> result = manufacturerProductRepository.getListPage(pageRequest);

        for(Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }
    }

//    @Test
//    public void testGetManufacturerProductWithNo() {
//        String manuInfoNo = "MI00023";
//
//        List<Product> result = manufacturerProductRepository.findByManufacturerInfoNo(manuInfoNo);
//
//        result.forEach(manufacturerProduct -> {
//           System.out.println(manufacturerProduct.getProductNo());
//            System.out.println(manufacturerProduct.getProduct_size());
//            System.out.println(manufacturerProduct.getProduct_name());
//            System.out.println(manufacturerProduct.getProduct_period());
//        });
//
//    }



    @Test
    public void testGetManufacturerProduct() {
        String manuInfoNo = "MI00023";
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder()
                .manuInfoNo(manuInfoNo)
                .build();

        List<ManufacturerProduct> result = manufacturerProductRepository.findByManufacturerInfo(manufacturerInfo);

        result.forEach(productOption -> {
            System.out.println(productOption.getManuProductNo());
            System.out.println(productOption.getManufacturerInfo().getManu_info_name());
            System.out.println(productOption.getProduct().getProduct_name());
            System.out.println(productOption.getProduct().getProduct_size());
            System.out.println(productOption.getManuProductPrice());
        });

    }

    @Test
    public void testGetManufacturerProduct2() {
        String productNo = "P00021";
        Product product = Product.builder().productNo(productNo).build();

        List<ManufacturerProduct> result = manufacturerProductRepository.findByProduct(product);

        result.forEach(productOption -> {
            System.out.println(productOption.getManuProductNo());
            System.out.println(productOption.getManufacturerInfo().getManu_info_name());
            System.out.println(productOption.getProduct().getProduct_name());
            System.out.println(productOption.getProduct().getProduct_size());
            System.out.println(productOption.getManuProductPrice());
        });

    }


    @Test
    public void insertManufacturerProduct() {
        String manuInfoNo = "MI00027";
        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder()
                .manuInfoNo(manuInfoNo)
                .build();
        String productNo = "P00048";
        Product product = Product.builder()
                .productNo(productNo)
                .build();

        ManufacturerProduct manufacturerProduct = ManufacturerProduct.builder()
                .manufacturerInfo(manufacturerInfo)
                .product(product)
                .manuProductPrice(70000)
                .build();

        manufacturerProductRepository.save(manufacturerProduct);
    }

}
