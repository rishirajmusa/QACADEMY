package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.plaf.SliderUI;

public class Playlist extends BaseEntity {
private final List<Song> songs;
private Song currentSong;
private int currentSongIndex;

    public Playlist(Playlist playlist){
        this(playlist.id, playlist.name, playlist.songs);
    }


    public Playlist(String id, String name, List<Song> songs) {
        this( name, songs);
        this.id=id;
       
    }

    public Playlist(String name, List<Song> songs) {
        this.name=name;
        this.songs=songs;
       
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public boolean checkIfSongExist(Song song){
        return songs.contains(song);
        
    }
    public void deleteSong(Song song){
        songs.removeIf(s -> s.getId() == song.getId());
    }

    public List<Song> getSongs() {
        return songs.stream().collect(Collectors.toList());
    }

    public List<String> getSongIds(){
        List<String> sList =new ArrayList<>();
        for(Song s : songs){
            sList.add(s.id);
        }
        return sList;
        
    }
    public Song getCurrentSong() {
        if(currentSong == null && songs!=null){
            return songs.get(currentSongIndex);
        }
        return currentSong;
    }


    public void setCurrentSong(Song song) {
        int i=0;
        for(Song s:songs){
            if(s.id==song.id){
                currentSongIndex =i;
            }
            i++;
        }
        this.currentSong = song;
    }
   
    public void playNextSong(){
        if(currentSongIndex<songs.size()-1){
            currentSongIndex++;
        }else{
            currentSongIndex=0;
        }
        playCurrentSong(currentSongIndex);
    }   
    public void playPreviousSong(){
        if(currentSongIndex>0){
            currentSongIndex--;
        }else{
            currentSongIndex = songs.size()-1;
        }
        playCurrentSong(currentSongIndex);

    }
    public void playCurrentSong(int currentSongIndex){
        this.currentSongIndex = currentSongIndex;
        this.currentSong = songs.get(currentSongIndex);

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
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist ID - "+ id;
    }







    
}
