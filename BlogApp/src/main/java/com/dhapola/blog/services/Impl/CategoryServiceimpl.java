package com.dhapola.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhapola.blog.entities.Category;
import com.dhapola.blog.exception.ResourceNotFoundException;
import com.dhapola.blog.payload.CategoryDto;
import com.dhapola.blog.repositories.CategoryRepo;
import com.dhapola.blog.services.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	// Create Category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	// Update category
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categoty", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	// get Category
	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.save(cat);
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	// Get All categories
	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoriesDto = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return categoriesDto;
	}

	// Delete A Category
	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.delete(cat);

	}

	// delete All Category
	@Override
	public void deleteAllCategories() {

		List<Category> allCategories = this.categoryRepo.findAll();

		for (Category category : allCategories) {
			this.categoryRepo.delete(category);
		}

	}

}
