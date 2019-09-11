package com.music.musicproject.service;

import com.music.musicproject.entity.Songs;
import com.music.musicproject.repository.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SongsService {

    @Autowired
    private SongsRepository songsRepository;

    @Transactional(readOnly = true)
    public Songs findBySongId(Integer id) {
        return songsRepository.findId(id);
    }

    @Transactional(readOnly = true)
    public Songs findBySongName(String songName) {
        return songsRepository.findBySongName(songName);
    }

    @Transactional(readOnly = true)
    public List<Songs> findByPartSongs(String name){
        return songsRepository.findPartSongs(name);
    }

    @Transactional(readOnly = true)
    public List<Songs> findBySongsArtist(String artistName) {
        return songsRepository.findSongsByArtistName(artistName);
    }

}
