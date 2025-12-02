package com.example.gateway.controller;


import com.example.gateway.command.CommandExecutor;
import com.example.gateway.command.LoginCommand;
import com.example.gateway.command.model.LoginCommandRequest;
import com.example.gateway.model.LoginRequest;
import com.example.gateway.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {


  private final CommandExecutor commandExecutor;



  @GetMapping
  public LoginResponse get(@RequestParam("email") String email,
      @RequestParam("password") String password) {
    String url="http://localhost:8081/member?email="+email+"&password="+password;
    var commandRequest = LoginCommandRequest.builder().email(email).password(password).build();

    var commandResponse = commandExecutor.execute(LoginCommand.class, commandRequest);

    return LoginResponse.builder().name(commandResponse.getName()).email(commandResponse.getEmail()).build();
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
