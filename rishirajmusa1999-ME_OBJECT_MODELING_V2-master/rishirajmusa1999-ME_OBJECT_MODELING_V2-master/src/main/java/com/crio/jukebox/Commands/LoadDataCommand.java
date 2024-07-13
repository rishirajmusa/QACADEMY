package com.crio.jukebox.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.ArtistGroup;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.services.IsongService;

public class LoadDataCommand implements ICommand{
    private final IsongService songservice;

    public LoadDataCommand(IsongService songservice) {
        this.songservice = songservice;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        
        boolean errors = false;
        String filename = tokens.get(1);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line=reader.readLine()) != null){
                String[] columns = line.split(",");
                if(columns.length<6){
                  break;
                }
                String songName = columns[1];
                String albumName =  columns[3];
                String artistNames= columns[5];
                SongOwner songOwner;
                if(artistNames.contains("#")){
                    String[] artistList = artistNames.split("#");
                    List<Artist> artists = new ArrayList<>();
                    for(String names: artistList){
                        Artist artist = new Artist(names);
                        artists.add(artist);
                    }
                    ArtistGroup artistGroup = new ArtistGroup(artists); 
                    songOwner = new SongOwner(artistGroup);  
                }else{
                    Artist artist = new Artist(artistNames);
                    songOwner = new SongOwner(artist);
                }
                Album album = new Album(albumName);
                Song song = songservice.create(songName, songOwner, album);
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            // TODO Auto-generated catch block
            errors = true;
            System.out.println("File reached end");
        }
        if(! errors){
            System.out.println("Songs Loaded successfully");
        }
        
    }


    
    
}
