package com.crio.jukebox.Commands;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.dtos.SongSummaryDTO;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.exceptions.SongNotFoundException;
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

@DisplayName("Play Song Command Test")
@ExtendWith(MockitoExtension.class)
public class PlaySongCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    IPlaylistService playlistServicemock;

    @InjectMocks
    PlaySongCommand playSongCommand;

    @BeforeEach
    public void setup(){
       System.setOut(new PrintStream(outputStreamCaptor));
        
    }
    @Test
    @DisplayName("Play Next Song Command Must Play Song")
    public void playNextSong_ShouldPlaySong(){
        List<String> tokens;
        tokens = List.of("PLAY-SONG", "1", "NEXT");
        SongSummaryDTO dto = new SongSummaryDTO("Song name", "album", "Artist name");
        when(playlistServicemock.playNextSong(anyString())).thenReturn(dto);
        playSongCommand.execute(tokens);
        Assertions.assertEquals(dto.toString(), outputStreamCaptor.toString().trim());
        verify(playlistServicemock,times(1)).playNextSong(anyString());
    }

    @Test
    @DisplayName("Play Previous Song Command Must Play Song")
    public void playPreviousSong_ShouldPlaySong(){
        List<String> tokens;
        tokens = List.of("PLAY-SONG", "1", "BACK");
        SongSummaryDTO dto = new SongSummaryDTO("Song name", "album", "Artist name");
        when(playlistServicemock.playPreviousSong(anyString())).thenReturn(dto);
        playSongCommand.execute(tokens);
        Assertions.assertEquals(dto.toString(), outputStreamCaptor.toString().trim());
        verify(playlistServicemock,times(1)).playPreviousSong(anyString());
    }

    @Test
    @DisplayName("Play Specific Song Command Must Play that Song")
    public void playSpecificSong_ShouldPlaySong(){
        List<String> tokens;
        tokens = List.of("PLAY-SONG", "1", "5");
        SongSummaryDTO dto = new SongSummaryDTO("Song name", "album", "Artist name");
        when(playlistServicemock.playSpecificSong(anyString(), anyString())).thenReturn(dto);
        playSongCommand.execute(tokens);
        Assertions.assertEquals(dto.toString(), outputStreamCaptor.toString().trim());
        verify(playlistServicemock,times(1)).playSpecificSong(anyString(),anyString());
    }

    @Test
    @DisplayName("Play Specific Song Command Must throw error if song not found")
    public void playSpecificSong_ShouldThrowError(){
        List<String> tokens;
        tokens = List.of("PLAY-SONG", "1", "5");
        doThrow(new SongNotFoundException("Song Not Found in the current active playlist."))
        .when(playlistServicemock).playSpecificSong(anyString(), anyString());
        Assertions.assertThrows(SongNotFoundException.class, ()-> playSongCommand.execute(tokens));
        verify(playlistServicemock,times(1)).playSpecificSong(anyString(),anyString());
    }
    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

}
