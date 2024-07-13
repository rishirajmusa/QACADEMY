package com.crio.jukebox.entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Test")
public class UserTest {
    private List<Playlist> playlists;
    private List<Song> songs;
    @BeforeEach
        void setup(){
            final Artist artist= new Artist("1","Artist 1");
            Album album = new Album("new Album");
            final List<Artist> lisArtists = new ArrayList<Artist>(){
                {
                    add(new Artist("1 A", "AG"));
                    add(new Artist("1 B", "AG"));
                }
            };
            songs= new ArrayList<Song>() {
                {
                    add(new Song("1", "Song 1",new SongOwner("1", artist), album));
                    add(new Song("2", "Song 2", new SongOwner("2",new ArtistGroup("1", "Band", lisArtists)),album));
                }
            };
            Playlist playlist=new Playlist("P1", "P1 Playlist", songs);
            Playlist playlist2=new Playlist("P2", "P2 Playlist", songs);
            playlists = new ArrayList<>(Arrays.asList(playlist,playlist2));
        }


@Test
@DisplayName("Add Playlist should Add Playlist")
public void addPlaylist_ShouldAddPlaylist(){
    User user =new User("U1", "User 1", playlists);
    user.addPlaylist(playlists.get(0));
    playlists.add(playlists.get(0));
    Assertions.assertEquals(playlists,user.getPlaylists() );
}

@Test
@DisplayName("Delete Playlist should Delete Playlist")
public void deletePlaylist_ShouldDeletePlaylist(){
    User user =new User("U1", "User 1", playlists);
    user.deletePlaylist(playlists.get(0));
    playlists.remove(0);
    Assertions.assertEquals(playlists,user.getPlaylists() );
}

@Test
@DisplayName("Delete Playlist if Playlist exists")
public void deletePlaylistIfPlaylistExists_ShouldDeletePlaylist(){
    User user =new User("U1", "User 1", playlists);
    Playlist playlist= new Playlist("id", "name", songs);
    user.deletePlaylist(playlist);
    Assertions.assertEquals(playlists,user.getPlaylists() );
}

@Test
@DisplayName("Check Playlist must return true if Playlist exists")
public void checkIfPlaylistExists_ShouldReturnTrueIfPlaylistExist(){
    User user =new User("U1", "User 1", playlists);
    Playlist playlist= new Playlist("id", "name", songs);
    Boolean Actual = user.checkIfPlaylistExist(playlist);
    Boolean Actual2= user.checkIfPlaylistExist(playlists.get(0));
    Assertions.assertEquals(false,Actual );
    Assertions.assertEquals(true,Actual2 );
}

@Test
@DisplayName("Return Song Owner")
public void getSongOwner_shouldReturnSongOwner(){
    Song song = new Song(songs.get(1));
}


}
