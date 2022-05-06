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
                                  String date,
                                  String title,
                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "6") Integer pageSize){
        HttpSession session = request.getSession(false);
        //获取用户id
        TbUser user = (TbUser) session.getAttribute("user");
        //获取title
        String title1 = (String)session.getAttribute("title");
        //设置参数
        TbNote tbNote = new TbNote();
        if(id!=null){

            tbNote.setTypeId(id);
        }
        if (date!=null){
            tbNote.setDate(date);
        }
        if (title1!=null){
            System.out.println("找到title");
            tbNote.setTitle(title1);
        }
        tbNote.setId(user.getId());
        //tbNote.setPubTime();
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
    /*@RequestMapping("searchType")
    public ModelAndView center(HttpServletRequest request, HttpServletResponse response,Integer id){
        HttpSession session = request.getSession(false);
        session.setAttribute("id",id);
        System.out.println("???????????????????????????????????");
        System.out.println(id);
        System.out.println("??????????????????????????????????");
        //创建返回视图
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("forward:/index/paege?id="+id);
        return modelAndView;
    }*/
    @RequestMapping("searchDate")
    public ModelAndView center(HttpServletRequest request, HttpServletResponse response,String date){
        HttpSession session = request.getSession(false);
        System.out.println("=============+++++++++++++++++++++");
        String[] years = date.split("年");
        String mm = years[1].substring(0, 2);
        Map<String, Object> map = tbNoteService.getdateAll(years[0]+" "+mm);
        System.out.println(map);
        session.setAttribute("pubTime",map.get("sell"));
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("forward:/index/page");
        return modelAndView;
    }
    @GetMapping("searchTitle")
    public ModelAndView searchTitle(HttpSession session,String title){
        System.out.println("开始转发，执行查找");
        session.setAttribute("title",title);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("forward:/index/page");
        return modelAndView;
    }
}
