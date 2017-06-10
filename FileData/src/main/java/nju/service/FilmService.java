package nju.service;

import nju.vo.FilmVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public interface FilmService {

    /**
     * Find all films by id
     * @param page page number
     * @param pageSize page size
     * @param sortColumn [optional] sort by what column
     * @param sortDirection [optional] ASC or DESC or null
     * @return page of {@link FilmVo}
     */
    Page<FilmVo> findAll(int page, int pageSize, String sortColumn, String sortDirection);

    /**
     * search film summary by film name
     * @param keyWord search keyword
     * @param page page number
     * @param pageSize page size
     * @param sortColumn [optional] sort by what column
     * @param sortDirection [optional] ASC or DESC or null
     * @return page of {@link FilmVo}
     */
    Page<FilmVo> search(String keyWord, int page, int pageSize, String sortColumn, String sortDirection);

    /**
     * find detail of a film
     * @param id id
     * @return detail of {@link FilmVo}
     */
    FilmVo findById(int id);
}
