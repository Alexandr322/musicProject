package com.music.musicproject.repository;

import com.music.musicproject.entity.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongsRepository extends JpaRepository<Songs,Integer>{

    @Query("SELECT c FROM Songs c WHERE c.song_id=:id")
    Songs findId(@Param("id") Integer id);

    @Query("SELECT c FROM Songs c WHERE c.songName=:name")
    Songs findBySongName(@Param("name")String name);

    @Query("SELECT c FROM Songs c WHERE UPPER(c.songName) LIKE CONCAT('%',:songName,'%')")
    List<Songs> findPartSongs(@Param("songName") String songName);

    @Query("SELECT s FROM Songs s WHERE UPPER(s.artist.artistName) LIKE CONCAT('%',:artistName,'%')")
    List<Songs> findSongsByArtistName(@Param("artistName")String artistName);

}
