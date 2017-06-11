package nju.controller;

import nju.service.FilmService;
import nju.vo.FilmVo;
import nju.vo.ShowInfosVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@RestController
public class FilmController {

    @Resource
    private FilmService filmService;


    @RequestMapping(path = "recommend",method = RequestMethod.GET)
    public Page<FilmVo> index_recommend(){
        return filmService.findAll(1,9,"id", "ASC");
    }


    @RequestMapping(path = "detail",method = RequestMethod.GET)
    public FilmVo detail(@RequestParam(name = "id")int id) {
        return filmService.findById(id);
    }

    @RequestMapping(path = "price",method = RequestMethod.GET)
    public List<ShowInfosVo> price(@RequestParam(name = "id")int id,
                                   @RequestParam(name = "order")String order,
                                   @RequestParam(name = "offset")int offset,
                                   @RequestParam(name = "limit")int limit) {
        return filmService.findById(id).getShowInfosVos();
    }

    @RequestMapping(path = "query",method = RequestMethod.GET)
    public Page<FilmVo>  search(@RequestParam(name="key")String keyword){
        return filmService.search(keyword,0,20,"id", "ASC");
    }


}
