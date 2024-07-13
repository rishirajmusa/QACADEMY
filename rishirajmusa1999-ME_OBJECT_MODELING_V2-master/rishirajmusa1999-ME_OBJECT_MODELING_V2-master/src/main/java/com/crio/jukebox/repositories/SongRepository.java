package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class SongRepository implements ISongRepository {
private final Map<String,Song> userMap;
private Integer autoIncrement= 0;
 
public SongRepository(){
    userMap=new HashMap<String,Song>();
}

    public SongRepository(Map<String, Song> userMap) {
    this.userMap = userMap;
    this.autoIncrement = userMap.size();
}

    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
            Song song=new Song(Integer.toString(autoIncrement), entity.getName(), entity.getOwner(), entity.getAlbum());
            userMap.put(song.getId(), song);
            return song;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        Song song = null;
        for(Map.Entry<String,Song>entry: userMap.entrySet() ){
            Song song1= entry.getValue();
            if(song1.getId().equals(id)){
                song=song1;
                break;
            }
        }
        return Optional.ofNullable(song);
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        Song song = null;
        for(Map.Entry<String,Song>entry: userMap.entrySet() ){
            Song song1= entry.getValue();
            if(song1.getId()==id){
                song=song1;
                return true;
            }
        }
        return false;
    }

    public Song returnById(String id) throws SongNotFoundException {
        // TODO Auto-generated method stub
       Song song =null;
        for(Map.Entry<String,Song>entry: userMap.entrySet() ){
            Song song1 = entry.getValue();
            if(song1.getId()==id){
                song=song1;
              break;
            }
        }
        if(song != null){
            return song;
        }else
        throw new SongNotFoundException("Song not found");
    }


    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        String key;
        for(Map.Entry<String,Song>entry: userMap.entrySet() ){
            if(entry.getValue().equals(entity)){
            key=entry.getKey();
            userMap.remove(key, entity);
            break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        Song song =null;
        for(Map.Entry<String,Song>entry: userMap.entrySet() ){
            Song song1 = entry.getValue();
            if(song1.getId()==id){
                song=song1;
                break;
            }
        }
        String key;
        for(Map.Entry<String,Song>entry: userMap.entrySet() ){
            if(entry.getValue().equals(song)){
            key=entry.getKey();
            userMap.remove(key, song);
            break;
            }
        }
      
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}


