package com.example.member.controller;


import com.example.member.command.CommandExecutor;
import com.example.member.command.CreateMemberCommand;
import com.example.member.command.GetMemberCommand;
import com.example.member.command.model.CreateMemberCommandRequest;
import com.example.member.command.model.GetMemberCommandRequest;
import com.example.member.controller.model.GetMemberRequest;
import com.example.member.controller.model.GetMemberResponse;
import com.example.member.controller.model.MemberRequest;
import com.example.member.controller.model.MemberResponse;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    var commandRequest = CreateMemberCommandRequest.builder()
        .name(webRequest.getName()).email(webRequest.getEmail()).password(webRequest.getPassword()).build();

    var commandResponse = commandExecutor.execute(CreateMemberCommand.class, commandRequest);

    return MemberResponse.builder()
        .name(commandResponse.getName())
        .email(webRequest.getEmail()) // We take email from the original request
        .build();
  }

  @GetMapping("/debug-auth")
  public String debugAuth(@RequestHeader(value = "Authorization", required = false) String authHeader) {
    return "Authorization header seen by member-service: " + authHeader;
  }

  @PostMapping("/getmember")
  public GetMemberResponse post(@RequestBody GetMemberRequest webRequest) {
    var commandRequest = GetMemberCommandRequest.builder().email(webRequest.getEmail()).build();

    var commandResponse = commandExecutor.execute(GetMemberCommand.class, commandRequest);

    return GetMemberResponse.builder().password(commandResponse.getPassword()).name(commandResponse.getName()).email(commandResponse.getEmail()).build();
  }


  @GetMapping("/debug-gateway")
  public String debugGateway(
      @RequestHeader(value = "X-From-Gateway", required = false) String fromGateway
  ) {
    return "X-From-Gateway header = " + fromGateway;
  }

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
