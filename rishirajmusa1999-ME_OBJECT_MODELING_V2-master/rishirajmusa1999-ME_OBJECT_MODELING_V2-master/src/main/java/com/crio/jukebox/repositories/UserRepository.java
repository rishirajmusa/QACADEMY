package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

public UserRepository(){
    userMap=new HashMap<String,User>();
}
public UserRepository(Map<String , User> usermaMap){
    this.userMap=usermaMap;
    this.autoIncrement=usermaMap.size();
}
    
    @Override
    public User save(User entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
            List<Playlist> lPlaylists=new ArrayList<>();
            if(entity.getPlaylists()!=null){
                lPlaylists=entity.getPlaylists();
            }
            User user= new User(Integer.toString(autoIncrement), entity.getName(),lPlaylists);
            userMap.put(user.getId(), user);
            return user;
        }
        List<Playlist> lPlaylists=new ArrayList<>();
        if(entity.getPlaylists()!=null){
            lPlaylists=entity.getPlaylists();
        }
        User user= new User(entity.getId(), entity.getName(),lPlaylists);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
        User user=null;
        for(Map.Entry<String,User>entry: userMap.entrySet() ){
            User u1= entry.getValue();
            if(u1.getId().equals(id)){
                user=u1;
                break;
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        boolean result = (userMap.containsKey(id))? true : false;
        return result;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

   
    
}
