package com.shkj.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloMvcController {


    @RequestMapping("/mvc")
    public String helloMvc() {
        return "home";
    }


    @RequestMapping("/viewAll")
    public ModelAndView viewAll(String name, String password) {
        ModelAndView mv = new ModelAndView();
        System.out.println("HelloMvcController.viewAll");
        System.out.println("name=" + name);
        System.out.println("password=" + password);
        mv.setViewName("home");
        mv.addObject("msg","这里传的是viewAll视图信息");
        return mv;
    }

    /*@RequestMapping("/query")
    public ModelAndView query(String name, String password) {
        ModelAndView mv = new ModelAndView();
        System.out.println("HelloMvcController.viewAll");
        System.out.println("name=" + name);
        System.out.println("password=" + password);
        mv.setViewName("home");
        return mv;
    }*/


}




