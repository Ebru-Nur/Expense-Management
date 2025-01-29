package com.split.services;

import com.split.dto.ResponseCategoryDto;
import com.split.entities.Category;

import java.util.List;

public interface ICategoryService {
    public ResponseCategoryDto saveCategory(Category category);
    public List<ResponseCategoryDto> listCategories();

    public Category getCategoryById(Integer id);
}
