package com.zp.controller;

import com.zp.dao.TbNoteMapper;
import com.zp.entity.TbNote;
import com.zp.entity.TbUser;
import com.zp.service.BaoBiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("report")
public class BaoBiaoController {
    @Autowired
    private BaoBiaoService baoBiaoService;

    @RequestMapping("info")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        session.setAttribute("menu_page","report");
        session.setAttribute("changePage","report/info.jsp");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
    @RequestMapping("location")
    public Map<String,Object> wei(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        TbUser user = (TbUser)session.getAttribute("user");

        List<TbNote> tbNote = baoBiaoService.selectJW(user.getId());


        Map<String, Object> map = new HashMap<>();
        if (tbNote!=null){
            map.put("code",1);
            map.put("data",tbNote);
        }

        return map;
    }
    @GetMapping("month")
    public Map<String, Object> month(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        TbUser user = (TbUser)session.getAttribute("user");

        Map<String, Object> map = baoBiaoService.selectMC(user.getId());

        return map;
    }
}
