package com.example.demo.playlist.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.song.domain.Song;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "playlist_song",
            joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )
    private List<Song> songs = new ArrayList<>();  // Inicializado para evitar NullPointerException

    private String coverImage;

    public Playlist() {}

    public Playlist(Long id, String title, List<Song> songs, String coverImage) {
        this.id = id;
        this.title = title;
        this.songs = songs;
        this.coverImage = coverImage;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        if (songs == null) {
            throw new IllegalArgumentException("Songs list cannot be null");
        }
        this.songs = songs;
    }

    public String getCoverImage() {
        return coverImage;
    }

  public void setCoverImage(String coverImage) {
    if (coverImage == null || coverImage.trim().isEmpty()) {
        throw new IllegalArgumentException("Cover image cannot be null or empty");
    }
    this.coverImage = coverImage;
}


    // toString
    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", songs=" + songs +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return getId().equals(playlist.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
