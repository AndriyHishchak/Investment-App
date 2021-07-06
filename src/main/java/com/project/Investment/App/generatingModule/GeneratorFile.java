package com.project.Investment.App.generatingModule;

import com.project.Investment.App.model.Position;

import java.nio.file.Path;
import java.util.List;

public interface GeneratorFile {

    void createFileAndWriteToFile(String file, List<Position> positions);

    String content(List<Position> positions);
}
