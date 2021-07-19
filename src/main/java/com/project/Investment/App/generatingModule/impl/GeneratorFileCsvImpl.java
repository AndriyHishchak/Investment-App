package com.project.Investment.App.generatingModule.impl;

import com.project.Investment.App.generatingModule.GeneratorFile;
import com.project.Investment.App.model.Position;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * This class implements the interface GeneratorFile
 *
 * @author Andriy Hishchak
 */
@Service("generatorFileCsv")
public class GeneratorFileCsvImpl implements GeneratorFile {


    private final Path path = Paths.get("C:/Users/andrii.hishchak/Documents/GitHub/Investment-App/src/main/resources/csv/");

    /**
     * The method creates and writes a file
     *
     * @param file      file name
     * @param positions list of all Positions
     */
    @Override
    public void createFileAndWriteToFile(String file, List<Position> positions) {

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(path + "/" + file),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(getContent(positions));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns the content to save the file
     *
     * @param positions list of all Positions
     */
    @Override
    public String getContent(List<Position> positions) {

        StringBuilder sb = new StringBuilder();
        sb.append("ENTITY_ID,");
        sb.append("EFFECTIVE_DATE,");
        sb.append("FREQUENCY,");
        sb.append("SECURITY_ID,");
        sb.append("WEIGHT,");
        sb.append("GROSS_RETURN,");
        sb.append("BMV_GROSS,");
        sb.append("EMV_GROSS,");
        sb.append("GAIN_LOSS_GROSS");
        sb.append('\n');

        positions.forEach(position -> sb.append(position.toStringCsv()));

        return sb.toString();
    }

}
