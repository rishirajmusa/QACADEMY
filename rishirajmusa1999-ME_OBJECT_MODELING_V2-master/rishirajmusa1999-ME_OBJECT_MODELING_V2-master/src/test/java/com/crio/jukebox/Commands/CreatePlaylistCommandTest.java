package com.crio.jukebox.Commands;

import static org.mockito.ArgumentMatchers.any;
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
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.services.IPlaylistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Create JukeBox Playlist")
@ExtendWith(MockitoExtension.class)
public class CreatePlaylistCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();




    @Mock
    IPlaylistService playlistServicemock;
    @InjectMocks
    CreatePlaylistCommand createPlaylistCommand;

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("create playlist command Should print Playlist")
    public void createPlaylistCommand_ShouldPrintPlaylist(){
        List<String> tokens = List.of("CREATE-PLAYLIST", "1", "MY_PLAYLIST_1", "1", "4", "5", "6");
        Artist a= new Artist("1", "Artist name");
        Album album = new Album("album ");
        Song asong= new Song("1", "Song name", new SongOwner(a), album);        
        Song bsong= new Song("4", "Song name", new SongOwner(a), album);
        Song csong= new Song("5", "Song name", new SongOwner(a), album);        
        Song dsong= new Song("6", "Song name", new SongOwner(a), album);
        List<Song> songs=new ArrayList<>();
        songs= Arrays.asList(asong,bsong,csong,dsong);
        Playlist playlist = new Playlist("1", "MY_PLAYLIST_1", songs);
        when(playlistServicemock.create(anyString(), anyString(), any())).thenReturn(playlist);
        createPlaylistCommand.execute(tokens);
        String expected ="Playlist ID - 1";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
        verify(playlistServicemock,times(1)).create(anyString(), anyString(), anyList());
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
}