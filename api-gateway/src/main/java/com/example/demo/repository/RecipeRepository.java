package com.example.demo.repository;

import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
@Query("SELECT r FROM Recipe r Where r.uniqueValue = ?1")
List<Recipe> findByUniqueValue(String uniqueValue);

}
