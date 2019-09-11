package com.music.musicproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="artist")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="artist_name", length = 2000)
    private String artistName;

    @JsonIgnore
    @OneToMany(mappedBy = "artist" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Songs> songs;

    @Column(name = "country",length = 2000)
    private String country;


    public Artist(String artistName, List<Songs> songs, String country) {
        this.artistName = artistName;
        this.songs = songs;
        this.country = country;
    }
}
