package com.split.services.impl;

import com.split.dto.ResponseCategoryDto;
import com.split.entities.Category;
import com.split.entities.Group;
import com.split.repository.CategoryRepository;
import com.split.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseCategoryDto saveCategory(Category category) {
        try {
            Category category1= categoryRepository.save(category);
            return new ResponseCategoryDto(category1.getId(), category1.getName(), category1.getCreator().getFirstName()+" "+category1.getCreator().getLastName());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ResponseCategoryDto> listCategories() {
        List<ResponseCategoryDto>  categories= new ArrayList<>();
        try{
            List<Category> categoryList= categoryRepository.findAll();
            for (Category category: categoryList) {
                categories.add(new ResponseCategoryDto(category.getId(), category.getName(), category.getCreator().getFirstName()+" "+category.getCreator().getLastName()));
            }
            return categories;
        } catch (Exception e) {
            return categories;
        }
    }

    @Override
    public Category getCategoryById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        Category category = optional.get();
        return category;
    }
}
