package com.example.demo.song.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.song.domain.Song;
import com.example.demo.song.domain.SongRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public ResponseEntity<List<Song>> songs() {
        List<Song> songs = songRepository.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> song(@RequestBody Song song) {
        songRepository.save(song);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()) {
            Song existingSong = optionalSong.get();
            existingSong.setTitle(song.getTitle());
            existingSong.setArtist(song.getArtist());
            existingSong.setAlbum(song.getAlbum());
            existingSong.setReleaseDate(song.getReleaseDate());
            existingSong.setGenre(song.getGenre());
            existingSong.setDuration(song.getDuration());
            existingSong.setCoverImage(song.getCoverImage());
            existingSong.setSpotifyUrl(song.getSpotifyUrl());
            songRepository.save(existingSong);
            return ResponseEntity.status(200).body("Updated");
        } else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()) {
            songRepository.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        } else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @PatchMapping("/{id}")
public ResponseEntity<String> patchSong(@PathVariable Long id, @RequestBody Song partialSong) {
    Optional<Song> optionalSong = songRepository.findById(id);

    if (optionalSong.isPresent()) {
        Song existingSong = optionalSong.get();

        if (partialSong.getTitle() != null) {
            existingSong.setTitle(partialSong.getTitle());
        }
        if (partialSong.getArtist() != null) {
            existingSong.setArtist(partialSong.getArtist());
        }
        if (partialSong.getAlbum() != null) {
            existingSong.setAlbum(partialSong.getAlbum());
        }
        if (partialSong.getReleaseDate() != null) {
            existingSong.setReleaseDate(partialSong.getReleaseDate());
        }
        if (partialSong.getGenre() != null) {
            existingSong.setGenre(partialSong.getGenre());
        }
        if (partialSong.getDuration() != null) {
            existingSong.setDuration(partialSong.getDuration());
        }
        if (partialSong.getCoverImage() != null) {
            existingSong.setCoverImage(partialSong.getCoverImage());
        }
        if (partialSong.getSpotifyUrl() != null) {
            existingSong.setSpotifyUrl(partialSong.getSpotifyUrl());
        }

        songRepository.save(existingSong);
        return ResponseEntity.status(200).body("Updated");
    } else {
        return ResponseEntity.status(404).body("Not Found");
    }
}

}
