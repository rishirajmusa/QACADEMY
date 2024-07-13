package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Playlist Test")

public class PlaylistTest {
    private List<Song> songs;
@BeforeEach
    void setup(){
        final Artist artist= new Artist("1","Artist 1");
        Album album = new Album("New Album");
        final List<Artist> lisArtists = new ArrayList<Artist>(){
            {
                add(new Artist("1 A", "AG"));
                add(new Artist("1 B", "AG"));
            }
        };
        songs= new ArrayList<Song>() {
            {
                add(new Song("1", "Song 1",new SongOwner("1", artist),album));
                add(new Song("2", "Song 2", new SongOwner("2",new ArtistGroup("1", "Band", lisArtists)),album));
            }
        };
    }


@Test
@DisplayName ("Delete song method should delete song")
public void deleteSong_ShouldDeleteSong(){
    String id="1";
    String name= "Name of the Playlist 1";
    Playlist playlist = new Playlist(id, name, songs);
    playlist.deleteSong(songs.get(0));
    Assertions.assertEquals(songs, playlist.getSongs());
}

@Test
@DisplayName ("ADD song method should ADD song")
public void AddSong_ShouldAddSong(){
    String id="1";
    String name= "Name of the Playlist 1";
    Playlist playlist = new Playlist(id, name, songs);
    playlist.addSong(songs.get(1));
    songs.add(songs.get(1));
    Assertions.assertEquals(songs, playlist.getSongs());
}

@Test
@DisplayName ("Get song method should Get song")
public void GetSong_ShouldGetSong(){
    String id="1";
    String name= "Name of the Playlist 1";
    Playlist playlist = new Playlist(id, name, songs);
    Assertions.assertEquals(songs, playlist.getSongs());
}

@Test
@DisplayName ("Current song method should Get Current song")
public void GetCurrentSong_ShouldGetCurrentSong(){
    String id="1";
    String name= "Name of the Playlist 1";
    Playlist playlist = new Playlist(id, name, songs);
    Assertions.assertEquals(songs.get(0), playlist.getCurrentSong());
}

}
