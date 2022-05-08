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
import java.util.HashMap;
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
    @RequestMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate( String typeName,Integer id,HttpServletRequest request){
        System.out.println(id);
        System.out.println(typeName);
        Map<String, Object> map = new HashMap<>();
        //添加类型
        if(id==null&&typeName!=null){
            Integer addSuccess=tbNoteTypeService.add(typeName,request);
            System.out.println(addSuccess);
            if (addSuccess>0){
                map.put("id",addSuccess);
            }
            return map;
        }
        Boolean isSuccess=tbNoteTypeService.updates(typeName,id);

        if (isSuccess){
            map.put("code",1);
        }else {
            map.put("code",0);
            map.put("message","修改或者添加失败");

        }
        return map;
    }
    @RequestMapping("delete")
    public Map<String, Object> delete(Integer id){

        Boolean isSuccess=tbNoteTypeService.del(id);
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
