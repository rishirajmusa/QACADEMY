package com.geektrust.backend.commands;

import java.util.List;

public interface Icommand {
    public void execute(List<String> tokens);
}
