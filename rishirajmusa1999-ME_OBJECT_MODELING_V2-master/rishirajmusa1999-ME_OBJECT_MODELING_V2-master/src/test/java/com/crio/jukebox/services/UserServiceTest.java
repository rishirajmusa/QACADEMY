package com.crio.jukebox.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;

@DisplayName("User Service test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository userRepositorymock;

    @Mock
    private IPlaylistRepository playlistRepositorymock;
    
    @Mock
    private ISongRepository songRepositorymock;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("Create should create user")
    public void create_ShouldCreateUser(){
        User expectedUser= new User("1", "name");
        when(userRepositorymock.save(any(User.class))).thenReturn(expectedUser);
        User actualUser = userService.create("name");
        assertEquals(expectedUser, actualUser);
    }


    @Test
    @DisplayName("FindAll should return all users")
    public void findAll_ShouldReturnAllUsers(){
        User u1= new User("1","name");
        User u2= new User("2","name2");
        List<User> expectedUser= new ArrayList<User>(){
            {
                add(u1);
                add(u2);
            }
        };
        when(userRepositorymock.findAll()).thenReturn(expectedUser);
        List<User> actualUser = userService.getAllUsers();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Add Song should Add song to playlist")
    public void addSongToPlaylist_ShouldAddSong(){
        Artist a = new Artist("john doe");
        Album album = new Album("album ");
        Song asong = new Song("1", "name", new SongOwner(a),album);
        List<Song> songs=new ArrayList<Song>(){
            {
                add(asong);
            }
        };
        
        Playlist playlist= new Playlist("1", "Playlist Name" , songs);
        User u1= new User("1", "name");
        u1.addPlaylist(playlist);
        List<String> songids=new ArrayList<String>(){
            {
                add("1");
            }
        };
        when(userRepositorymock.findById(anyString())).thenReturn(Optional.of(u1));
        when(playlistRepositorymock.findById(anyString())).thenReturn(Optional.of(playlist));
        when(songRepositorymock.findById(anyString())).thenReturn(Optional.of(asong));
        PlaylistSummaryDTO actualUserplaylistSummaryDTO= 
        userService.modifyPlaylistAddSong("1", "1", songids);
        PlaylistSummaryDTO expectedUserplaylistSummaryDTO = new PlaylistSummaryDTO(playlist, songids);
        assertEquals(expectedUserplaylistSummaryDTO.toString(), actualUserplaylistSummaryDTO.toString());
        verify(userRepositorymock,times(1)).findById(anyString());
        verify(playlistRepositorymock, times(1)).findById(anyString());
        verify(songRepositorymock,times(1)).findById(anyString());
    }


    @Test
    @DisplayName("Delete Song should Delete song From playlist")
    public void deleteSongToPlaylist_ShouldDeleteSong(){
        Artist a = new Artist("john doe");
        Album album = new Album("album ");
        Song asong = new Song("1", "name", new SongOwner(a),album);
        Song bsong = new Song("2", "name", new SongOwner(a),album);
        List<Song> songs=new ArrayList<Song>(){
            {
                add(asong);
                add(bsong);
            }
        };
        
        Playlist playlist= new Playlist("1", "Playlist Name" , songs);
        
        User u1= new User("1", "name");
        u1.addPlaylist(playlist);
        List<String> songids=new ArrayList<String>(){
            {
                add("2");
            }
        };
        List<String> songid=new ArrayList<String>(){
            {
                add("1");
            }
        };
        when(userRepositorymock.findById(anyString())).thenReturn(Optional.of(u1));
        when(playlistRepositorymock.findById(anyString())).thenReturn(Optional.of(playlist));
        when(playlistRepositorymock.save(any(Playlist.class))).thenReturn(playlist);
        when(songRepositorymock.findById(anyString())).thenReturn(Optional.of(bsong));
        PlaylistSummaryDTO actualUserplaylistSummaryDTO= 
        userService.modifyPlaylistDeleteSong("1", "1", songids); 
        PlaylistSummaryDTO expectedUserplaylistSummaryDTO = new PlaylistSummaryDTO(playlist, songid);
        assertEquals(expectedUserplaylistSummaryDTO.toString(), actualUserplaylistSummaryDTO.toString());
        verify(userRepositorymock,times(1)).findById(anyString());
        verify(playlistRepositorymock, times(1)).findById(anyString());
        verify(songRepositorymock,times(1)).findById(anyString());
    }

    @Test
    @DisplayName("Delete Playlist Should Delete Playlist")
    public void deletePlaylist_ShouldDeletePlaylist(){
        Artist a = new Artist("john doe");
        Album album = new Album("album ");
        Song asong = new Song("1", "name", new SongOwner(a),album);
        Song bsong = new Song("2", "name", new SongOwner(a),album);
        List<Song> songs=new ArrayList<Song>(){
            {
                add(asong);
                add(bsong);
            }
        };
        
        Playlist playlist= new Playlist("1", "Playlist Name" , songs);
        Playlist playlist2= new Playlist("2", "Playlist Name 2" , songs);
        User u1= new User("1", "name");
        u1.addPlaylist(playlist);
        u1.addPlaylist(playlist2);
        when(userRepositorymock.findById(anyString())).thenReturn(Optional.of(u1));
        when(playlistRepositorymock.findById(anyString())).thenReturn(Optional.of(playlist));
        String actualResult = userService.deletePlaylist(u1.getId(), playlist.getId());
        String expectedResult = "Delete Successful";
        Assertions.assertEquals(expectedResult, actualResult);
        verify(userRepositorymock,times(1)).findById(anyString());
        verify(playlistRepositorymock,times(1)).findById(anyString());
    }
}
