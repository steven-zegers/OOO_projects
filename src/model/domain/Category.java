package model.domain;

/**
 * A Category is a way to group different questions which handle a similar subject matter.
 * @author Steven Zegers
 */

public class Category {

    /**
     * The title of this Category.
     */

    private String title;

    /**
     * The description of this Category, giving some more in-depth information about it.
     */

    private String description;

    /**
     * The Category which this Category extends and further specifies.
     */

    private Category superCategory;

    /**
     * Creates a Category-object with a given title and description. It will not have a super Category.
     * @param title
     * The title of the Category, to be given as String
     * @param description
     * The description of the Category, to be given as String
     */

    public Category(String title, String description) {
        setTitle(title);
        setDescription(description);
    }

    /**
     * Creates a Category-object with a given title, description and super Category.
     * @param title
     * The title of the Category, to be given as String
     * @param description
     * The description of the Category, to be given as String
     * @param category
     * The existing Category which this new instance will further specify.
     */

    public Category(String title, String description, Category category) {
        setTitle(title);
        setDescription(description);
        setSuperCategory(category);
    }

    /**
     * Sets the super Category.
     * @param category
     * An existing Category object which this new instance will further specify.
     */

    private void setSuperCategory(Category category) {
        this.superCategory = category;
    }

    /**
     * Sets the title of this Category.
     * @param title
     * The title as a String
     */

    private void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the description of this Category.
     * @param description
     * A String containing a description of this Category.
     */

    private void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of this Category.
     * @return
     * category as a String
     */

    public String getDescription() {
        return description;
    }

    /**
     * Returns the title of this Category.
     * @return
     * title as a String
     */

    public String getTitle() {
        return title;
    }

    /**
     * Returns the super Category.
     * @return
     * superCategory as a Category
     */

    public Category getSuperCategory() {
        return superCategory;
    }
}
