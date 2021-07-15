package com.project.Investment.App.service;

import java.time.LocalDate;
import java.util.Optional;

public class SqlUtil {

    public static <T> String IsPresentParameter(T parameter, String sql) {
        return parameter != null ? sql : "";
    }
}
