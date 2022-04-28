package com.zp.controller;

import com.zp.entity.TbNote;
import com.zp.entity.TbUser;
import com.zp.service.TbNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("index")
@ResponseBody
public class TbNoteController {
    @Autowired
    private TbNoteService tbNoteService;
    @PostMapping("page")
    public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        HttpSession session = request.getSession(false);
        TbUser user = (TbUser) session.getAttribute("user");
        TbNote tbNote = new TbNote();
        tbNote.setTypeId(user.getId());
        //获取云记列表
        Map<String, Object> dates = tbNoteService.getDates(tbNote, pageNum, pageSize);
        session.setAttribute("page",dates.get("pageinfo"));
        //获取云记日期
        //tbNoteService.getDateInfo(tbNote,pageNum,pageSize);
        //获取云记类别
        tbNoteService.getDateType(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }
}
