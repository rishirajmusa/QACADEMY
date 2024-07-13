package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;

public class CreateUserCommand implements ICommand{
private final IUserService userService;
    public CreateUserCommand(IUserService userService) {
    this.userService = userService;
}
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String name =tokens.get(1);
        User user = userService.create(name);
        System.out.println(user.toString());
    }
    
}
