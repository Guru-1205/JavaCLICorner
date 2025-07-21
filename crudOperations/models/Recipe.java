package crudOperations.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Map<String, String> ingredients;
    private String description;
    private String username; // Username of the creator

    public Recipe(String name, Map<String, String> ingredients, String description, String username) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Recipe recipe = (Recipe) o;
        return name.equals(recipe.name) && username.equals(recipe.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
