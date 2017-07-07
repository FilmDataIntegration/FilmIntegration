package nju.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by cxworks on 17-7-7.
 */
@Entity
@Table(name = "douban")
@Data
public class MLFilm {


    @Id
    @Column(name = "id")
    long id;
    @Basic
            @Column(name = "name")
    String name;
    @Basic
            @Column(name = "release_date")
    int release_date;
    @Basic
            @Column(name = "mark")
    double mark;
    @Basic
            @Column(name = "mark_number")
    int mark_number;
    @Basic
            @Column(name = "director")
    String director;
    @Basic
            @Column(name = "country")
    String country;
    @Basic
            @Column(name = "runtime")
    int runtime;


    public Object[] toObjArr(){
        if (director==null) director="";
        if (country==null) country="";

        Object[] objs = {director,country,release_date,mark_number,(double)runtime,mark};
        return objs;
    }

}
