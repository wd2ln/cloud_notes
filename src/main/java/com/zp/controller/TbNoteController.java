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
    @RequestMapping("page")
    public ModelAndView indexPage(HttpServletRequest request, HttpServletResponse response,
                                  Integer id,
                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        HttpSession session = request.getSession(false);
        //获取用户id
        TbUser user = (TbUser) session.getAttribute("user");
        TbNote tbNote = new TbNote();
        tbNote.setId(id);
        tbNote.setId(user.getId());
        //获取云记列表
        Map<String, Object> dates = tbNoteService.getDates(tbNote, pageNum, pageSize);
        session.setAttribute("page",dates.get("pageinfo"));
        //获取云记日期
        Map<String, Object> dateInfo = tbNoteService.getDateInfo(tbNote);
        session.setAttribute("dateInfo",dateInfo.get("dateinfo"));
        //获取云记类别
        Map<String, Object> dateType = tbNoteService.getDateType(tbNote);
        session.setAttribute("typeInfo",dateType.get("typeinfo"));
        //主页8、9
        session.setAttribute("menu_page","index");
        session.setAttribute("changePage","note/list.jsp");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }
}
