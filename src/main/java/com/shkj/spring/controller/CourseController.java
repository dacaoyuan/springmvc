package com.shkj.spring.controller;

import com.shkj.spring.model.Course;
import com.shkj.spring.service.CourseService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private static Logger log = LoggerFactory.getLogger(CourseController.class);


    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    //(1)传统的请求方式
    //:9090/courses/view?courseId=132
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {

        Course course = courseService.getCoursebyId(courseId);

        log.info("viewCourse courseId={}", courseId);

        model.addAttribute(course);

        return "course_overview";


    }

    //(2)RESFUL的请求方式
    //:9090/courses/view2/{courseId}  shuch as://:9090/course/view2/111
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> model) { //Map<String, Object> model

        log.info("viewCourse2 courseId={}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        model.put("course", course);

        return "course_overview";


    }

    //(2)RESFUL的请求方式
    //:9090/courses/view22/{courseId}  shuch as://:9090/course/view22/111
    @RequestMapping(value = "/view22/{courseId}", method = RequestMethod.GET)
    public String viewCourse22(@PathVariable("courseId") Integer courseId, Model model) { //Model model

        Course course = courseService.getCoursebyId(courseId);

        log.info("courseId={}", courseId);

        model.addAttribute(course);

        return "course_overview";


    }


    //(3)servlet request的请求方式
    //:9090/courses/view3?courseId=445
    @RequestMapping(value = "/view3", method = RequestMethod.GET)
    public String viewCourse3(HttpServletRequest request) {

        Integer courseId = Integer.valueOf(request.getParameter("courseId"));

        log.info("viewCourse3 courseId={}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        request.setAttribute("course", course);

        return "course_overview";
    }

    //http://localhost:9090/course/admin?add
    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse() {
        return "course_admin/edit";
    }

    //http://localhost:9090/courses/save
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(@ModelAttribute Course course) {
        String string = ReflectionToStringBuilder.toString(course);//能把传入的对象，里面包含的参数，全部显示成字符串
        log.info("doSave courseId={}", string);

        //在此进行业务操作，比如数据库持久化

        course.setCourseId(123);

        return "redirect:view2/" + course.getCourseId();
    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage() {
        return "course_admin/file";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            long fileSize = file.getSize();
            log.info("fileSize={}", fileSize);

            String fileContentType = file.getContentType();
            log.info("fileContentType={}", fileContentType);

            String fileName = file.getName();
            log.info("fileName={}", originalFilename);
            String s = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("D://aa", s + originalFilename));


        }


        return "success";
    }


}
