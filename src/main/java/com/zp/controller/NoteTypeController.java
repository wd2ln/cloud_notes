package com.zp.controller;

import com.zp.entity.TbNote;
import com.zp.entity.TbUser;
import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("type")
public class NoteTypeController {
    @Autowired
    private TbNoteTypeService tbNoteTypeService;
    @RequestMapping("list")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        TbUser user = (TbUser)session.getAttribute("user");
        Integer id = user.getId();
        Map<String, Object> map = tbNoteTypeService.selectType(id);
        session.setAttribute("list",map.get("list"));
        session.setAttribute("menu_page","type");
        session.setAttribute("changePage","type/list.jsp");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
}
