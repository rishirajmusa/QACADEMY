package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IUserService {
    public User create(String name);
    public List<User> getAllUsers();
    public String deletePlaylist(String UserId, String playlistId);
    public PlaylistSummaryDTO modifyPlaylistAddSong(String userId, String playlistId, List<String> songids);
    public PlaylistSummaryDTO modifyPlaylistDeleteSong(String userId, String playlistId, List<String> songids);
}
