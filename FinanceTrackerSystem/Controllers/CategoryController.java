package Controllers;

import Models.enums.CategoryType;
import Models.Category;
import static Controllers.UserController.currentUser;

import java.util.UUID;

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

    public static boolean editCategory(String categoryName, String newCategoryName, CategoryType newCategoryType) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    category.setCategoryName(newCategoryName);
                    category.setCategoryType(newCategoryType);
                    if (UserController.saveUsersDetails()) {
                        System.out.println("Category edited successfully.");
                        return true;
                    } else {
                        System.out.println("Failed to save user details after editing category.");
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

    public static UUID getCategoryIdByName(String categoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    return category.getCategoryId();
                }
            }
            System.out.println("Category not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }

    // method to get the type of category by its name
    public static CategoryType getCategoryTypeByName(String categoryName) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryName().equals(categoryName)) {
                    return category.getCategoryType();
                }
            }
            System.out.println("Category not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }

    // method to get the category type by itss id
    public static CategoryType getCategoryTypeById(UUID categoryId) {
        if (currentUser != null) {
            for (Category category : currentUser.getCategories()) {
                if (category.getCategoryId().equals(categoryId)) {
                    return category.getCategoryType();
                }
            }
            System.out.println("Category not found.");
            return null;
        } else {
            System.out.println("User not found.");
            return null;
        }
    }
}