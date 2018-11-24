package model.domain;

public class Category {
    private String title;
    private String description;
    private Category superCategory;
    public Category(String title, String description) {
        setTitle(title);
        setDescription(description);
    }

    public Category(String title, String description, Category category) {
        setTitle(title);
        setDescription(description);
        setSuperCategory(category);
    }

    private void setSuperCategory(Category category) {
        this.superCategory = category;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Category getSuperCategory() {
        return superCategory;
    }
}
