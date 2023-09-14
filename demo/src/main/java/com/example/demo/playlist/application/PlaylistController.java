package com.example.demo.playlist.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.playlist.domain.Playlist;
import com.example.demo.playlist.domain.PlaylistRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    // Obtener todas las playlists
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // Crear una nueva playlist
    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    // Obtener una playlist por ID
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable(value = "id") Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            return ResponseEntity.ok().body(playlist.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar una playlist
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable(value = "id") Long playlistId, @RequestBody Playlist playlistDetails) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            Playlist updatedPlaylist = playlist.get();
            updatedPlaylist.setTitle(playlistDetails.getTitle());
            updatedPlaylist.setSongs(playlistDetails.getSongs());
            updatedPlaylist.setCoverImage(playlistDetails.getCoverImage());
            playlistRepository.save(updatedPlaylist);
            return ResponseEntity.ok(updatedPlaylist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una playlist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable(value = "id") Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            playlistRepository.delete(playlist.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
