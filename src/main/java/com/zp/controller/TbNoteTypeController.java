package com.zp.controller;

import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("index")
public class TbNoteTypeController {
    @Autowired
    private TbNoteTypeService tbNoteTypeService;
    @GetMapping("searchType")
    public ModelAndView search(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("运行======");
        modelAndView.setViewName("forward:/index/page?id="+id);
        return modelAndView;
    }
}
