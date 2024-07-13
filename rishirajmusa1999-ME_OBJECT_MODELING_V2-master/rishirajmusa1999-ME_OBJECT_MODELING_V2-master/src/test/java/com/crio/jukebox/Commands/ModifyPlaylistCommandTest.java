package com.crio.jukebox.Commands;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.services.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Modify Playlist Test")
@ExtendWith(MockitoExtension.class)
public class ModifyPlaylistCommandTest {
    private final PrintStream standardout = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }



    @Mock
    IUserService userServicemock;

    @InjectMocks
    ModifyPlaylistCommand modifyPlaylistCommand;



    @Test
    @DisplayName("Modify playlist commnand should call the respective methods")
    public void ModifyPlaylistCommand_ShouldCallResMethods(){
        List<String> songids = List.of("7");
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album ");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("4", "Song name", new SongOwner(a), album);
        Song csong= new Song("5", "Song name", new SongOwner(a), album);        
        Song dsong= new Song("6", "Song name", new SongOwner(a), album);      
        Song esong= new Song("7", "Song name", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong,csong,dsong,esong);
        Playlist playlist = new Playlist("1", "MY_PLAYLIST_1", songs);
        PlaylistSummaryDTO dtoExpected = new PlaylistSummaryDTO(playlist, playlist.getSongIds());
        when(userServicemock.modifyPlaylistAddSong(anyString(), anyString(), anyList())).thenReturn(dtoExpected);
        List<String> tokens = List.of("MODIFY-PLAYLIST", "ADD-SONG", "1", "1", "7");
         modifyPlaylistCommand.execute(tokens);
         Assertions.assertEquals(dtoExpected.toString(), outputStreamCaptor.toString().trim());
        verify(userServicemock,times(1)).modifyPlaylistAddSong(anyString(), anyString(), anyList());
    }
    @Test
    @DisplayName("Modify playlist commnand should call the respective methods")
    public void ModifyPlaylistCommand_ShouldCallRespMethods(){
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album ");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("4", "Song name", new SongOwner(a), album);
        Song csong= new Song("5", "Song name", new SongOwner(a), album);        
        Song dsong= new Song("6", "Song name", new SongOwner(a), album);      
        Song esong= new Song("7", "Song name", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong,csong,dsong,esong);
        Playlist playlist = new Playlist("1", "MY_PLAYLIST_1", songs);
        PlaylistSummaryDTO dtoExpected = new PlaylistSummaryDTO(playlist, playlist.getSongIds());
        when(userServicemock.modifyPlaylistDeleteSong(anyString(), anyString(), anyList())).thenReturn(dtoExpected);
        List<String> tokens = List.of("MODIFY-PLAYLIST", "DELETE-SONG", "1", "1", "7");
         modifyPlaylistCommand.execute(tokens);
         Assertions.assertEquals(dtoExpected.toString(), outputStreamCaptor.toString().trim());
         verify(userServicemock,times(1)).modifyPlaylistDeleteSong(anyString(), anyString(), anyList());
    
    }
    @AfterEach
    public void tearDown(){
        System.setOut(standardout);
    }
}
