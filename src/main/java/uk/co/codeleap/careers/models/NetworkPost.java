package uk.co.codeleap.careers.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity()
@Table(name = "POSTS")

public class NetworkPost implements Serializable {
    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private LocalDateTime createdDatetime;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @PrePersist
    protected void onCreate() {
        this.createdDatetime = LocalDateTime.now();
    }

    public NetworkPost() {
    }

    public NetworkPost(Integer id, String userName, LocalDateTime createdDatetime, String title, String content) {
        this.id = id;
        this.userName = userName;
        this.createdDatetime = createdDatetime;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
