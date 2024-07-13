package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Album extends BaseEntity {
    private final List<Song> songs;

    public Album(String name){
        this.name=name;
        this.songs= new ArrayList<>();
    }

public void addSong(Song song){
    songs.add(song);
}

public List<Song> getSongs() {
    return songs.stream().collect(Collectors.toList());
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
        Album other = (Album) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return  name;
   
}
}

