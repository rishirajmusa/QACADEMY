package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements IsongService{
private final ISongRepository songRepository;
    public SongService(ISongRepository songRepository) {
    this.songRepository = songRepository;
}

    @Override
    public Song create(String name, SongOwner owner, Album album) {
        // TODO Auto-generated method stub
        final Song song=new Song(name, owner,album);
        return songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongs() {
        // TODO Auto-generated method stub
        return songRepository.findAll();
    }
    
}