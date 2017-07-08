package nju.dao.impl;

import com.google.common.base.Preconditions;
import nju.dao.MLFilmDAO;
import nju.entity.MLFilm;
import nju.repository.MLFilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cxworks on 17-7-7.
 */
@Component
public class MLFilmDAOImpl implements MLFilmDAO {

    @Resource
    MLFilmRepository mlFilmRepository;
    @Override
    public Page<MLFilm> findAll(int page, int pageSize, String sortColumn, Sort.Direction sortDirection) {
        Preconditions.checkArgument(page>=0);
        Preconditions.checkArgument(pageSize>=1);
        String sortCol = sortColumn == null? "id": sortColumn;
        Sort.Direction direction = sortDirection == null? Sort.Direction.ASC: sortDirection;
        Sort sort = new Sort(direction, sortCol);
        Pageable pageable = new PageRequest(page,pageSize,sort);
        return mlFilmRepository.findAll(pageable);
    }

    @Override
    public List<MLFilm> search(String keyWord) {

        return mlFilmRepository.findByNameContains(keyWord);
    }
}
