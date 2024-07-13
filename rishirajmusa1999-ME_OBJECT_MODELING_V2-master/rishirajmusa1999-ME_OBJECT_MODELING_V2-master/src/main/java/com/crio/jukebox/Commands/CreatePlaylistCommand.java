package com.crio.jukebox.Commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{
private IPlaylistService playlistService;
    public CreatePlaylistCommand(IPlaylistService playlistService) {
    this.playlistService = playlistService;
}
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String UserId = tokens.get(1);
        String playlistName =tokens.get(2);
        List<String> songList = new ArrayList<>();
        for(int i=3;i<tokens.size();i++){
            songList.add(tokens.get(i));
        }
        Playlist playlist = playlistService.create(UserId, playlistName, songList);
        System.out.println(playlist);
    }
    
}
