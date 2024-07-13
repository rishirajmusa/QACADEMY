package com.crio.jukebox.Commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IUserService;

public class ModifyPlaylistCommand implements ICommand {
    private IUserService userService;

    public ModifyPlaylistCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songids = new ArrayList<>();
        for(int i=4;i<tokens.size();i++){
            songids.add(tokens.get(i));
        }
        String addcommand="ADD-SONG";
        String deleteCommand="DELETE-SONG";
        // TODO Auto-generated method stub
        if(tokens.get(1).equals(addcommand)){
            PlaylistSummaryDTO dto =  userService.modifyPlaylistAddSong(userId, playlistId, songids);
            System.out.println(dto.toString());
        }
        if(tokens.get(1).equals(deleteCommand)){
            PlaylistSummaryDTO dto = userService.modifyPlaylistDeleteSong(userId, playlistId, songids);
            System.out.println(dto.toString());
        }
        
    }



}
