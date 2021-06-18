package com.project.Investment.App.service;

import com.project.Investment.App.Dao.EntityDao;

public interface EntityService {

    EntityDao findById (String id);
}
