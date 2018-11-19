package model.domain;

public class Category {
    private String title;
    private String description;
    private Category category;
    public Category(String title, String description, Category category) {
        setTitle(title);
        setDescription(description);
        setCategory(category);
    }

    private void setCategory(Category category) {
        this.category = category;
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

    public Category getCategory() {
        return category;
    }
}
