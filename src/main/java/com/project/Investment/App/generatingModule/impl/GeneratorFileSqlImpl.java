package com.project.Investment.App.generatingModule.impl;

import com.project.Investment.App.generatingModule.GeneratorFile;
import com.project.Investment.App.generatingModule.GeneratorPosition;
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
@Service("generatorFileSql")
public class GeneratorFileSqlImpl implements GeneratorFile {

    private final GeneratorPosition generatorPosition;
    private final Path path = Paths.get("C:/Users/andrii.hishchak/Desktop/Investment-App/src/main/resources/db/migration/");

    private final String INSERT_INTO_POSITION = "INSERT INTO position (\n" +
            "    entity_id ,\n" +
            "    effective_date ,\n" +
            "    aggregate_id ,\n" +
            "    frequency ,\n" +
            "    security_id ,\n" +
            "    weight ,\n" +
            "    gross_return ,\n" +
            "    bmv_gross ,\n" +
            "    emv_gross ,\n" +
            "    gain_loss_gross)\n" +
            "VALUES ";


    public GeneratorFileSqlImpl(GeneratorPosition generatorPosition) {
        this.generatorPosition = generatorPosition;
    }

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

        sb.append(INSERT_INTO_POSITION);

        positions.forEach(position -> sb.append(position.toStringSql()));

        return sb.toString().replaceFirst(".$", ";");
    }
}
