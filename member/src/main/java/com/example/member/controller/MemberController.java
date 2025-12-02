package com.example.member.controller;


import com.example.member.command.CommandExecutor;
import com.example.member.command.CreateMemberCommand;
import com.example.member.command.GetMemberCommand;
import com.example.member.command.model.CreateMemberCommandRequest;
import com.example.member.command.model.GetMemberCommandRequest;
import com.example.member.controller.model.MemberRequest;
import com.example.member.controller.model.MemberResponse;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {


  private final CommandExecutor commandExecutor;

  private final MemberRepository memberRepository;


  @PostMapping
  public MemberResponse create(@RequestBody MemberRequest webRequest) {

    // 1. Convert Public JSON -> Internal Command Request
    var commandRequest = CreateMemberCommandRequest.builder()
        .name(webRequest.getName()).email(webRequest.getEmail()).password(webRequest.getPassword()).build();

    // 2. Execute the Logic (The Worker)
    var commandResponse = commandExecutor.execute(CreateMemberCommand.class, commandRequest);

    // 3. Convert Internal Result -> Public JSON Response
    return MemberResponse.builder()
        .name(commandResponse.getName())
        .email(webRequest.getEmail()) // We take email from the original request
        .build();
  }


  @GetMapping
  public MemberResponse get(@RequestParam MemberRequest memberRequest) {
    var commandRequest =
        GetMemberCommandRequest.builder().email(memberRequest.getEmail()).password(memberRequest.getPassword()).build();

    var commandResponse = commandExecutor.execute(GetMemberCommand.class, commandRequest);

    return MemberResponse.builder().name(commandResponse.getName()).email(commandResponse.getEmail()).build();
  }


  //  @RequestMapping(path = "/recipes", method = RequestMethod.GET)
  //  public List<Recipe> getRecipes(@RequestParam(required = false, defaultValue = "1") int size,
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
