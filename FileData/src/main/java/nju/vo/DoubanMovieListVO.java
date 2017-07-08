package nju.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nju.entity.MLFilm;

import java.util.List;

/**
 * Created by cxworks on 17-7-8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoubanMovieListVO {

    long total;
    List<MLFilm> rows;
}
