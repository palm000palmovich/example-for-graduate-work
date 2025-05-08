package ru.skypro.homework.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "ad_data")
public class AdImage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "mediatype")
    private String mediatype;

    @Lob
    @Type(type = "binary")
    private byte[] data;

    @OneToOne
    private Ad ad;

    public AdImage(Integer id, String filePath, long fileSize, String mediatype, byte[] data, Ad ad) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediatype = mediatype;
        this.data = data;
        this.ad = ad;
    }

    public AdImage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "AdImage{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediatype='" + mediatype + '\'' +
                ", data=" + Arrays.toString(data) +
                ", ad=" + ad +
                '}';
    }
}
