package com.crio.jukebox.Commands;

import java.util.List;

public interface ICommand {
    void execute(List<String> tokens);
}
