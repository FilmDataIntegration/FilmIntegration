package nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@Entity
@Table(name = "platform_films")
public class PlatformFilmsEntity {
    private int id;
    private String description;
    private Double score;
    private String type;
    private PlatformsEntity platformsEntity;

    @ManyToOne
    @JoinColumn(name = "platform_id", referencedColumnName = "id")
    public PlatformsEntity getPlatformsEntity() {
        return platformsEntity;
    }

    public void setPlatformsEntity(PlatformsEntity platformsEntity) {
        this.platformsEntity = platformsEntity;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformFilmsEntity that = (PlatformFilmsEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
