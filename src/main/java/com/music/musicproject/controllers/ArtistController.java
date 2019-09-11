package com.music.musicproject.controllers;

import com.music.musicproject.entity.Artist;
import com.music.musicproject.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArtistController {

    @Autowired
    public ArtistService artistService;

    @RequestMapping(value = "/artists/all")
    private List<Artist> getAllArtists() {
        return artistService.findAll();
    }

    @RequestMapping(value = "/artistspart/{name}")
    private List<Artist> getPartArtist(@PathVariable("name")String name){
        List<Artist> artists = artistService.findByPartArtist(name);
        return artists.stream().filter(artist -> name.contains(name)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/artist/{name}")
    private Artist getOneArtist(@PathVariable("name") String name){
        return artistService.findByArtist(name);
    }

}
