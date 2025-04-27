package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ad_title", nullable = false)
    @Size(max = 23)
    private String title;
    @Column(name = "ad_description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "image_path")
    private String image;

    @OneToMany(mappedBy = "ad")
    private List<Comment> commentsList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @JsonBackReference
    private User user;
}