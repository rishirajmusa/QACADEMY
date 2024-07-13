package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class User extends BaseEntity {
private List<Playlist> playlists = new ArrayList<>();

    public User(String id, String name){
        this(id, name, new ArrayList<>());
    }
   
    public User(String id, String name, List<Playlist> playlists) {
    this(name, playlists);
    this.id=id;
}
    public User(String name, List<Playlist> playlists) {
    this(name);
    this.playlists = playlists;

}

    public User(String name) {
        this.name=name;
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        playlists.removeIf(p -> p.getId() == playlist.getId());
    }


    public List<Playlist> getPlaylists() {
        List<Playlist> pList = new ArrayList<>();
        if(playlists==null){
            return pList;
        }
        return playlists.stream().collect(Collectors.toList());
    }

    public boolean checkIfPlaylistExist(Playlist playlist){
        // if(playlist==null){
        //     return false;
        // }
        return playlists.contains(playlist);
    }
    
    public List<String> getPlaylistIds(){
        List<String> sList =new ArrayList<>();
        for(Playlist s : playlists){
            sList.add(s.id);
        }
        return sList;
        
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        String result = (playlists.isEmpty() == true ) ?
          id + " " + name
         :"User [id=" + id + ", name=" + name + ", playlists=" + playlists + "]";

        return result;
    }
   
}
