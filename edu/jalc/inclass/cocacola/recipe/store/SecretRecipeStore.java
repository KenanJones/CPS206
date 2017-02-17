package edu.jalc.inclass.cocacola.recipe.store;

import edu.jalc.inclass.cocacola.security.PinNumber;
import edu.jalc.inclass.cocacola.recipe.Recipe;
import edu.jalc.inclass.cocacola.employee.Employee;
import java.util.HashMap;

/*

		This is a store of secret recipes.

		There can only be one instance of the store!
		The store houses a collection of recipes that require a pin number to access.
		This pin number belongs to an employee, so only that employee, using their pin, can access the recipe!
		Make sure your `getRecipe` method checks for the employee's pin against the recipes pin! If the pins do not
		match, throw an exception.
 */
public class SecretRecipeStore {

  private static SecretRecipeStore secretRecipeStore;
  private HashMap<Recipe,PinNumber> secretRecipies;

  private SecretRecipeStore(){
    secretRecipies = new HashMap();
  }

  public static SecretRecipeStore getInstance(){
    if(secretRecipeStore == null) secretRecipeStore = new SecretRecipeStore();
    return secretRecipeStore;
  }

  public void addRecipe(Employee employee, Recipe recipe){
    secretRecipies.put(recipe, employee.getPinNumber());
  }

  public Recipe getRecipe(String name, Employee employee)throws Exception{
    Recipe foundRecipe = null;
    for(Recipe recipe: secretRecipies.keySet()){
      if(recipe.getName().equals(name)){
        foundRecipe = recipe;
      }
    }
    if(foundRecipe == null) throw new Exception("Recipe not found");
    if(secretRecipies.get(foundRecipe) != employee.getPinNumber()){
      throw new Exception("pinNumbers do not match");
    }
    return foundRecipe;
  }
}
