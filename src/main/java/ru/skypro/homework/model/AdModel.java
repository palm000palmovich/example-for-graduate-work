package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
public class AdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ad_title")
    private String title;
    @Column(name = "ad_description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "image_path")
    private String image;

}
