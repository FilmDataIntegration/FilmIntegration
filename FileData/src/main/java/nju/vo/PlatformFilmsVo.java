package nju.vo;

import nju.entity.PlatformFilmsEntity;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
public class PlatformFilmsVo {
    private int id;
    private String description;
    private Double score;
    private String type;
    private PlatformsVo platformsVo;

    public PlatformFilmsVo() {
    }

    public PlatformFilmsVo(PlatformFilmsEntity entity) {
        this(entity, false);
    }

    public PlatformFilmsVo(PlatformFilmsEntity entity, boolean isTransactional) {
        BeanUtils.copyProperties(entity, this, "platformsVo");
        if (isTransactional) platformsVo = new PlatformsVo(entity.getPlatformsEntity());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PlatformsVo getPlatformsVo() {
        return platformsVo;
    }

    public void setPlatformsVo(PlatformsVo platformsVo) {
        this.platformsVo = platformsVo;
    }
}
