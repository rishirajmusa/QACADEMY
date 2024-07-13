package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;

public interface IsongService {
    public Song create(String name, SongOwner owner, Album album);
    public List<Song> getAllSongs();
}
