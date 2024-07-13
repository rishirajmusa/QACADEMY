package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("User Repositories test")
public class userRepositoryTest {
    
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        final Map<String,User> userMap = new HashMap<String,User>() {
            {
                put("1", new User("1", "User1", null) );
                put("2", new User("2", "User2", null) );
                put("3", new User("3", "User3", null) );
            }
        };
        userRepository = new UserRepository(userMap);
    }

    @Test
    @DisplayName("Save method should create and save User")
    public void saveMethod_ShouldSaveUser(){
        final User u1 = new User( "User u1");
        User expectedUser=new User("4", "User u1");
        User actualUser = userRepository.save(u1);
        Assertions.assertEquals(expectedUser,actualUser);
        
    }

    @Test
    @DisplayName("findAll method should return All User")
    public void findAllUser(){
        //Arrange
        int expectedCount = 3;
        //Act
        List<User> actualUsers = userRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualUsers.size());
    }


    @Test
    @DisplayName("findAll method should return Empty List if no Users Found")
    public void findAllUser_ShouldReturnEmptyList(){
        //Arrange
        int expectedCount = 0;
        UserRepository emptyUserRepository = new UserRepository();
        //Act
        List<User> actualUsers = emptyUserRepository.findAll();
        //Assert
        Assertions.assertEquals(expectedCount,actualUsers.size());
    }


    @Test
    @DisplayName("findById method should return User Given Id")
    public void findById_ShouldReturnUser_GivenUserId(){
        //Arrange
        String expectedUserId = "3";
        //Act
        Optional<User> actualUser = userRepository.findById(expectedUserId);
        //Assert
        Assertions.assertEquals(expectedUserId,actualUser.get().getId());
    }

    @Test
    @DisplayName("findById method should return empty if User Not Found")
    public void findById_ShouldReturnEmptyIfUserNotFound(){
        //Arrange
        Optional<User> expected = Optional.empty();
        //Act
        Optional<User> actual = userRepository.findById("5");
        //Assert
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("existById method should return User Given Id")
    public void existById_ShouldReturnUser_GivenUserId(){
        //Arrange
        boolean expected = false;
        //Act
       boolean actual = userRepository.existsById("5");
        //Assert
        Assertions.assertEquals(expected,actual);
    }
}
