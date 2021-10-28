package com.omni.cell.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.omni.cell.entity.Recipe;

public interface RecipeRepo extends CrudRepository<Recipe, Integer> {
	
	Recipe findById(int id);

}
