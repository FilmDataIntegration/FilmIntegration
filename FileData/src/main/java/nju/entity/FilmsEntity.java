package nju.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by cuihao on 2017-06-10.
 * Film Entity
 */
@Entity
@Table(name = "films")
public class FilmsEntity {
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
    private List<PlatformFilmsEntity> platformFilmsEntities;
    private List<ShowInfosEntity> showInfosEntities;

    @OneToMany
    @JoinColumn(name = "film_id")
    public List<PlatformFilmsEntity> getPlatformFilmsEntities() {
        return platformFilmsEntities;
    }

    public void setPlatformFilmsEntities(List<PlatformFilmsEntity> platformFilmsEntities) {
        this.platformFilmsEntities = platformFilmsEntities;
    }

    @OneToMany
    @JoinColumn(name = "film_id")
    public List<ShowInfosEntity> getShowInfosEntities() {
        return showInfosEntities;
    }

    public void setShowInfosEntities(List<ShowInfosEntity> showInfosEntities) {
        this.showInfosEntities = showInfosEntities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "first_run")
    public Date getFirstRun() {
        return firstRun;
    }

    public void setFirstRun(Date firstRun) {
        this.firstRun = firstRun;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "d")
    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "main_characters")
    public String getMainCharacters() {
        return mainCharacters;
    }

    public void setMainCharacters(String mainCharacters) {
        this.mainCharacters = mainCharacters;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmsEntity that = (FilmsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (firstRun != null ? !firstRun.equals(that.firstRun) : that.firstRun != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (d != null ? !d.equals(that.d) : that.d != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (mainCharacters != null ? !mainCharacters.equals(that.mainCharacters) : that.mainCharacters != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (firstRun != null ? firstRun.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (mainCharacters != null ? mainCharacters.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
