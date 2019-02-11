package jp.co.softbank.trackproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
  private String title;
  
  private String makingTime;
  
  private String serves;
  
  private String ingredients;
  
  private int cost;
}
