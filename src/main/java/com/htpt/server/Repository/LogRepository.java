package com.htpt.server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.htpt.server.Models.Form.LogModel;

@Repository
public interface LogRepository extends JpaRepository<LogModel, Integer> {
    
    public List<LogModel> findAll();
}
