package com.ags.jonlive.controller;

import com.ags.jonlive.exception.JOnLiveException;
import com.ags.jonlive.v1.CodeExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Example controller.
 * @author Angel
 * @since 03/05/2014
 */
@Controller
@Scope(value = "session")
public class MainController {


    @RequestMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(value = "/sendCode" , method = RequestMethod.POST)
    public ModelAndView sendCode(@RequestParam String importCode, @RequestParam String javaCode, HttpServletRequest request) {
        try {
            new CodeExecutor().load(importCode, javaCode, request);
        } catch (JOnLiveException e) {

        }
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
