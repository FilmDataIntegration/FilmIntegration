package nju.vo;

import nju.entity.ShowInfosEntity;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public class ShowInfosVo {
    private int id;
    private String videoHall;
    private Timestamp time;
    private Double price;
    private Integer seatsLeft;
    private PlatformsVo platformsVo;
    private TheatreVo theatreVo;

    public ShowInfosVo() {
    }

    public ShowInfosVo(ShowInfosEntity entity) {
        this(entity, false);
    }

    public ShowInfosVo(ShowInfosEntity entity, boolean isTransactional) {
        BeanUtils.copyProperties(entity,this,"platformsVo","theatreVo");
        if (isTransactional) {
            platformsVo = new PlatformsVo(entity.getPlatformsEntity());
            theatreVo = new TheatreVo(entity.getTheatresEntity(), true);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoHall() {
        return videoHall;
    }

    public void setVideoHall(String videoHall) {
        this.videoHall = videoHall;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(Integer seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    public PlatformsVo getPlatformsVo() {
        return platformsVo;
    }

    public void setPlatformsVo(PlatformsVo platformsVo) {
        this.platformsVo = platformsVo;
    }

    public TheatreVo getTheatreVo() {
        return theatreVo;
    }

    public void setTheatreVo(TheatreVo theatreVo) {
        this.theatreVo = theatreVo;
    }
}
