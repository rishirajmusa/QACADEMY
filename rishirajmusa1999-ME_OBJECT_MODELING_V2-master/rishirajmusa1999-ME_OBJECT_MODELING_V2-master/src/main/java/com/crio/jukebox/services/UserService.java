package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.InvalidOpException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.SongRepository;

public class UserService implements IUserService{
    private IUserRepository userRepository;
    private IPlaylistRepository playlistRepository;
    private ISongRepository songRepository;


    public UserService(IUserRepository userRepository, IPlaylistRepository playlistRepository, ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository=songRepository;
    }
    @Override
    public User create(String name) {
        // TODO Auto-generated method stub
        User user = new User(name);
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }
    @Override
    public PlaylistSummaryDTO modifyPlaylistAddSong(String userId, String playlistId,
            List<String> songids) {
        User user= userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot Modify Playlist, User Not Found!"));
        // TODO Auto-generated method stub
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Cannot Modify Playlist, Playlist Id: "+playlistId+" does not exist!"));
        if(user.checkIfPlaylistExist(playlist) == false){
            throw new InvalidOpException("This Playlist"+playlistId+ "does not come under this user"+userId);
        }
        List<Song> songs = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for(String ids : songids){
            Optional<Song> song = songRepository.findById(ids);
            if(song.isEmpty() == false){
                songs.add(song.get());
            }else{
                errors.add("Song with Id:"+ids+"Does not exist");
            }
        }
        if(!errors.isEmpty()){
            System.out.println(errors);
        }
        for(Song s : songs){
           if(playlist.checkIfSongExist(s)==false){
            playlist.addSong(s);
           }
        }
        
        playlistRepository.save(playlist);
        songids= playlist.getSongIds();
        return new PlaylistSummaryDTO(playlist, songids);
    }
    @Override
    public PlaylistSummaryDTO modifyPlaylistDeleteSong(String userId, String playlistId,
            List<String> songids) {
        // TODO Auto-generated method stub
        User user= userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Cannot Modify Playlist, User Not Found!"));
        // TODO Auto-generated method stub
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new PlaylistNotFoundException("Cannot Modify Playlist, Playlist Id: "+playlistId+" does not exist!"));
       
        if(user.checkIfPlaylistExist(playlist) == false){
            throw new InvalidOpException("This Playlist"+playlistId+ "does not come under this user"+userId);
        }
        List<Song> songs = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for(String ids : songids){
            Optional<Song> song = songRepository.findById(ids);
            if(song.isEmpty() == false){
                songs.add(song.get());
            }else{
                errors.add("Song with Id:"+ids+"Does not exist");
            }
        }
        if(!errors.isEmpty()){
            System.out.println(errors);
        }
        for(Song s : songs){
           if(playlist.checkIfSongExist(s)==true){
            playlist.deleteSong(s);
           }
        }
        playlistRepository.save(playlist);
        Playlist playlistNew = playlistRepository.save(playlist);
        List<String> songList = playlistNew.getSongIds();
        return new PlaylistSummaryDTO(playlist, songList);
    }

    
    @Override
    public String deletePlaylist(String UserId, String playlistId) {
        // TODO Auto-generated method stub
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+"  Does not exist"));
        Playlist playlist= playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("The Playlist Id: "+playlistId+
        "You are trying to delete, does not exist"));
    if(user.checkIfPlaylistExist(playlist) == false){
        throw new PlaylistNotFoundException("User does not have the Playlist you are trying to delete");
    }
    user.deletePlaylist(playlist);
    userRepository.save(user);

        return "Delete Successful";
    }

}
