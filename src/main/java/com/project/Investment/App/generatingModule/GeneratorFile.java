package com.project.Investment.App.generatingModule;

import com.project.Investment.App.model.Position;

import java.nio.file.Path;
import java.util.List;

/**
 * The interface GeneratorFile
 *
 * @author Andriy Hishchak
 */
public interface GeneratorFile {
    /**
     * The method creates and writes a file
     *
     * @param file      file name
     * @param positions list of all Positions
     */
    void createFileAndWriteToFile(String file, List<Position> positions);

    /**
     * The method returns the content to save the file
     *
     * @param positions list of all Positions
     */
    String getContent(List<Position> positions);
}
