package nju.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@Entity
@Table(name = "show_infos")
public class ShowInfosEntity {
    private int id;
    private String videoHall;
    private Timestamp time;
    private Double price;
    private Integer seatsLeft;
    private PlatformsEntity platformsEntity;
    private TheatresEntity theatresEntity;

    @ManyToOne
    @JoinColumn(name = "platform_id", referencedColumnName = "id")
    public PlatformsEntity getPlatformsEntity() {
        return platformsEntity;
    }

    public void setPlatformsEntity(PlatformsEntity platformsEntity) {
        this.platformsEntity = platformsEntity;
    }

    @ManyToOne
    @JoinColumn(name = "theatre_id", referencedColumnName = "id")
    public TheatresEntity getTheatresEntity() {
        return theatresEntity;
    }

    public void setTheatresEntity(TheatresEntity theatresEntity) {
        this.theatresEntity = theatresEntity;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "video_hall")
    public String getVideoHall() {
        return videoHall;
    }

    public void setVideoHall(String videoHall) {
        this.videoHall = videoHall;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "seats_left")
    public Integer getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(Integer seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowInfosEntity that = (ShowInfosEntity) o;

        if (id != that.id) return false;
        if (videoHall != null ? !videoHall.equals(that.videoHall) : that.videoHall != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (seatsLeft != null ? !seatsLeft.equals(that.seatsLeft) : that.seatsLeft != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (videoHall != null ? videoHall.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (seatsLeft != null ? seatsLeft.hashCode() : 0);
        return result;
    }
}
