package uk.co.codeleap.careers.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity()
@Table(name = "POSTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


}
