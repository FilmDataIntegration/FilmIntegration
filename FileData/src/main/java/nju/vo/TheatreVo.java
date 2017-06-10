package nju.vo;

import nju.entity.TheatresEntity;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public class TheatreVo {
    private int id;
    private String name;
    private String address;
    private String phone;
    private PlatformsVo platformsVo;

    public TheatreVo() {
    }

    public TheatreVo(TheatresEntity entity) {
        this(entity, false);
    }

    public TheatreVo(TheatresEntity entity, boolean isTransactional) {
        BeanUtils.copyProperties(entity,this,"platformsVo");
        if (isTransactional) platformsVo = new PlatformsVo(entity.getPlatformsEntity());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PlatformsVo getPlatformsVo() {
        return platformsVo;
    }

    public void setPlatformsVo(PlatformsVo platformsVo) {
        this.platformsVo = platformsVo;
    }
}
