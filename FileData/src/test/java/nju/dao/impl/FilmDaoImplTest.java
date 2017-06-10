package nju.dao.impl;

import nju.FilmDataApplicationTests;
import nju.dao.FilmDao;
import nju.entity.FilmsEntity;
import org.junit.Test;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public class FilmDaoImplTest extends FilmDataApplicationTests {

    @Resource
    private FilmDao filmDao;

    @Test
    public void findAll() throws Exception {
        Page<FilmsEntity> filmsEntities = filmDao.findAll(0,10,null,null);
        assertTrue(filmsEntities.getContent().size()==10);
    }

    @Test
    public void search() throws Exception {
        Page<FilmsEntity> filmsEntities = filmDao.search("神奇女侠",0,10,null,null);
        List<FilmsEntity> filmsEntityList = filmsEntities.getContent();
        assertEquals("","神奇女侠",filmsEntityList.get(0).getName());
    }

    @Test
    @Transactional
    public void findById() throws Exception {
        assertTrue(filmDao.findById(1).getPlatformFilmsEntities().get(0).getPlatformsEntity().getId()==1);
    }

}