package model.domain;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class CategoryFactory {
    public Category createCategory(String name, String description,Category superCategory) {
        Category c = null;
        if (superCategory == null) {
            c = new Category(name, description);
        } else {
            c = new SubCategory(name, description, superCategory);
        }
        return c;
    }
}
