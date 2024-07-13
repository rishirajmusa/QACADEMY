package com.crio.jukebox.Commands;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.jukebox.dtos.SongSummaryDTO;
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

@DisplayName("Play Playlist Should play Playlist")
@ExtendWith(MockitoExtension.class)
public class PlayPlaylistCommandTest {
    private final PrintStream standardout= System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Mock
    IPlaylistService playlistServicemock;

    @InjectMocks
    PlayPlaylistCommand playPlaylistCommand;

    @Test
    @DisplayName("Play Playlist Command Should Play Playlist")
    public void playPlaylist_ShouldPlayPlaylist(){
        List<String> tokens;
        tokens = List.of("PLAY-PLAYLIST", "1", "1");
        SongSummaryDTO dto = new SongSummaryDTO("songName", "albumName", "artistName");
        when(playlistServicemock.runPlaylist(anyString(), anyString())).thenReturn(dto);
        playPlaylistCommand.execute(tokens);
        Assertions.assertEquals(dto.toString(), outputStreamCaptor.toString().trim());;
        verify(playlistServicemock,times(1)).runPlaylist(anyString(), anyString());
    }



    @AfterEach
    public void tearDown(){
        System.setOut(standardout);
    }
}