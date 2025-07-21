package Menus;

import java.util.Scanner;
import java.util.UUID;

import Controllers.CategoryController;
import Models.enums.CategoryType;

public class CategoryMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void menu(UUID userId) {
        System.out.print("\nCategory Management Menu:");
        while (true) {
            System.out.print("\n1. Add Category");
            System.out.print("\n2. View Categories");
            System.out.print("\n3. Edit Category");
            System.out.print("\n4. Delete Category");
            System.out.print("\n5. Back to Main Menu");
            int userOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (userOption) {
                case 1 -> {
                    System.out.print("\nEnter category name: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("\nEnter category type (INCOME/EXPENSE): ");
                    String categoryTypeInput = scanner.nextLine().toUpperCase();
                    CategoryType categoryType;
                    try {
                        categoryType = CategoryType.valueOf(categoryTypeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category type. Please enter INCOME or EXPENSE.");
                        continue;
                    }
                    System.out.print("\n is this a subcategory? (yes/no): ");
                    String isSubcategory = scanner.nextLine().toLowerCase();
                    if (isSubcategory.equals("yes")) {
                        System.out.print("\nEnter parent category name: ");
                        String parentCategoryName = scanner.nextLine();
                        CategoryController.addSubCategory(categoryType, categoryName, parentCategoryName);
                        System.out.println("Subcategory added successfully under " + parentCategoryName);
                    } else if (isSubcategory.equals("no")) {
                        CategoryController.addCategory(categoryType, categoryName);
                        System.out.println("Category added successfully: " + categoryName);
                    }
                }
                case 2 -> CategoryController.viewCategories();
                case 3 -> {
                    System.out.print("\nEnter category name to edit: ");
                    String categoryName = scanner.nextLine();
                    System.out.print("\nEnter new category name: ");
                    String newCategoryName = scanner.nextLine();
                    System.out.print("\nEnter new category type (INCOME/EXPENSE): ");
                    String newCategoryTypeInput = scanner.nextLine().toUpperCase();
                    CategoryType newCategoryType;
                    try {
                        newCategoryType = CategoryType.valueOf(newCategoryTypeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category type. Please enter INCOME or EXPENSE.");
                        continue;
                    }
                    if (CategoryController.editCategory(categoryName, newCategoryName, newCategoryType)) {
                        System.out.println("Category edited successfully.");
                    } else {
                        System.out.println("Failed to edit category.");
                    }
                }
                case 4 -> {
                    System.out.print("\nEnter category name to delete: ");
                    String categoryName = scanner.nextLine();
                    if (CategoryController.deleteCategory(categoryName)) {
                        System.out.println("Category deleted successfully.");
                    } else {
                        System.out.println("Failed to delete category.");
                    }
                }
                case 5 -> {
                    System.out.println("Returning to main menu...");
                    return;
                }
            }
        }
    }
}
