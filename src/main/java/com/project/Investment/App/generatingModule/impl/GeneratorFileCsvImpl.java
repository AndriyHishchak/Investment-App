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

@Service("generatorFileCsv")
public class GeneratorFileCsvImpl implements GeneratorFile {

    private final GeneratorPosition generatorPosition;
    private final Path path = Paths.get("C:/Users/andrii.hishchak/Desktop/Investment-App/src/main/resources/csv/");

    public GeneratorFileCsvImpl(GeneratorPosition generatorPosition) {
        this.generatorPosition = generatorPosition;
    }

    @Override
    public void createFileAndWriteToFile(String file, List<Position> positions) {

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of(path + "/" + file),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(content(positions));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String content(List<Position> positions) {

        StringBuilder sb = new StringBuilder();
        sb.append("ENTITY_ID,");
        sb.append("EFFECTIVE_DATE,");
        sb.append("AGGREGATE_ID,");
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
