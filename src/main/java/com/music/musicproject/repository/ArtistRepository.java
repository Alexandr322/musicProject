package com.music.musicproject.repository;

import com.music.musicproject.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {

    @Query("SELECT a FROM Artist a WHERE a.artistName=:artistName")
    Artist findByArtistName(@Param("artistName") String name);

    @Query("SELECT a FROM Artist a WHERE UPPER(a.artistName) LIKE CONCAT('%',:artistName,'%')")
    List<Artist> findPartArtist(@Param("artistName") String name);
}
