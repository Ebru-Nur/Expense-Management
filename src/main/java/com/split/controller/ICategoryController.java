package com.split.controller;

import com.split.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryController {
    public ResponseEntity<Map<String, Object>> saveCategory(Category category);

    public ResponseEntity<Map<String,Object>> listCategories();

}
