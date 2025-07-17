package Models;

import java.util.UUID;

import Models.enums.CategoryType;

public class Category {
    private UUID categoryId;
    private CategoryType categoryType;
    private String categoryName;
    private boolean isSubCategory;
    private String parentCategoryName;

    // Parameterized constructor
    public Category(CategoryType categoryType, String categoryName, boolean isSubCategory, String parentCategoryName) {
        this.categoryId = UUID.randomUUID();
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.isSubCategory = isSubCategory;
        this.parentCategoryName = parentCategoryName;
    }

    // Getter for categoryId
    public UUID getCategoryId() {
        return categoryId;
    }

    // Getter for categoryType
    public CategoryType getCategoryType() {
        return categoryType;
    }

    // Setter for categoryType
    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    // Getter for categoryName
    public String getCategoryName() {
        return categoryName;
    }

    // Setter for categoryName
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Getter for isSubCategory
    public boolean isSubCategory() {
        return isSubCategory;
    }

    // Setter for isSubCategory
    public void setSubCategory(boolean isSubCategory) {
        this.isSubCategory = isSubCategory;
    }

    // Getter for parentCategoryName
    public String getParentCategoryName() {
        return parentCategoryName;
    }

    // Setter for parentCategoryName
    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    // Method to display category information
    @Override
    public String toString() {
        return "Category ID: " + categoryId + "\n" +
                "Category Type: " + categoryType + "\n" +
                "Category Name: " + categoryName + "\n" +
                "Is Sub-Category: " + isSubCategory + "\n" +
                "Parent Category Name: " + parentCategoryName;
    }

}
