package com.geektrust.backend.repositoriesTest;
import com.geektrust.backend.entities.Category;
import com.geektrust.backend.entities.Programmes;
import com.geektrust.backend.repositories.IProgrammeRepository;
import com.geektrust.backend.repositories.ProgrammeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("ProgrammerRepositoryTest")
public class ProgrammerRepositoryTest {
    private IProgrammeRepository programmeRepository =new ProgrammeRepository();
    private Programmes programmes1, programmes2;
    
    @BeforeEach
    void setup(){
        //Arrange
        programmes1 = new Programmes(0.0, Category.NOT_FOUND);
        programmes2 = new Programmes(3000, Category.CERTIFICATION);
        programmeRepository.save(programmes1);
        programmeRepository.save(programmes2);
    }

    @Test
    @DisplayName("save method of ProgrammeRepository should save the program")
    public void save_ShouldSaveProgramme(){
        //Act
        boolean actual =programmeRepository.findAllUserAddedPrograms().contains(programmes1);
        //Assert
        Assertions.assertEquals(true, actual);
    }

    @Test
    @DisplayName("checkIfExist method of ProgrammeRepository should return true if programme is valid")
    public void checkIfExist_ShouldReturnTrue(){
        //Act
        boolean actual =programmeRepository.checkIfExist("CERTIFICATION");
        //Assert
        Assertions.assertEquals(true, actual);
    }

    @Test
    @DisplayName("checkIfExist method of ProgrammeRepository should return false if programme is not valid")
    public void checkIfExist_ShouldReturnFalse_GivenWrongProgramme(){
        //Act
        boolean actual =programmeRepository.checkIfExist("NOT_FOUND");
        //Assert
        Assertions.assertEquals(false, actual);
    }

    @Test
    @DisplayName("getCartSize method of ProgrammeRepository should return Cart size")
    public void getCartSize_ShouldReturnCartSize(){
        //Act
        int actual =programmeRepository.getCartSize();
        //Assert
        Assertions.assertEquals(2, actual);
    }

    @Test
    @DisplayName("getProgramPricesByName method of ProgrammeRepository should return price given name")
    public void getProgramPricesByName_ShouldReturnPrice_GivenName(){
        //Act
        double actual =programmeRepository.getProgramPricesByName("CERTIFICATION");
        //Assert
        Assertions.assertEquals(3000.0, actual);
    }

    @Test
    @DisplayName("getProgramPricesByName method of ProgrammeRepository should zero given wrong name")
    public void getProgramPricesByName_ShouldReturnZero_GivenWrongName(){
        //Act
        double actual =programmeRepository.getProgramPricesByName("NOT_FOUND");
        //Assert
        Assertions.assertEquals(0.0, actual);
    }
    
}
