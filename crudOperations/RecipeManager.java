package crudOperations;

import crudOperations.models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeManager {
    private static final String RECIPES_FILE = "E:/JAVA PT/Basics_Of_OOPs/JavaCLICorner/crudOperations/files/recipes.txt";
    private static List<Recipe> recipes = new ArrayList<>();

    static {
        loadRecipes();
    }

    private static void loadRecipes() {
        File file = new File(RECIPES_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            recipes = (List<Recipe>) ois.readObject();
        } catch (EOFException e) {
            // Ignore, file is empty
        } catch (FileNotFoundException e) {
            System.out.println("Recipes file not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading recipes: " + e.getMessage());
        }
    }

    private static void saveRecipes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RECIPES_FILE))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            System.out.println("Error writing recipes: " + e.getMessage());
        }
    }

    public static boolean addRecipe(String name, Map<String, String> ingredients, String description, String username) {
        Recipe newRecipe = new Recipe(name, ingredients, description, username);
        if (recipes.contains(newRecipe)) {
            System.out.println("Recipe already exists.");
            return false;
        }
        recipes.add(newRecipe);
        saveRecipes();
        return true;
    }

    public static boolean deleteRecipe(String name, String username) {
        Recipe recipeToDelete = new Recipe(name, null, null, username);
        if (recipes.remove(recipeToDelete)) {
            saveRecipes();
            return true;
        }
        System.out.println("Recipe not found or not created by you.");
        return false;
    }

    public static Recipe searchRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    public static List<Recipe> getUserRecipes(String username) {
        List<Recipe> userRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getUsername().equals(username)) {
                userRecipes.add(recipe);
            }
        }
        return userRecipes;
    }
}
