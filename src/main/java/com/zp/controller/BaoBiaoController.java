package com.zp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("report")
public class BaoBiaoController {
    @RequestMapping("info")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        session.setAttribute("menu_page","report");
        session.setAttribute("changePage","report/info.jsp");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
}
