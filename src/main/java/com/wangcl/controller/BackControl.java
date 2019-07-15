package com.wangcl.controller;

import com.wangcl.constant.SiteOwner;
import com.wangcl.service.ArticleService;
import com.wangcl.utils.TransCodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

/**
 * @author: wangcl
 * @Date: 2018/5/17 19:24
 * Describe: 所有页面跳转
 */
@Controller
public class BackControl {

    @Autowired
    ArticleService articleService;


    /**
     * 跳转首页
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model,
                        @AuthenticationPrincipal Principal principal) {
        String username = null;
        model = setPageAttr(model);
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            request.getSession().removeAttribute("lastUrl");
            return "index";
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
        return "index";
    }


    /**
     * 跳转登录页
     */
    @GetMapping("/login")
    public String login(Model model) {
        model = setPageAttr(model);
        return "column/user/login";
    }

    /**
     * 登录前尝试保存上一个页面的url
     */
    @GetMapping("/toLogin")
    public @ResponseBody
    void toLogin(HttpServletRequest request, Model model) {
        model = setPageAttr(model);
        //保存跳转页面的url
        request.getSession().setAttribute("lastUrl", request.getHeader("Referer"));
    }

    /**
     * 跳转注册页
     */
    @GetMapping("/register")
    public String register(Model model) {
        model = setPageAttr(model);
        return "column/user/register";
    }

    /**
     * 跳转到归档页
     */
    @GetMapping("/archives")
    public String archives(HttpServletResponse response,
                           HttpServletRequest request, Model model) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");
        String archive = request.getParameter("archive");
        model = setPageAttr(model);
        try {
            response.setHeader("archive", TransCodingUtil.stringToUnicode(archive));
        } catch (Exception e) {
            System.out.println("归档请求error");
        }
        return "column/archives";
    }

    /**
     * 跳转到分类页
     */
    @GetMapping("/categories")
    public String categories(HttpServletResponse response,
                             HttpServletRequest request, Model model) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");
        String category = request.getParameter("category");
        model = setPageAttr(model);
        try {
            response.setHeader("category", TransCodingUtil.stringToUnicode(category));
        } catch (Exception e) {
        }
        return "column/categories";
    }

    /**
     * 跳转到标签页
     */
    @GetMapping("/tags")
    public String tags(HttpServletResponse response,
                       HttpServletRequest request, Model model) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");
        model = setPageAttr(model);
        String tag = request.getParameter("tag");
        try {
            response.setHeader("tag", TransCodingUtil.stringToUnicode(tag));
        } catch (Exception e) {
        }
        return "column/tags";
    }

    /**
     * 跳转关于我页面
     */
    @GetMapping("/aboutme")
    public String aboutme(HttpServletRequest request, Model model) {
        model = setPageAttr(model);
        request.getSession().removeAttribute("lastUrl");
        return "column/aboutme";
    }

    /**
     * 跳转更新页
     */
    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model = setPageAttr(model);
        request.getSession().removeAttribute("lastUrl");
        return "column/update";
    }

    /**
     * 跳转友链页
     */
    @GetMapping("/friendlylink")
    public String friendlylink(HttpServletRequest request, Model model) {
        model = setPageAttr(model);
        request.getSession().removeAttribute("lastUrl");
        return "column/friendlylink";
    }


    /**
     * 跳转到用户页
     */
    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        model = setPageAttr(model);
        request.getSession().removeAttribute("lastUrl");
        return "column/user/user";
    }

    /**
     * 跳转到文章编辑页
     */
    @GetMapping("/editor")
    public String editor(HttpServletRequest request) {
        request.getSession().removeAttribute("lastUrl");
        String id = request.getParameter("id");
        if (!"".equals(id)) {
            request.getSession().setAttribute("id", id);
        }
        return "column/article/editor";
    }

    /**
     * 跳转到文章显示页
     */
    @GetMapping("/article/{articleId}")
    public String show(@PathVariable("articleId") long articleId,
                       HttpServletResponse response,
                       Model model,
                       HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        Map<String, String> articleMap = articleService.findArticleTitleByArticleId(articleId);
        if (articleMap.get("articleTitle") != null) {
            model.addAttribute("articleTitle", articleMap.get("articleTitle"));
            String articleTabloid = articleMap.get("articleTabloid");
            String articleTags = articleMap.get("articleTags");
            if (articleTabloid.length() <= 110) {
                model.addAttribute("articleTabloid", articleTabloid);
            } else {
                model.addAttribute("articleTabloid", articleTabloid.substring(0, 110));
            }
            if (articleTags.length() <= 6) {
                model.addAttribute("articleTags", "博客,IT,❤,springboot,java,后端");
            } else {
                model.addAttribute("articleTags", articleTags);
            }
        }
        //将文章id存入响应头
        response.setHeader("articleId", String.valueOf(articleId));
        return "column/article/show";
    }

    /**
     * 跳转到超级管理员页
     */
    @GetMapping("/superadmin")
    public String superadmin(HttpServletRequest request) {
        request.getSession().removeAttribute("lastUrl");
        return "column/admin/superadmin";
    }

    /* 公共设置页面head文件*/
    public Model setPageAttr(Model model) {
        model.addAttribute("common_title", SiteOwner.SITE_COMMON_TITLE);
        model.addAttribute("common_keywords", SiteOwner.SITE_COMMON_KEYWORDS);
        model.addAttribute("common_description", SiteOwner.SITE_COMMON_DESCRIPTION);
        return model;
    }


    /**
     * 跳转我的女孩页
     */
    @GetMapping("/mylove")
    public String myLove() {
        return "extendpage/mylove";
    }

    /**
     * 跳转我的藏心阁页
     */
    @GetMapping("/myheart")
    public String myheart() {
        return "myheart";
    }

    /**
     * 跳转我的故事页
     */
    @GetMapping("/mystory")
    public String mystory(HttpServletRequest request) {
        request.getSession().removeAttribute("lastUrl");
        return "extendpage/mystory";
    }

    /**
     * 跳转阿狸表白页
     */
    @GetMapping("/ali")
    public String ali() {
        return "extendpage/ali";
    }


}
