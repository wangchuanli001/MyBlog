package xyz.wangcl.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.wangcl.pojo.Person;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    @RequestMapping("/test")
    public ModelAndView welcomeIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.addObject("name", "xx");
        return mv;
    }

    @RequestMapping(value = "/getPerson/{age}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person getPerson(@PathVariable int age) {
        Person p = new Person();
        p.setName("小李");
        p.setAge(age);
        return p;
    }


}