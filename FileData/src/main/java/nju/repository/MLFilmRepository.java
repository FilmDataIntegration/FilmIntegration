package nju.repository;

import nju.entity.MLFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cxworks on 17-7-7.
 */
public interface MLFilmRepository extends JpaRepository<MLFilm,Long> {

    List<MLFilm> findByNameContains(String name);




}
