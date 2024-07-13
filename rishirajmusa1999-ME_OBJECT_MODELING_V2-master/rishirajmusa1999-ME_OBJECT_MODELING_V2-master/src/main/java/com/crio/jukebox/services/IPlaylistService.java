package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.SongSummaryDTO;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.InvalidPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;

public interface IPlaylistService {
    public Playlist create(String UserId, String playlistName, List<String> songList) throws UserNotFoundException, PlaylistNotFoundException;
    public List<Playlist> getAllPlaylists(String UserId);
    public SongSummaryDTO runPlaylist( String UserId,String playlistID) throws PlaylistNotFoundException, SongNotFoundException, UserNotFoundException;
    public SongSummaryDTO playNextSong(String UserId) throws UserNotFoundException,PlaylistNotFoundException;
    public SongSummaryDTO playPreviousSong(String UserId)throws UserNotFoundException,PlaylistNotFoundException;
    public SongSummaryDTO playSpecificSong(String UserId, String SongId) throws UserNotFoundException,PlaylistNotFoundException;
}
