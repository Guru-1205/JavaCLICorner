package Controllers;

import Models.enums.CategoryType;
import Models.Category;
import static Controllers.UserController.currentUser;

public class CategoryController {
    public static boolean addCategory(CategoryType categoryType, String categoryName) {
        if (currentUser != null) {
            Category newCategory = new Category(categoryType, categoryName, false, null);
            currentUser.getCategories().add(newCategory);
            if (UserController.saveUsersDetails()) {
                System.out.println("Category added successfully!");
                return true;
            } else {
                System.out.println("Failed to save user details after adding category.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    public static boolean addSubCategory(CategoryType categoryType, String categoryName, String parentCategoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(parentCategoryName)) {
                    Category newSubCategory = new Category(categoryType, categoryName, true, category);
                    currentUser.getCategories().add(newSubCategory);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Subcategory added successfully under " + parentCategoryName);
                        return true;
                    } else {
                        System.out.println("Failed to save user details after adding subcategory.");
                        return false;
                    }
                }
            }
            System.out.println("Parent category not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }

    public static void viewCategories() {
        if (currentUser != null) {
            if (currentUser.getCategories().isEmpty()) {
                System.out.println("No categories found.");
            } else {
                System.out.println("User Categories:");
                for (Category category : currentUser.getCategories()) {
                    System.out.println(category);
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public static boolean deleteCategory(String categoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    // if it's a main category, then look through all subcategories and change their
                    // parent to null and then remove the main category
                    if (!category.isSubCategory()) {
                        for (Category subCategory : currentUser.getCategories()) {
                            if (subCategory.getparentCategory() != null
                                    && subCategory.getparentCategory().getCategoryName().equals(categoryName)) {
                                subCategory.setparentCategory(null);
                                subCategory.setSubCategory(false);
                            }
                        }
                    }
                    currentUser.getCategories().remove(category);

                    if (UserController.saveUsersDetails()) {
                        System.out.println("Category deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after deleting category.");
                        return false;
                    }
                }
            }
            System.out.println("Category not found.");
            return false;
        } else {
            System.out.println("User not found.");
            return false;
        }
    }
}