package com.wangcl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: wangcl
 * @Date: 2018/6/7 17:09
 * Describe: 错误页面跳转
 */
@Controller
public class ErrorPageControl {

    @GetMapping("/400")
    public String error400() {
        return "errorpage/400";
    }

    @GetMapping("/401")
    public String error401() {
        return "errorpage/401";
    }

    @GetMapping("/403")
    public String error403() {
        return "errorpage/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "errorpage/404";
    }

    @GetMapping("/408")
    public String error408() {
        return "errorpage/408";
    }

    @GetMapping("/500")
    public String error500() {
        return "errorpage/500";
    }

    @GetMapping("/502")
    public String error502() {
        return "errorpage/502";
    }

}
