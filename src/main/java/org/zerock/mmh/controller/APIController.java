package org.zerock.mmh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mmh.dto.OptionDTO;
import org.zerock.mmh.dto.ServiceDTO;
import org.zerock.mmh.entity.ManufacturerProduct;
import org.zerock.mmh.repository.ManufacturerServiceRepository;
import org.zerock.mmh.service.ManufacturerProductService;
import org.zerock.mmh.service.ManufacturerServiceService;
import org.zerock.mmh.service.OptionService;
import org.zerock.mmh.service.ServiceService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class APIController {
    private final OptionService optionService;
    private final ServiceService serviceService;
    private final ManufacturerServiceService manufacturerServiceService;
    private final ManufacturerProductService manufacturerProductService;


    //* 옵션 *//
    //입력
    @PostMapping("/post/option")
    public ResponseEntity<String> postOption(@RequestBody OptionDTO dto) {
        return ResponseEntity.ok(optionService.register(dto));
    }

    //수정
    @PutMapping({"/post/option/{optionNo}"})
    public ResponseEntity<String> modifyOption(@RequestBody OptionDTO dto, @PathVariable String optionNo) {
        optionService.modify(dto);
        return ResponseEntity.ok(optionNo);
    }

    //삭제
    @DeleteMapping({"/post/option/{optionNo}"})
    public ResponseEntity<String> deleteOption(@PathVariable String optionNo) {
        optionService.remove(optionNo);
        return ResponseEntity.ok(optionNo);
    }


    //* 서비스 *//
    //입력
    @PostMapping("/post/service")
    public ResponseEntity<String> postService(@RequestBody ServiceDTO dto) {
        return ResponseEntity.ok(serviceService.register(dto));
    }

    //수정
    @PutMapping({"/post/service/{serviceNo}"})
    public ResponseEntity<String> modifyService(@RequestBody ServiceDTO dto, @PathVariable String serviceNo) {
        serviceService.modify(dto);
        return ResponseEntity.ok(serviceNo);
    }

    //삭제
    @DeleteMapping({"/post/service/{serviceNo}"})
    public ResponseEntity<String> deleteService(@PathVariable String serviceNo) {
        serviceService.remove(serviceNo);
        return ResponseEntity.ok(serviceNo);
    }

    //* 업체에 서비스 추가하기 *//
    @PostMapping("/post/manuService/{manuInfoNo}/{serviceNo}")
    public ResponseEntity<String> postManuService(@PathVariable String manuInfoNo, @PathVariable String serviceNo) {
        manufacturerServiceService.register(manuInfoNo, serviceNo);
        return ResponseEntity.ok(manuInfoNo);
    }

    //* 업체에 서비스 삭제하기*//
    @DeleteMapping("/post/manuService/{manuInfoNo}/{serviceNo}")
    public ResponseEntity<String> deleteManuService(@PathVariable String manuInfoNo, @PathVariable String serviceNo) {
        manufacturerServiceService.remove(manuInfoNo, serviceNo);
        return ResponseEntity.ok(manuInfoNo);
    }

    //* 업체에 상품 추가하기 *//
    @PostMapping("/post/manuProduct/{manuInfoNo}/{productNo}")
    public ResponseEntity<String> postManuProduct(@PathVariable String manuInfoNo, @PathVariable String productNo) {
        manufacturerProductService.register(manuInfoNo, productNo);
        return ResponseEntity.ok(manuInfoNo);
    }

    //* 업체에 상품 삭제하기*//
    @DeleteMapping("/post/manuProduct/{manuInfoNo}/{productNo}")
    public ResponseEntity<String> deleteManuProduct(@PathVariable String manuInfoNo, @PathVariable String productNo) {
        manufacturerProductService.remove(manuInfoNo, productNo);
        return ResponseEntity.ok(manuInfoNo);
    }



}
