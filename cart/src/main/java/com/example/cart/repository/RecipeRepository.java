package com.example.cart.repository;

import com.example.cart.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
@Query("SELECT r FROM Recipe r Where r.uniqueValue = ?1")
List<Recipe> findByUniqueValue(String uniqueValue);

}
