package com.music.musicproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "songs")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id", nullable = false)
    private Integer song_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @Column(name = "text", length = 65600)
    private String text;

    @Column(name = "song_name", length = 2000)
    private String songName;

    @Column(name = "rating")
    private Double rating;

    public Songs(Artist artist, String text, String songName) {
        this.artist = artist;
        this.text = text;
        this.songName = songName;
    }
}

