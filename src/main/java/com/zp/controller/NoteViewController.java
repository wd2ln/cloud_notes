package com.zp.controller;

import com.zp.entity.TbUser;
import com.zp.service.TbNoteService;
import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("note")
public class NoteViewController {
    @Autowired
    private TbNoteService tbNoteService;
    @RequestMapping("view")
    public ModelAndView index(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(required = false) Integer noteId
    ){
        HttpSession session = request.getSession(false);
        TbUser user = (TbUser)session.getAttribute("user");
        Integer id = user.getId();

        Map<String, Object> map = tbNoteService.selectTypess(noteId,id);

        session.setAttribute("typeList",map.get("list"));
        session.setAttribute("changePage","note/view.jsp");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
}
