package nju.repository;

import nju.entity.FilmsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public interface FilmRepository extends JpaRepository<FilmsEntity,Integer>{

    Page<FilmsEntity> findByNameContains(String name, Pageable pageable);

}
