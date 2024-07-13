package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.dtos.SongSummaryDTO;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {
IPlaylistService playlistService;
    public PlayPlaylistCommand(IPlaylistService playlistService) {
    this.playlistService = playlistService;
}
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String playlistID = tokens.get(2);
        String UserId = tokens.get(1);
        SongSummaryDTO dto =  playlistService.runPlaylist(UserId, playlistID);
        System.out.println(dto);
    }
    
}