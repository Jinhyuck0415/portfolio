package org.zerock.mmh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.mmh.dto.ManufacturerInfoDTO;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.entity.ManufacturerInfo;
import org.zerock.mmh.entity.ManufacturerProduct;
import org.zerock.mmh.repository.ManufacturerProductRepository;
import org.zerock.mmh.service.ManufacturerProductService;
import org.zerock.mmh.service.ProductService;

@Controller
@RequestMapping("/mproduct")
@Log4j2
@RequiredArgsConstructor
public class ManufacturerProductController {

    private final ManufacturerProductRepository manufacturerProductRepository;
    private final ManufacturerProductService manufacturerProductService;
    private final ProductService productService;

    @GetMapping("/")
    public String index() {
        return "redirect:/manuinfo/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...........");
        log.info(manufacturerProductService.getList(pageRequestDTO));
        model.addAttribute("result", manufacturerProductService.getList(pageRequestDTO));
    }

//        String manuInfoNo = "MI00031";
//        log.info("manuInfoNo: " + manuInfoNo);
//
//        ManufacturerInfo manufacturerInfo = ManufacturerInfo.builder().manuInfoNo(manuInfoNo).build();
//        log.info("list...........");
//        model.addAttribute("result", productService.getList(pageRequestDTO));
//        model.addAttribute("originalProductList", productService.getList(pageRequestDTO));
//
////        여기서 가져와야하는 DTO는 업체 정보 번호로 추려낸 해당 업체의 상품 리스트와 그 상품들의 옵션 리스트가 필요하다.
////        상품 추가는 업체 정보에 서비스 추가하던 로직 참고해서 넣으면 될 것 같음.
////        해당 상품에 옵션을 추가하는 것도 페이지를 따로 구성할 것.
//        ManufacturerInfoDTO dto = service.getManuInfo(manuInfoNo);
//        log.info("for read dto: " + dto);
//        model.addAttribute("dto", dto);
    //}
}
