package model.domain;

public class SubCategory extends Category {
    private Category superCategory;
    public SubCategory(String title, String description, Category category) {
        super(title, description);
        setSuperCategory(category);
    }

    public Category getSuperCategory() {
        return superCategory;
    }
    public void setSuperCategory(Category superCategory) {
        if (superCategory.getTitle().equals(this.getTitle())) throw new DomainException("A category cannot be its own main category.");
        this.superCategory = superCategory;
    }
}
