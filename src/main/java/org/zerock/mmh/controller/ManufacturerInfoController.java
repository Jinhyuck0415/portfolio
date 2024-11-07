package org.zerock.mmh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.mmh.dto.ManufacturerInfoDTO;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.service.ManufacturerInfoService;
import org.zerock.mmh.service.ServiceService;

import java.util.List;

@Controller
@RequestMapping("/manuinfo")
@Log4j2
@RequiredArgsConstructor
public class ManufacturerInfoController {
    private final ManufacturerInfoService service;
    private final ServiceService sService;

    @GetMapping("/")
    public String index() {
        return "redirect:/manuinfo/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...........");
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get.........");
    }

    @PostMapping("/register")
    public String registerPost(ManufacturerInfoDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);

        //새로 추가된 entity의 번호
        String manuInfoNo = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", manuInfoNo);
        return "redirect:/manuinfo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(String manuInfoNo, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("manuInfoNo: " + manuInfoNo);
//        ManufacturerInfoDTO dto = service.read(manuInfoNo);

        ManufacturerInfoDTO dto = service.getManuInfo(manuInfoNo);
        log.info("for read dto: " + dto);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(String manuInfoNo, RedirectAttributes redirectAttributes) {
        log.info("manuInfoNo: " + manuInfoNo);
        service.remove(manuInfoNo);
        redirectAttributes.addFlashAttribute("msg", manuInfoNo);
        return "redirect:/manuinfo/list";
    }

    @PostMapping("/modify")
    public String modify(ManufacturerInfoDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("post modify.........................");
        log.info("dto..." + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("manuInfoNo", dto.getManuInfoNo());

        return "redirect:/manuinfo/read";
    }
}
