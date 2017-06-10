package nju.dao;

import nju.entity.FilmsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public interface FilmDao {
    /**
     * Find all films by id
     * @param page page number
     * @param pageSize page size
     * @param sortColumn [optional] sort by what column
     * @param sortDirection [optional] asc or des
     * @return page of {@link FilmsEntity}
     */
    Page<FilmsEntity> findAll(int page, int pageSize, String sortColumn, Sort.Direction sortDirection);

    /**
     *
     * @param keyWord search keyword
     * @param page page number
     * @param pageSize page size
     * @param sortColumn [optional] sort by what column
     * @param sortDirection [optional] asc or des
     * @return page of {@link FilmsEntity}
     */
    Page<FilmsEntity> search(String keyWord, int page, int pageSize, String sortColumn, Sort.Direction sortDirection);

    /**
     * Find detail by id
     * @param id id
     * @return {@link FilmsEntity}
     */
    FilmsEntity findById(int id);
}
