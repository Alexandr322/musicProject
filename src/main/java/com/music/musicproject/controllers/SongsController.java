package com.music.musicproject.controllers;

import com.music.musicproject.entity.Songs;
import com.music.musicproject.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SongsController {

    @Autowired
    private SongsService songsService;

    @RequestMapping(value = "/song/{id}")
    private Songs getBySongId(@PathVariable ("id")Integer id)  {
        return songsService.findBySongId(id);
    }

    @RequestMapping(value = "/songName/{name}")
    private Songs getBySongName(@PathVariable("name")String name){
        return songsService.findBySongName(name);
    }

    @RequestMapping(value = "/songspart/{name}")
    private List<Songs> getPartSongs(@PathVariable("name")String name){
        List<Songs> songs = songsService.findByPartSongs(name);
        return songs.stream().filter(songs1 -> name.contains(name)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/songs/{artistName}")
    private  List<Songs> getSongsArtist(@PathVariable ("artistName") String artistName){
        return songsService.findBySongsArtist(artistName);
    }

}
