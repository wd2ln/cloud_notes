package com.zp.controller;

import com.zp.entity.TbNote;
import com.zp.entity.TbUser;
import com.zp.service.TbNoteService;
import com.zp.service.TbNoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    @GetMapping("detail")
    public ModelAndView detail(HttpServletRequest request,
                              HttpServletResponse response,
                              Integer id
    ){
        TbNote tbNote=tbNoteService.selectById(id);

        request.setAttribute("note",tbNote);
        request.setAttribute("changePage","note/detail.jsp");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
    @GetMapping("addOrUpdate")
    public ModelAndView addOrUpdate(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestBody TbNote tbNote
    ){
        System.out.println(tbNote.getTitle());
        System.out.println(tbNote.getDate());
        if (true){
            return null;
        }
        TbNote tbNote1 = tbNoteService.selectById(tbNote.getId());
        System.out.println(tbNote1.getTypeId());
        System.out.println(tbNote1.getTitle());
        request.setAttribute("resultInfo",tbNote1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }
    @GetMapping("delete")
    public Map<String, Object> delete(Integer id){

        Boolean isSuccess=tbNoteService.del(id);
        Map<String, Object> map = new HashMap<>();
        if (isSuccess){
            map.put("code",1);
        }else {
            map.put("code",0);
            map.put("message","删除失败");

        }
        return map;
    }
}
