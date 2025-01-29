package com.split.controller.impl;

import com.split.controller.ICategoryController;
import com.split.dto.ResponseCategoryDto;
import com.split.entities.Category;
import com.split.entities.User;
import com.split.services.ICategoryService;
import com.split.services.IUserService;
import com.split.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryControllerImpl implements ICategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/save")
    @Override
    public ResponseEntity<Map<String,Object>> saveCategory(@RequestBody Category category) {
        String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getUserById(Integer.parseInt(id));
        category.setCreator(user);
        ResponseCategoryDto savedCategory = categoryService.saveCategory(category);
        if (savedCategory == null) {
            return ResponseUtils.error("Category can not build.");
        }
        return ResponseUtils.success("Category successfully created.",savedCategory);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> listCategories() {
        List<ResponseCategoryDto> categories = categoryService.listCategories();
        return ResponseUtils.success("Categories successfully listed.",categories);
    }

}
