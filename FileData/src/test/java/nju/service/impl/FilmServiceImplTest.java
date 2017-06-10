package nju.service.impl;

import nju.FilmDataApplicationTests;
import nju.service.FilmService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-06-10.
 */
public class FilmServiceImplTest extends FilmDataApplicationTests {

    @Resource
    private FilmService filmService;

    @Test
    public void findAll() throws Exception {
        System.out.println(filmService.findAll(0,10,"id","ASC").getContent());
    }

    @Test
    public void search() throws Exception {
        System.out.println(filmService.search("",0,10,"id","ASC").getContent().size());
    }

    @Test
    public void findById() throws Exception {
        System.out.println(filmService.findById(1));
    }

}