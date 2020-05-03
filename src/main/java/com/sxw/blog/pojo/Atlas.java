package com.sxw.blog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "atlas")
public class Atlas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToMany(mappedBy = "atlas")
    private List<Picture> pictures;

    @Transient
    private int firstPictureID;

    public int getFirstPictureID() {
        return firstPictureID;
    }

    public void setFirstPictureID(int firstPictureID) {
        this.firstPictureID = firstPictureID;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Atlas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
