package com.crio.jukebox.dtos;

import java.util.List;
import java.util.StringJoiner;
import com.crio.jukebox.entities.Playlist;

public class PlaylistSummaryDTO {
    private  Playlist playlist;
private  List<String> ids;

    public PlaylistSummaryDTO() {}


    public PlaylistSummaryDTO(Playlist playlist, List<String> SongIds) {
        this.ids = SongIds;
    this.playlist=playlist;
    }


    public Playlist getPlaylist() {
        return playlist;
    }



    @Override
    public String toString() {
        return "Playlist ID - " + playlist.getId()+"\n"
        + "Playlist Name - " + playlist.getName() +"\n"
        + "Song IDs - " + ids.stream().collect(()-> new StringJoiner(" "),
                                                StringJoiner::add,
                                                StringJoiner::merge).toString() ;
    }




    
}
