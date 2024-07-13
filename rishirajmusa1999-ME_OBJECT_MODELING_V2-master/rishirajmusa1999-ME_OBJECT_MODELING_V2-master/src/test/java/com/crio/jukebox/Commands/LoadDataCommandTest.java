package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.services.SongService;
import org.junit.jupiter.api.Test;

public class LoadDataCommandTest {



    
    @Test
    public void loadData_MustLoadData(){
        SongRepository songRepository = new SongRepository();
        SongService songService = new SongService(songRepository);
        LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
        
        List<String> tokens = List.of("LOAD-DATA", "songs.csv");
        loadDataCommand.execute(tokens);

    }
}
