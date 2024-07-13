package com.crio.jukebox.repositories;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongOwner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Song Repository Test")
public class SongRepositoryTest {
    
    private SongRepository songRepository;
    Map<String, Song> userMap;
    @BeforeEach
    void setup(){
        final Artist artist= new Artist("artist 1");
        final Artist artist2= new Artist("artist 2");
        final Artist artist3= new Artist("artist 3");
        final Artist artist4= new Artist("artist 4");
        Album album = new Album("album ");
        userMap= new HashMap<String,Song>(){
            {
                put("1", new Song("1","SongABC", new SongOwner(artist),album));
                put("2", new Song("3","SongDEF", new SongOwner(artist2),album));
                put("3", new Song("2","SongGHI", new SongOwner(artist3),album));
                put("4", new Song("4","SongJKL", new SongOwner(artist4),album));
            }
        };
        songRepository = new SongRepository(userMap);
    }

    @Test
    @DisplayName("Save method should create and save Song")
    public void saveMethod_ShouldSaveSong(){
        Artist artist= new Artist("Becca");
        Album album = new Album("album ");
        final Song s1 = new Song( "Song 1", new SongOwner(artist),album);
        Song expectedSong=new Song("5","Song 1", new SongOwner(artist),album);
        Song actualSong = songRepository.save(s1);
        Assertions.assertEquals(expectedSong,actualSong);
    }



    @Test
    @DisplayName("findAll method should return All Songs")
    public void findAllSongs(){
        //Arrange
        int expectedCount = 4;
        //Act
        List<Song> actualSongs = songRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualSongs.size());
    }


    @Test
    @DisplayName("findAll method should return Empty List if no Songs Found")
    public void findAllSongs_ShouldReturnEmptyList(){
        //Arrange
        int expectedCount = 0;
        SongRepository emptySongRepository = new SongRepository();
        //Act
        List<Song> actualSongs = emptySongRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualSongs.size());
    }


    @Test
    @DisplayName("findById method should return Songs Given Id")
    public void findById_ShouldReturnSong_GivenSongId(){
        //Arrange
        String expectedSongId = "3";
        //Act
        Optional<Song> actualSongs = songRepository.findById(expectedSongId);
        //Assert
        Assertions.assertEquals(expectedSongId,actualSongs.get().getId());
    }

    @Test
    @DisplayName("findById method should return empty if Song Not Found")
    public void findById_ShouldReturnEmptyIfSongNotFound(){
        //Arrange
        Optional<Song> expected = Optional.empty();
        //Act
        Optional<Song> actual = songRepository.findById("5");
        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("existById method should return Song status Given Id")
    public void existById_ShouldReturnSongStatus_GivenSongId(){
        //Arrange
        boolean expected = true;
        //Act
       boolean actual = songRepository.existsById("4");
        //Assert
        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("returnById method should return Song Given Id")
    public void returnById_ShouldReturnSong_GivenSongId(){
        //Arrange
        Song expectedSong=new Song(userMap.get("2"));
        //Act
       Song actualSong= songRepository.returnById("3");
        //Assert
        Assertions.assertEquals(expectedSong,actualSong);
    }

    @Test
    @DisplayName("deleteById method should Delete Song Given Id")
    public void deleteById_ShouldDeleteSong_GivenSongId(){
        //Arrange
       songRepository.deleteById("3");
        //Assert
        Assertions.assertEquals(false,songRepository.existsById("3"));
    }


    @Test
    @DisplayName("delete method should Delete Song Given SongObject")
    public void delete_ShouldDeleteSong_GivenSongObject(){
        //Arrange
        Song songToBeDeleted=new Song(userMap.get("2"));
       songRepository.delete(songToBeDeleted);
        //Assert
        Assertions.assertEquals(false,songRepository.existsById("3"));
    }
}
