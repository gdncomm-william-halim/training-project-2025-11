package com.example.gateway.controller;


import com.example.gateway.command.CommandExecutor;
import com.example.gateway.command.LoginCommand;
import com.example.gateway.command.LogoutCommand;
import com.example.gateway.command.model.LoginCommandRequest;
import com.example.gateway.model.LoginRequest;
import com.example.gateway.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {


  private final CommandExecutor commandExecutor;

  private final LogoutCommand logoutCommand;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest webRequest) {
    var commandRequest = LoginCommandRequest.builder()
        .email(webRequest.getEmail())
        .password(webRequest.getPassword())
        .build();

    var commandResponse = commandExecutor.execute(LoginCommand.class, commandRequest);
    return LoginResponse.builder().accessToken(commandResponse.getAccessToken()).build();
  }

  @PostMapping("/logout")
  public void logout(@RequestHeader("Authorization") String token) {
    logoutCommand.execute(token);
  }

//  @PostMapping
//  public MemberResponse create(@RequestBody MemberRequest webRequest) {
//
//    var commandRequest = CreateMemberCommandRequest.builder()
//        .name(webRequest.getName()).email(webRequest.getEmail()).password(webRequest.getPassword()).build();
//
//    var commandResponse = commandExecutor.execute(CreateMemberCommand.class, commandRequest);
//
//    return MemberResponse.builder()
//        .name(commandResponse.getName())
//        .email(webRequest.getEmail()) // We take email from the original request
//        .build();
//  }




  //  @RequestMapping(path = "/recipes", method = RequestMethod.GET)
  //  public List<Recipe> getRecpes(@RequestParam(required = false, defaultValue = "1") int size,
  //      @RequestParam(required = false, defaultValue = "2") int page) {
  //    return recipeService.getRecipes(size, page);
  //  }
  //
  //
  //  @RequestMapping(path = "/recipe/{uniqueValue}", method = RequestMethod.GET)
  //  public ResponseEntity<ApiResponse<Recipe>> getRecipeByUniqueValue(@PathVariable String uniqueValue) {
  //    Recipe find= recipeService.getRecipeByUniqueValu(uniqueValue);
  //    ApiResponse<Recipe> body = ApiResponse.ok("Recipe fetched", find);
  //    return ResponseEntity
  //        .status(HttpStatus.OK)
  //        .body(body);
  //  }
  //
  //  @RequestMapping(path = "/Recipes", method = RequestMethod.POST)
  //  public ResponseEntity<ApiResponse<Recipe>> addRecipe2(@RequestBody Recipe recipe) {
  //    // Implementation for adding a new recipe
  ////    recipeService.addRecipes(recipe);
  //
  //    Recipe saved = recipeService.addRecipes(recipe);
  //    ApiResponse<Recipe> body = ApiResponse.ok("Recipe created", saved);
  //
  //    return ResponseEntity
  //        .status(HttpStatus.OK)
  //        .body(body);
  //
  //}


}
