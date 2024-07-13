package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.dtos.SongSummaryDTO;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand{
    IPlaylistService playlistService;
    public PlaySongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String UserId = tokens.get(1);
        String playBack = tokens.get(2);
        SongSummaryDTO ssdto;
        try {
            switch(playBack){
                case "NEXT": 
                ssdto=playlistService.playNextSong(UserId);
                break;
                case "BACK": 
                ssdto=playlistService.playPreviousSong(UserId);
                break;
                default: 
                ssdto=playlistService.playSpecificSong(UserId, playBack);
                break;
            };
            System.out.println(ssdto);
        } catch (Exception e) {
            //TODO: handle exception
        }
       
       
    }
    
}
// PLAY-SONG 1 NEXT
// PLAY-SONG 1 BACK