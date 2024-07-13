package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.services.IUserService;

public class DeletePlaylistCommand implements ICommand {
private IUserService userService;
 
    public DeletePlaylistCommand(IUserService userService) {
    this.userService = userService;
}

    @Override
    public void execute(List<String> tokens) {
        String UserId = tokens.get(1);
        String playlistId = tokens.get(2);
        // TODO Auto-generated method stub
        String result = userService.deletePlaylist(UserId, playlistId);
        System.out.println(result);
    }
    
}