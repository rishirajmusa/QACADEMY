package com.crio.jukebox.Commands;

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

@DisplayName("Delete Playlist Command")
@ExtendWith(MockitoExtension.class)
public class DeletePlaylistCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamcaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamcaptor));
    }

    @Mock
    IUserService userServicemock;

    @InjectMocks
    DeletePlaylistCommand deletePlaylistCommand;


    @Test
    @DisplayName("Delete playlist command Should print Result")
    public void deletePlaylistCommand_ShouldPrintResult(){
        List<String> tokens = List.of("DELETE-PLAYLIST", "1", "2");
  
        when(userServicemock.deletePlaylist("1", "2")).thenReturn("Delete Successful");
        deletePlaylistCommand.execute(tokens);
        String expected ="Delete Successful";

        Assertions.assertEquals(expected, outputStreamcaptor.toString().trim());
        verify(userServicemock,times(1)).deletePlaylist("1", "2");
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }
}
// DELETE-PLAYLIST 1 2