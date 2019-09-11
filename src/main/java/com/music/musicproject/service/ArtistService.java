package com.music.musicproject.service;

import com.music.musicproject.entity.Artist;
import com.music.musicproject.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Artist> findByPartArtist(String name){
        return artistRepository.findPartArtist(name);
    }

    @Transactional(readOnly = true)
    public Artist findByArtist(String name) {
        return artistRepository.findByArtistName(name);
    }

}
