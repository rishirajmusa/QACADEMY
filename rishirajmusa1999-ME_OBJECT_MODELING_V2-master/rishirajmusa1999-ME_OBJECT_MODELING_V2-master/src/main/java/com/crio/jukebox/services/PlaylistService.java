package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.dtos.PlaylistSummaryDTO;
import com.crio.jukebox.dtos.SongSummaryDTO;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.UserPlaylists;
import com.crio.jukebox.exceptions.InvalidPlaylistException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.UserRepository;

public class PlaylistService implements IPlaylistService{
    private final IPlaylistRepository playlistRepository;
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;
    private final UserPlaylists userPlaylists;
    
    public PlaylistService(IPlaylistRepository playlistRepository, IUserRepository userRepository,
            ISongRepository songRepository, UserPlaylists userPlaylists) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.userPlaylists = userPlaylists;
    }

    @Override
    public Playlist create(String UserId, String playlistName, List<String> songList)
            throws UserNotFoundException, PlaylistNotFoundException {
        // TODO Auto-generated method stub
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+"  Does not exist"));
        List<Song> songs= new ArrayList<>();
        if(songList.isEmpty()){
            throw new SongNotFoundException("Songs IDS are not available, Please provide song ids");
        }
        for(String sids : songList){
            Song song= songRepository.findById(sids).orElseThrow(()-> new SongNotFoundException("the song with ID:  "+sids+" Does not exist"));
            songs.add(song);
        };
        Playlist playlist= new Playlist(playlistName, songs);
        
        playlist=playlistRepository.save(playlist);
        user.addPlaylist(playlist);
        return playlist;
    }
    @Override
    public List<Playlist> getAllPlaylists(String UserId) {
        // TODO Auto-generated method stub
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+",  Does not exist"));

        return user.getPlaylists();
    }
    @Override
    public SongSummaryDTO runPlaylist(String UserId, String playlistID)
            throws PlaylistNotFoundException, SongNotFoundException,UserNotFoundException {
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+"  Does not exist"));
        Playlist playlist= playlistRepository.findById(playlistID).orElseThrow(()-> new PlaylistNotFoundException("The Playlist Id: "+playlistID+
        ",  You are trying to Run, does not exist"));
        if(user.checkIfPlaylistExist(playlist) == false){
            throw new PlaylistNotFoundException("User does not have the Playlist you are trying to delete");
        }
        if(playlist.getSongs() == null){
            throw new SongNotFoundException("Playlist is empty.");
        }
        List<Song> songs= playlist.getSongs();
        
        playlist.setCurrentSong(songs.get(0));
        Song currentSong = playlist.getCurrentSong();
        // TODO Auto-generated method stub
        userPlaylists.setActivePlaylist(UserId, playlist);
        return new SongSummaryDTO(currentSong.getName(), currentSong.getAlbum().toString(), currentSong.getOwner().toString());
    }
    @Override
    public SongSummaryDTO playNextSong(String UserId)throws UserNotFoundException,PlaylistNotFoundException {
        // TODO Auto-generated method stub
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+"  Does not exist"));
        Playlist currentPlaylist = userPlaylists.getActivePlaylist(UserId);
       if(currentPlaylist == null){
        throw new PlaylistNotFoundException("No Playlist is running, please play a playlist");
       }
       currentPlaylist.playNextSong();
       Song currentSong = currentPlaylist.getCurrentSong();
        return new SongSummaryDTO(currentSong.getName(), currentSong.getAlbum().getName(), currentSong.getOwner().toString());
    }
    @Override
    public SongSummaryDTO playPreviousSong(String UserId) {
        // TODO Auto-generated method stub
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+"  Does not exist"));
        Playlist currentPlaylist = userPlaylists.getActivePlaylist(UserId);
        if(currentPlaylist == null){
         throw new PlaylistNotFoundException("No Playlist is running, please play a playlist");
        }
        currentPlaylist.playPreviousSong();
        Song currentSong = currentPlaylist.getCurrentSong();
         return new SongSummaryDTO(currentSong.getName(), currentSong.getAlbum().getName(), currentSong.getOwner().toString());
      }
    @Override
    public SongSummaryDTO playSpecificSong(String UserId, String SongId) {
        User user= userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException("User with UserId : "+UserId+"  Does not exist"));
        Playlist currentPlaylist = userPlaylists.getActivePlaylist(UserId);
        if(currentPlaylist == null){
            throw new PlaylistNotFoundException("No Playlist is running, please play a playlist");
           }
        Song song=songRepository.findById(SongId).orElseThrow(()-> new SongNotFoundException("Song does not exist"));
        boolean answer = currentPlaylist.checkIfSongExist(song);
           
        if(answer==false){
            System.out.println("Given song id is not a part of the active playlist");
            throw new SongNotFoundException("Song Not Found in the current active playlist");
           }
        currentPlaylist.setCurrentSong(song);
        Song currentSong = currentPlaylist.getCurrentSong();
         return new SongSummaryDTO(currentSong.getName(), currentSong.getAlbum().getName(), currentSong.getOwner().toString());
  }
}
