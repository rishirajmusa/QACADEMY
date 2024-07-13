package com.crio.jukebox.repositories;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.ArtistGroup;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("PlayList Repository Test")
public class PlaylistRepositoryTest {
    
    private PlaylistRepository playlistRepository;
    Map<String, Playlist> userMap;
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
            add(new Song("1", "Song 1",new SongOwner("1", artist), album));
            add(new Song("2", "Song 2", new SongOwner("2",new ArtistGroup("1", "Band", lisArtists)),album));
        }
    };
        userMap =new HashMap<String, Playlist>() {
        {
            put("1", new Playlist("1", "Playlist1", songs));
            put("2", new Playlist("2", "Playlist2", songs));
        }
        };
        playlistRepository=new PlaylistRepository(userMap);
    }

    @Test
    @DisplayName("Save method should create and save Playlist")
    public void saveMethod_ShouldSavePlaylist(){
        final Playlist p1= new Playlist("Playlist 3",songs);
        Playlist expectedPlaylist = new Playlist("3", "Playlist 3", songs);
        Playlist actualPlaylist = playlistRepository.save(p1);
        Assertions.assertEquals(expectedPlaylist,actualPlaylist);
    }


    @Test
    @DisplayName("findAll method should return All Playlists")
    public void findAllPlaylists(){
        //Arrange
        int expectedCount = 2;
        //Act
        List<Playlist> aPlaylists = playlistRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,aPlaylists.size());
    }

    @Test
    @DisplayName("findAll method should return Empty List if no Playlists Found")
    public void findAllPlaylists_ShouldReturnEmptyList(){
        //Arrange
        int expectedCount = 0;
        PlaylistRepository emptyplaylistRepository = new PlaylistRepository();
        //Act
        List<Playlist> p1 = emptyplaylistRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,p1.size());
    }


    @Test
    @DisplayName("findById method should return Playlist Given Id")
    public void findById_ShouldReturnPlaylist_GivenPlaylistId(){
        //Arrange
        String expectedPlaylistId = "2";
        //Act
        Optional<Playlist> actualPlaylist = playlistRepository.findById(expectedPlaylistId);
        //Assert
        //System.out.println(actualPlaylist);
        Assertions.assertEquals(expectedPlaylistId,actualPlaylist.get().getId());
    }

    @Test
    @DisplayName("findById method should return empty if Playlist Not Found")
    public void findById_ShouldReturnEmptyIfPlaylistNotFound(){
       //Arrange
       Optional<Playlist> expectedPlaylist =Optional.empty();
       //Act
       Optional<Playlist> actualPlaylist = playlistRepository.findById("3");
       //Assert
       Assertions.assertEquals(expectedPlaylist,actualPlaylist);
    }

    @Test
    @DisplayName("existById method should return Playlist status Given Id")
    public void existById_ShouldReturnPlaylistStatus_GivenPlaylistId(){
        //Arrange
        boolean expected = true;
        //Act
       boolean actual = playlistRepository.existsById("2");
        //Assert
        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("returnById method should return Playlist Given Id")
    public void returnById_ShouldReturnPlaylist_GivenPlaylistId(){
        //Arrange
        Playlist expectedPlaylist=new Playlist(userMap.get("1"));
        //Act
        Playlist actualPlaylist= playlistRepository.returnById("1");
        //Assert
        Assertions.assertEquals(expectedPlaylist,actualPlaylist);
    }


    @Test
    @DisplayName("delete method should Delete Playlist Given Playlist Object")
    public void delete_ShouldDeletePlaylist_GivenPlaylistObject(){
        //Arrange
        Playlist playlistToBeDeleted=new Playlist(userMap.get("2"));
       playlistRepository.delete(playlistToBeDeleted);
        //Assert
        Assertions.assertEquals(false,playlistRepository.existsById("2"));
    }

    @Test
    @DisplayName("deleteById method should Delete Playlist Given Id")
    public void deleteById_ShouldDeletePlaylist_GivenPlaylistId(){
        //Arrange
       playlistRepository.deleteById("1");
        //Assert
        Assertions.assertEquals(false,playlistRepository.existsById("1"));
    }
}
