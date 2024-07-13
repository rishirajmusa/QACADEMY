package com.crio.jukebox.Commands;

import static org.mockito.ArgumentMatchers.anyList;
import java.util.ArrayList;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Command invoker test")
@ExtendWith(MockitoExtension.class)
public class CommandInvkerTest {
    private  CommandInvoker commandInvoker;
    @Mock
    CreatePlaylistCommand createPlaylistCommandMock;

    @Mock
    CreateUserCommand createUserCommandMock;

    @Mock
    DeletePlaylistCommand deletePlaylistCommandMock;

    @Mock
    ModifyPlaylistCommand modifyPlaylistCommandMock;

    @Mock
    PlayPlaylistCommand playPlaylistCommandMock;

    @Mock
    PlaySongCommand playSongCommandMock;

    @BeforeEach
    public void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.register("PLAY-SONG", playSongCommandMock);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommandMock);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommandMock);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommandMock);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommandMock);
        commandInvoker.register("CREATE-USER", createUserCommandMock);
    }


    @Test
    @DisplayName("Command Invoker test")
    public void CommandInvoker_ShouldInvokeCommand(){
        commandInvoker.executeCommand("CREATE-USER", anyList());
        commandInvoker.executeCommand("CREATE-PLAYLIST", anyList());
        commandInvoker.executeCommand("DELETE-PLAYLIST", anyList());
        commandInvoker.executeCommand("MODIFY-PLAYLIST", anyList());
        commandInvoker.executeCommand("PLAY-PLAYLIST", anyList());
        commandInvoker.executeCommand("PLAY-SONG", anyList());
    }

    @Test
    @DisplayName("Command not found should throw error")
    public void commandNotFound_SHouldThrowError(){
        Assertions.assertThrows(NoSuchCommandException.class, ()-> commandInvoker.executeCommand("commandName", new ArrayList<String>()));
    }
}
