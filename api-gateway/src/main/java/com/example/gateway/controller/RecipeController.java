package com.example.gateway.controller;


import com.example.gateway.api.ApiResponse;
import com.example.gateway.model.Recipe;
import com.example.gateway.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
@Autowired
private RecipeService recipeService;

  @RequestMapping(path = "/recipes", method = RequestMethod.GET)
  public List<Recipe> getRecipes(@RequestParam(required = false, defaultValue = "1") int size,
      @RequestParam(required = false, defaultValue = "2") int page) {
    return recipeService.getRecipes(size, page);
  }


  @RequestMapping(path = "/recipe/{uniqueValue}", method = RequestMethod.GET)
  public ResponseEntity<ApiResponse<Recipe>> getRecipeByUniqueValue(@PathVariable String uniqueValue) {
    Recipe find= recipeService.getRecipeByUniqueValu(uniqueValue);
    ApiResponse<Recipe> body = ApiResponse.ok("Recipe fetched", find);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(body);
  }

  @RequestMapping(path = "/Recipes", method = RequestMethod.POST)
  public ResponseEntity<ApiResponse<Recipe>> addRecipe2(@RequestBody Recipe recipe) {
    // Implementation for adding a new recipe
//    recipeService.addRecipes(recipe);

    Recipe saved = recipeService.addRecipes(recipe);
    ApiResponse<Recipe> body = ApiResponse.ok("Recipe created", saved);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(body);

}



}
