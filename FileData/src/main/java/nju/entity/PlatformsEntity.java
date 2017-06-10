package nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017-06-10.
 *
 */
@Entity
@Table(name = "platforms")
public class PlatformsEntity {
    private int id;
    private String platform;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformsEntity that = (PlatformsEntity) o;

        if (id != that.id) return false;
        if (platform != null ? !platform.equals(that.platform) : that.platform != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }
}
