package com.sxw.blog.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    private String title;

    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "atlas_id")
    @JsonIgnore
    private Atlas atlas;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Atlas getAtlas() {
        return atlas;
    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createDate=" + createDate +
                ", atlas=" + atlas +
                ", user=" + user +
                '}';
    }
}
