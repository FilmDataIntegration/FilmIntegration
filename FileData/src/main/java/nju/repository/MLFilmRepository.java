package nju.repository;

import nju.entity.MLFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cxworks on 17-7-7.
 */
public interface MLFilmRepository extends JpaRepository<MLFilm,Long> {

    Page<MLFilm> findByNameContains(String name, Pageable pageable);




}
