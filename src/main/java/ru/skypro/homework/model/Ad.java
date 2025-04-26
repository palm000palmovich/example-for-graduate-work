package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

//    @OneToMany(mappedBy = "ads")
//    private Collection<Comment> commentsList;
}
