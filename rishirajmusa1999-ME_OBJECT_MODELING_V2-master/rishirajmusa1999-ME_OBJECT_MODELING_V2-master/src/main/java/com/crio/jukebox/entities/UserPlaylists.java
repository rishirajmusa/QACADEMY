package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;

public class UserPlaylists {
    private final Map<String, Playlist> activePlaylist;

    public UserPlaylists(Map<String, Playlist> activePlaylist) {
        this.activePlaylist = activePlaylist;
    }

    public UserPlaylists() {
        activePlaylist = new HashMap<String, Playlist>();
    }

    public void setActivePlaylist(String UserId,Playlist playlist){
        activePlaylist.put(UserId, playlist);
    }
    public Playlist getActivePlaylist(String UserId) {
        return activePlaylist.get(UserId);
    }
    

    // @Override
    // public String toString() {
    //     return "UserPlaylistSongs [playlistSongsMap=" + playlistSongsMap + "]";
    // }
}
