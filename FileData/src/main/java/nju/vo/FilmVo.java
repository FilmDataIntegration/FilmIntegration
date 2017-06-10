package nju.vo;

import nju.entity.FilmsEntity;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cuihao on 2017-06-10.
 * Film info
 */
public class FilmVo {
    private int id;
    private String name;
    private Date firstRun;
    private String language;
    private Integer d;
    private String area;
    private Integer length;
    private String director;
    private String mainCharacters;
    private String url;
    //只在findById中不为null
    private List<ShowInfosVo> showInfosVos;
    //只在findById中不为null
    private List<PlatformFilmsVo> platformFilmsVos;

    public FilmVo() {}

    public FilmVo(FilmsEntity entity) {
        this(entity, false);
    }

    public FilmVo(FilmsEntity entity, boolean isTransactional) {
        BeanUtils.copyProperties(entity,this,"date","showInfosVos","platformFilmsVos");
        firstRun = new Date(entity.getFirstRun().getTime());
        if (isTransactional) {
            showInfosVos = entity.getShowInfosEntities().stream()
                    .map(entity1 -> new ShowInfosVo(entity1,true))
                    .collect(Collectors.toList());
            platformFilmsVos = entity.getPlatformFilmsEntities().stream()
                    .map(entity1 -> new PlatformFilmsVo(entity1,true))
                    .collect(Collectors.toList());
        }
    }

    public List<ShowInfosVo> getShowInfosVos() {
        return showInfosVos;
    }

    public void setShowInfosVos(List<ShowInfosVo> showInfosVos) {
        this.showInfosVos = showInfosVos;
    }

    public List<PlatformFilmsVo> getPlatformFilmsVos() {
        return platformFilmsVos;
    }

    public void setPlatformFilmsVos(List<PlatformFilmsVo> platformFilmsVos) {
        this.platformFilmsVos = platformFilmsVos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFirstRun() {
        return firstRun;
    }

    public void setFirstRun(Date firstRun) {
        this.firstRun = firstRun;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainCharacters() {
        return mainCharacters;
    }

    public void setMainCharacters(String mainCharacters) {
        this.mainCharacters = mainCharacters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
