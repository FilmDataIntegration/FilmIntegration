package nju.service.impl;

import nju.dao.FilmDao;
import nju.entity.FilmsEntity;
import nju.service.FilmService;
import nju.vo.FilmVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmDao filmDao;

    /**
     * Find all films by id
     *
     * @param page          page number
     * @param pageSize      page size
     * @param sortColumn    [optional] sort by what column
     * @param sortDirection [optional] ASC or DESC or null
     * @return page of {@link FilmVo}
     */
    @Override
    public Page<FilmVo> findAll(int page, int pageSize, String sortColumn, String sortDirection) {
        if (page < 0 || pageSize <= 0 ||
                (sortDirection!=null && !sortDirection.equals("ASC") && !sortDirection.equals("DESC")))
            throw new IllegalArgumentException("Invalid find all args");
        Page<FilmsEntity> filmsEntities = filmDao
                .findAll(page,pageSize,sortColumn, Sort.Direction.valueOf(sortDirection));
        return new PageImpl<>(
                filmsEntities.getContent().stream()
                        .map(FilmVo::new).collect(Collectors.toList()),
                filmsEntities.nextPageable(),
                filmsEntities.getTotalElements()
        );
    }

    /**
     * search film summary by film name
     *
     * @param keyWord       search keyword
     * @param page          page number
     * @param pageSize      page size
     * @param sortColumn    [optional] sort by what column
     * @param sortDirection [optional] ASC or DESC or null
     * @return page of {@link FilmVo}
     */
    @Override
    public Page<FilmVo> search(String keyWord, int page, int pageSize, String sortColumn, String sortDirection) {
        if (page < 0 || pageSize <= 0 ||
                (sortDirection!=null && !sortDirection.equals("ASC") && !sortDirection.equals("DESC")))
            throw new IllegalArgumentException("Invalid search args");
        keyWord = getTargetStr(keyWord);
        Page<FilmsEntity> filmsEntities = filmDao
                .search(keyWord,page,pageSize,sortColumn, Sort.Direction.valueOf(sortDirection));
        return new PageImpl<>(
                filmsEntities.getContent().stream()
                        .map(FilmVo::new).collect(Collectors.toList()),
                filmsEntities.nextPageable(),
                filmsEntities.getTotalElements()
        );
    }

    /**
     * find detail of a film
     *
     * @param id id
     * @return detail of {@link FilmVo}
     */
    @Override
    @Transactional
    public FilmVo findById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("id must more than zero");
        return new FilmVo(filmDao.findById(id),true);
    }

    /**
     * filter string
     * @param sourceStr source string
     * @return filtered string
     */
    private String getTargetStr(String sourceStr) {
        return  sourceStr.replaceAll("&", "&")
                                .replaceAll(";", ",")
                                .replaceAll("'", "")
                                .replaceAll("<", "<")
                                .replaceAll(">", ">")
                                .replaceAll("/", "")
                                .replaceAll("%", "")
                                .replaceAll("=", "");
    }
}
