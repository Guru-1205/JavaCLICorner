package crudOperations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import crudOperations.models.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline left-over

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter valid username:");
                    String username = sc.nextLine();
                    System.out.println("Enter password:");
                    String password = sc.nextLine();
                    if (UserAuthentication.login(username, password)) {
                        System.out.println("Logged in successfully!!!");
                        // Call recipe management methods
                        recipeManagement(username, sc);
                    } else {
                        System.out.println("Incorrect username/password ... please try again");
                    }
                }
                case 2 -> {
                    System.out.println("Enter username for registration:");
                    String username = sc.nextLine();
                    System.out.println("Enter password:");
                    String password = sc.nextLine();
                    if (UserAuthentication.register(username, password)) {
                        System.out.println("Registered successfully");
                    } else {
                        System.out.println("Registration failed, please try again");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void recipeManagement(String username, Scanner sc) {
        while (true) {
            System.out.println("\nRecipe Management:");
            System.out.println("1. Insert Recipe");
            System.out.println("2. Delete Recipe");
            System.out.println("3. Search Recipe");
            System.out.println("4. Display My Recipes");
            System.out.println("5. Logout");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline left-over

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter recipe name:");
                    String name = sc.nextLine();
                    Map<String, String> ingredients = new HashMap<>();
                    System.out.println("Enter ingredients (type 'done' to finish):");
                    while (true) {
                        System.out.println("Enter ingredient name:");
                        String ingredientName = sc.nextLine();
                        if (ingredientName.equalsIgnoreCase("done")) {
                            break;
                        }
                        System.out.println("Enter quantity:");
                        String quantity = sc.nextLine();
                        ingredients.put(ingredientName, quantity);
                    }
                    System.out.println("Enter description:");
                    String description = sc.nextLine();
                    if (RecipeManager.addRecipe(name, ingredients, description, username)) {
                        System.out.println("Recipe added successfully.");
                    } else {
                        System.out.println("Recipe addition failed.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter recipe name to delete:");
                    String name = sc.nextLine();
                    if (RecipeManager.deleteRecipe(name, username)) {
                        System.out.println("Recipe deleted successfully.");
                    } else {
                        System.out.println("Recipe deletion failed.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter recipe name to search:");
                    String name = sc.nextLine();
                    Recipe recipe = RecipeManager.searchRecipe(name);
                    if (recipe != null) {
                        System.out.println("Recipe found: " + recipe);
                    } else {
                        System.out.println("Recipe not found.");
                    }
                }
                case 4 -> {
                    List<Recipe> userRecipes = RecipeManager.getUserRecipes(username);
                    System.out.println("Your recipes:");
                    for (Recipe recipe : userRecipes) {
                        System.out.println(recipe);
                    }
                }
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
