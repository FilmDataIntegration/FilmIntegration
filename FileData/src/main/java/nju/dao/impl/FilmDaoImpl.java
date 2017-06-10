package nju.dao.impl;

import com.google.common.base.Preconditions;
import nju.dao.FilmDao;
import nju.entity.FilmsEntity;
import nju.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@Repository
public class FilmDaoImpl implements FilmDao {

    @Resource
    private FilmRepository filmRepository;

    private static final String DEFAULT_SORT = "id";

    /**
     * Find all films by id
     *
     * @param page          page number
     * @param pageSize      page size
     * @param sortColumn    [optional] sort by what column
     * @param sortDirection [optional] asc or des
     * @return page of {@link FilmsEntity}
     */
    @Override
    public Page<FilmsEntity> findAll(int page, int pageSize, String sortColumn, Sort.Direction sortDirection) {
        Preconditions.checkArgument(page>=0);
        Preconditions.checkArgument(pageSize>=1);
        String sortCol = sortColumn == null? DEFAULT_SORT: sortColumn;
        Sort.Direction direction = sortDirection == null? Sort.Direction.ASC: sortDirection;
        Sort sort = new Sort(direction, sortCol);
        Pageable pageable = new PageRequest(page,pageSize,sort);
        return filmRepository.findAll(pageable);
    }

    /**
     * @param keyWord       search keyword
     * @param page          page number
     * @param pageSize      page size
     * @param sortColumn    [optional] sort by what column
     * @param sortDirection [optional] asc or des
     * @return page of {@link FilmsEntity}
     */
    @Override
    public Page<FilmsEntity> search(String keyWord, int page, int pageSize,
                                    String sortColumn, Sort.Direction sortDirection) {
        Preconditions.checkArgument(page>=0);
        Preconditions.checkArgument(pageSize>=1);
        String sortCol = sortColumn == null? DEFAULT_SORT: sortColumn;
        Sort.Direction direction = sortDirection == null? Sort.Direction.ASC: sortDirection;
        Sort sort = new Sort(direction, sortCol);
        Pageable pageable = new PageRequest(page,pageSize,sort);
        return filmRepository.findByNameContains(keyWord,pageable);
    }

    /**
     * Find detail by id
     *
     * @param id id
     * @return {@link FilmsEntity}
     */
    @Override
    public FilmsEntity findById(int id) {
        Preconditions.checkArgument(id>0);
        return filmRepository.findOne(id);
    }
}
