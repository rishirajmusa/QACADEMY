package com.crio.jukebox.appConfig;

import com.crio.jukebox.Commands.CommandInvoker;
import com.crio.jukebox.Commands.CreatePlaylistCommand;
import com.crio.jukebox.Commands.CreateUserCommand;
import com.crio.jukebox.Commands.DeletePlaylistCommand;
import com.crio.jukebox.Commands.LoadDataCommand;
import com.crio.jukebox.Commands.ModifyPlaylistCommand;
import com.crio.jukebox.Commands.PlayPlaylistCommand;
import com.crio.jukebox.Commands.PlaySongCommand;
import com.crio.jukebox.entities.UserPlaylists;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.IsongService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    
    private final ISongRepository songRepository = new SongRepository();

    private final IUserRepository userRepository = new UserRepository();

    private final UserPlaylists userPlaylists = new UserPlaylists();

    private final IPlaylistService playlistService = new PlaylistService(playlistRepository, userRepository, songRepository, userPlaylists);

    private final IsongService songservice = new SongService(songRepository);

    private final IUserService userService = new UserService(userRepository, playlistRepository, songRepository);

    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songservice);

    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);

    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(userService);

    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(userService);

    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playlistService);

    private final PlaySongCommand playSongCommand = new PlaySongCommand(playlistService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("LOAD-DATA", loadDataCommand);
        return commandInvoker;
        
    }
}
