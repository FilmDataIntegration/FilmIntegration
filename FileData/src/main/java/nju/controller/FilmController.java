package nju.controller;

import nju.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@Controller
public class FilmController {

    @Resource
    private FilmService filmService;

    public String index(ModelAndView modelAndView) {
        modelAndView.addObject("list", filmService.findAll(0,10,null,null));
        return "index";
    }

    public String detail(ModelAndView modelAndView) {
        return "";
    }

}
