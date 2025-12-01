package com.example.gateway.service;


import com.example.gateway.model.Recipe;
import com.example.gateway.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

  @Autowired
  RecipeRepository repository;

  //  private final RecipeRepository recipeRepository;
  //    public RecipeService(RecipeRepository recipeRepository) {
  //        this.recipeRepository = recipeRepository;
  //    }
  //  List<Recipe> recipes = new ArrayList<Recipe>();
  //  {
  //    for (int i = 1; i <= 10; i++) {
  //      recipes.add(new Recipe(
  //          (long) i,
  //          "Recipe " + i,
  //          10.0 + i
  //      ));
  //    }
  //  }


  public Recipe addRecipes(Recipe recipe) {
    if (repository.findByUniqueValue(recipe.getUniqueValue()).stream().findFirst().isPresent()) {
      throw new IllegalArgumentException("Recipe with the same unique value already exists.");
    }
   return repository.save(recipe);
  }

  public List<Recipe> getRecipes(Integer size, Integer page) {

    return repository.findAll().subList((page - 1) * size, Math.min(page * size, repository.findAll().size()));
  }

  public Recipe getRecipeByUniqueValu(String uniqueValue) {
    if (repository.findByUniqueValue(uniqueValue).isEmpty()) {
      throw new IllegalArgumentException("Recipe with the given unique value does not exist.");
    }
    return repository.findByUniqueValue(uniqueValue).stream().findFirst().orElse(null);
    //    List<Recipe> recipes = repository.findByUniqueValue(uniqueValue);
    //    if (recipes != null && !recipes.isEmpty()) {
    //      return recipes.get(0);
    //    }
    //    return null;
  }


  //  private List<Recipe> recipes = new ArrayList<>();
}
