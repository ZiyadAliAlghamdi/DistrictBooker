package org.example.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiException;
import org.example.capstone2.Model.Category;
import org.example.capstone2.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getSingleCategory(Integer id){
        Category category = categoryRepository.getCategoryById(id);

        if (category == null){
            throw new ApiException("<category-service> category not found");
        }

        return category;
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer id, Category category){
        Category oldCategory = categoryRepository.getCategoryById(id);

        if (oldCategory == null){
            throw new ApiException("<category-service> category not found");
        }

        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Integer id){
        Category category = categoryRepository.getCategoryById(id);

        if (category == null){
            throw new ApiException("<category-service> category not found");
        }

        categoryRepository.delete(category);
    }
}
