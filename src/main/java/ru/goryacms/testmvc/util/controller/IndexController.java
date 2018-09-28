package ru.goryacms.testmvc.util.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView start() {
        LOGGER.info("Start page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }




//    @RequestMapping(value="/error", method = RequestMethod.GET)
//    public ModelAndView error() {
//        LOGGER.info("Error page");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("errorPage");
//        return modelAndView;
//    }
}