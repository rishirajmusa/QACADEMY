package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;

public class PlaylistRepository implements IPlaylistRepository{

    private final Map<String, Playlist> userMap;
    private Integer autoIncrement=0;
 
    public PlaylistRepository()
    {
        userMap= new HashMap<String,Playlist>();
    }

    public PlaylistRepository( Map<String, Playlist> userMap){
        this.userMap=userMap;
        this.autoIncrement=userMap.size();
    }
    @Override
    public Playlist save(Playlist entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
            Playlist playlist=new Playlist(Integer.toString(autoIncrement),entity.getName(),entity.getSongs());
            userMap.put(playlist.getId(), playlist);
            return playlist;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }



    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Playlist> findById(String id) {
        // TODO Auto-generated method stub
        Playlist playlist = null;
        for(Map.Entry<String,Playlist>entry: userMap.entrySet() ){
            Playlist p1= entry.getValue();
            if(p1.getId().equals(id)){
                playlist=p1;
                break;
            }
        }
        return Optional.ofNullable(playlist);
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        Playlist playlist = null;
        for(Map.Entry<String,Playlist>entry: userMap.entrySet() ){
            Playlist p1= entry.getValue();
            if(p1.getId()==id){
                playlist=p1;
                return true;
            } 
        }
            return false;
    }
      
    public Playlist returnById(String id) throws PlaylistNotFoundException {
        // TODO Auto-generated method stub
        Playlist playlist =null;
        for(Map.Entry<String,Playlist>entry: userMap.entrySet() ){
            Playlist p1 = entry.getValue();
            if(p1.getId()==id){
                playlist=p1;
              break;
            }
        }
        if(playlist != null){
            return playlist;
        }else
        throw new PlaylistNotFoundException("Playlist not found");
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        String key;
        for(Map.Entry<String,Playlist>entry: userMap.entrySet() ){
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
        Playlist playlist =null;
        for(Map.Entry<String,Playlist>entry: userMap.entrySet() ){
            Playlist p1 = entry.getValue();
            if(p1.getId()==id){
                playlist=p1;
                break;
            }
        }
        String key;
        for(Map.Entry<String,Playlist>entry: userMap.entrySet() ){
            if(entry.getValue().equals(playlist)){
            key=entry.getKey();
            userMap.remove(key, playlist);
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
