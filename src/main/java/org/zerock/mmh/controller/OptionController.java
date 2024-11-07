package org.zerock.mmh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.mmh.dto.PageRequestDTO;
import org.zerock.mmh.service.OptionService;

@Controller
@RequestMapping("/option")
@Log4j2
@RequiredArgsConstructor
public class OptionController {
    private final OptionService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/option/list";
    }
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...........");
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

}
