package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Текст комментария не может быть пустым")
    @Size(min = 1, max = 500, message = "Текст комментария должен содержать от 1 до 500 символов")
    @Column(nullable = false, length = 500)
    private String text;
    @Column(nullable = false)
    private Instant createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    @JsonBackReference
    private Ad ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private User user;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    public Comment(Integer id, String text, Instant createdAt, Ad ad, User user) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.ad = ad;
        this.user = user;
    }

    public Comment(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public User getAuthor() {
        return user;
    }

    public void setAuthor(User author) {
        this.user = author;
    }
}