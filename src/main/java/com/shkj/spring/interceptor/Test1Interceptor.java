package com.shkj.spring.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test1Interceptor implements HandlerInterceptor {


    /**
     * @return 返回值：表示我们是否需要将当前的请求拦截下来
     * 返回:false, 请求将被终止
     * 返回:true，请求将被继续
     * Object o 表示的是被拦截的请求的目标对象
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("Test1Interceptor.preHandle");

        /*通过拦截器也能解决乱码的问题*/
        /*httpServletRequest.setCharacterEncoding("utf-8");*/
        /*httpServletResponse.setCharacterEncoding("utf-8");*/


        if (httpServletRequest.getSession().getAttribute("user") == null) {
            System.out.println("Test1Interceptor.preHandle");
            //如果用户没有登录，就终止请求，并发送到登录界面
            httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
            return false;

        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Test1Interceptor.postHandle");

        //可以通过modelAndView参数来改变显示的视图，或修改发往视图的方法
        modelAndView.addObject("msg", "这是传的是被拦截器修改之后的视图信息");
        modelAndView.setViewName("home2");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("Test1Interceptor.afterCompletion");


    }
}
