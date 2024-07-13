package com.crio.jukebox.services;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import com.crio.jukebox.repositories.ISongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Song Service test")
@ExtendWith(MockitoExtension.class)
public class SongServiceTest {
    @Mock
    private ISongRepository songRespositoryMock;

    @InjectMocks
    private SongService songService;

    @Test
    @DisplayName("Create should create song")
    public void createSong_ShouldCreateSong(){
        Artist a = new Artist("john doe");
        Album album = new Album("album ");
        Song expectedSong = new Song("1", "name", new SongOwner(a),album);
        when(songRespositoryMock.save(any(Song.class))).thenReturn(expectedSong);
        
        //Act
        Song actualSong = songService.create("name", new SongOwner(a),album);

        //Assert
        Assertions.assertEquals(expectedSong,actualSong);
        verify(songRespositoryMock,times(1)).save((any(Song.class)));
    }
    @Test
    @DisplayName("Get all songs should get all songs")
    public void getAllSongs_ShouldGetAllSongs(){
        
        Artist a = new Artist("john doe");
        Album album = new Album("album ");
        Song aSong = new Song("1", "name", new SongOwner(a),album);
        
        Song bSong = new Song("2", "name2", new SongOwner(a),album);
        List<Song> eList= new ArrayList<Song>(){
            {
                add(aSong);
                add(bSong);
            }
        };
        when(songRespositoryMock.findAll()).thenReturn(eList);
        List<Song> actualSong = songService.getAllSongs();

        Assertions.assertEquals(eList, actualSong);
        verify(songRespositoryMock,times(1)).findAll();
    }
}
