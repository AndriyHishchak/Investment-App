package com.project.Investment.App.commandLineRunner;

import com.project.Investment.App.generatingModule.GeneratorPosition;
import com.project.Investment.App.generatingModule.impl.GeneratorFileCsvImpl;
import com.project.Investment.App.generatingModule.impl.GeneratorFileSqlImpl;
import com.project.Investment.App.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;

public class Runner implements CommandLineRunner {

    @Autowired
    private GeneratorPosition generatorPosition;
    @Autowired
    private GeneratorFileCsvImpl generatorFileCsv;
    @Autowired
    private GeneratorFileSqlImpl generatorFileSql;


    @Override
    public void run(String... args) {

        if (false) {
            List<Position> positions = generatorPosition
                    .generateDataForPositions(
                            LocalDate.parse("2021-01-01"),
                            "F001",
                            1000000,
                            new Integer[][]{{2, 10}, {2, 10}});

            generatorFileCsv.createFileAndWriteToFile("Position.csv", positions);
            generatorFileSql.createFileAndWriteToFile("V6__insert_data_into_position.sql", positions);
        }

    }


}
