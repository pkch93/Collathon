package com.project.collathon.command;

import java.io.IOException;
import java.util.Map;

public interface Command {
    void excute(Map map) throws IOException;
}
