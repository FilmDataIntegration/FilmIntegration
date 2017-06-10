package nju.vo;

import nju.entity.PlatformsEntity;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public class PlatformsVo {
    private int id;
    private String platform;

    public PlatformsVo() {
    }

    public PlatformsVo(PlatformsEntity platformsEntity) {
        BeanUtils.copyProperties(platformsEntity,this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
