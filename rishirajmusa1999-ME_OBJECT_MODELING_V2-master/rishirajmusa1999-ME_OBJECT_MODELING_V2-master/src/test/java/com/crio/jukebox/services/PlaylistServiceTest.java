package com.crio.jukebox.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.dtos.SongSummaryDTO;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.UserPlaylists;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Playlist Service Test")
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    Map<String,Song> songMap;
     Map<String , User> usermap;
     Map<String, Playlist> activePlaylist;

    @Mock
    private IPlaylistRepository playlistRepositorymock;
    @Mock
    private ISongRepository songRepositoryMock;
    @Mock 
    private IUserRepository userRepositoryMock;
    @Mock
    private UserPlaylists userplaylistMock;
    @InjectMocks
    PlaylistService playlistService;
  
    @Test
    @DisplayName("Create Playlist Should Create Playlist")
    public void create_ShouldCreatePlaylist(){
        List<String> songIds= Arrays.asList("1","2");
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album ");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("1", "Song name", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong);
        Playlist playlist = new Playlist("1", "Playlist name", songs);
        Playlist playlist2 = new Playlist("2","Playlist name 2", songs);
        List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(playlist);
                add(playlist2);
            }
        };
        Playlist expectedPlaylist = new Playlist("1", "Playlist name", songs);
        User u1 = new User("1", "User name", playlists);
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(u1));
        when(songRepositoryMock.findById(anyString())).thenReturn(Optional.of(asong));
        when(playlistRepositorymock.save(any(Playlist.class))).thenReturn(playlist);
        Playlist actuaPlaylist = playlistService.create("1", "Playlist name", songIds);
        Assertions.assertEquals(expectedPlaylist, actuaPlaylist);
        verify(userRepositoryMock,times(1)).findById(anyString());
        verify(playlistRepositorymock,times(1)).save(any(Playlist.class)); 
        verify(songRepositoryMock,times(2)).findById(anyString());
    }

    @Test
    @DisplayName("Get All Playlists Should Return All Playlists")
    public void getAllPlaylists_ShouldReturnAllPlaylists(){
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album ");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("1", "Song name", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong);
        Playlist playlist = new Playlist("1", "Playlist name", songs);
        Playlist playlist2 = new Playlist("2","Playlist name 2", songs);
        List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(playlist);
                add(playlist2);
            }
        };
        User u1 = new User("1", "User name", playlists);
        List<Playlist> expectedPlaylist = playlists;
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(u1));
        List<Playlist> actualPlaylist = playlistService.getAllPlaylists("1");
        Assertions.assertEquals(expectedPlaylist, actualPlaylist);
        verify(userRepositoryMock,times(1)).findById(anyString());
    }


    @Test
    @DisplayName("Run playlist Should Run Playlist")
    public void runPlaylist_ShouldRunPlaylist(){
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("2", "Song name 2 ", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong);
        Playlist playlist = new Playlist("1", "Playlist name", songs);
        Playlist playlist2 = new Playlist("2","Playlist name 2", songs);
        List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(playlist);
                add(playlist2);
            }
        };
        User u1 = new User("1", "User name", playlists);
        SongSummaryDTO expectedSongSummaryDTO = new SongSummaryDTO("Song name", "album", "Artist name");
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(u1));
        when(playlistRepositorymock.findById(anyString())).thenReturn(Optional.of(playlist));
        SongSummaryDTO actualsSongSummaryDTO = playlistService.runPlaylist("1", "1");
        Assertions.assertEquals(expectedSongSummaryDTO.toString(), actualsSongSummaryDTO.toString()); 
        verify(userRepositoryMock,times(1)).findById(anyString());
        verify(playlistRepositorymock,times(1)).findById(anyString());   
    }

    @Test
    @DisplayName("Play next song should play the next Song")
    public void playNextSong_ShouldPlayNextSong(){
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("2", "Song name 2 ", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong);
        Playlist playlist = new Playlist("1", "Playlist name", songs);
        Playlist playlist2 = new Playlist("2","Playlist name 2", songs);
        List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(playlist);
                add(playlist2);
            }
        };
        User u1 = new User("1", "User name", playlists);
        when(userplaylistMock.getActivePlaylist(anyString())).thenReturn(playlist);
      
        playlist.setCurrentSong(asong);
        SongSummaryDTO expectedSongSummaryDTO = new SongSummaryDTO(bsong.getName(), bsong.getAlbum().getName(), bsong.getOwner().toString());

        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(u1));
        SongSummaryDTO actualSongSummaryDTO = playlistService.playNextSong("1");
        Assertions.assertEquals(expectedSongSummaryDTO.toString(), actualSongSummaryDTO.toString());
        verify(userRepositoryMock,times(1)).findById(anyString());
    }
    @Test
    @DisplayName("Play Previous song should play the Previous Song")
    public void playPreviousSong_ShouldPlayPreviousSong(){
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("2", "Song name 2 ", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong);
        Playlist playlist = new Playlist("1", "Playlist name", songs);
        Playlist playlist2 = new Playlist("2","Playlist name 2", songs);
        List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(playlist);
                add(playlist2);
            }
        };
        User u1 = new User("1", "User name", playlists);
        playlist.setCurrentSong(asong);
        SongSummaryDTO expectedSongSummaryDTO = new SongSummaryDTO(bsong.getName(), bsong.getAlbum().getName(), bsong.getOwner().toString());
        when(userplaylistMock.getActivePlaylist(anyString())).thenReturn(playlist);
      
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(u1));
        SongSummaryDTO actualSongSummaryDTO = playlistService.playPreviousSong("1");
        Assertions.assertEquals(expectedSongSummaryDTO.toString(), actualSongSummaryDTO.toString());
        verify(userRepositoryMock,times(1)).findById(anyString());
    }
    @Test
    @DisplayName("Play Specific song should play that Song in the playlist")
    public void playSpecificSong_ShouldPlaySpecificSong(){
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("2", "Song name 2 ", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong);
        Playlist playlist = new Playlist("1", "Playlist name", songs);
        Playlist playlist2 = new Playlist("2","Playlist name 2", songs);
        List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(playlist);
                add(playlist2);
            }
        };
        User u1 = new User("1", "User name", playlists);
        SongSummaryDTO expectedSongSummaryDTO = new SongSummaryDTO(bsong.getName(), bsong.getAlbum().getName(), bsong.getOwner().toString());
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(u1));
        when(songRepositoryMock.findById(anyString())).thenReturn(Optional.of(bsong));
        when(userplaylistMock.getActivePlaylist(anyString())).thenReturn(playlist);
        SongSummaryDTO actualSongSummaryDTO = playlistService.playSpecificSong("1", "2");
        Assertions.assertEquals(expectedSongSummaryDTO.toString(), actualSongSummaryDTO.toString());
        verify(userRepositoryMock,times(1)).findById(anyString());
        verify(songRepositoryMock,times(1)).findById(anyString());
    }
}
