package nju.dao;

import nju.entity.FilmsEntity;
import nju.entity.MLFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by cxworks on 17-7-7.
 */
public interface MLFilmDAO {

    /**
     * Find all films by id
     * @param page page number
     * @param pageSize page size
     * @param sortColumn [optional] sort by what column
     * @param sortDirection [optional] asc or des
     * @return page of {@link MLFilm}
     */
    Page<MLFilm> findAll(int page, int pageSize, String sortColumn, Sort.Direction sortDirection);

    /**
     *
     * @param keyWord search keyword
     * @return page of {@link MLFilm}
     */
    List<MLFilm> search(String keyWord);

}
