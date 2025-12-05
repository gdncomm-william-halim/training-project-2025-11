package com.example.member.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {
  @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)          // <-- not allowed in request
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  private String name;
  private Double price;

  @Column(name = "code")
  private String uniqueValue;

  public Recipe() {
  }

  public Recipe(Long id, String name, Double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getUniqueValue() {
    return uniqueValue;
  }

  public void setUniqueValue(String uniqueValue) {
    this.uniqueValue = uniqueValue;
  }

}
