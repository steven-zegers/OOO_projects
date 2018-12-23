package model.domain;

/**
 * A Category is a way to group different questions which handle a similar subject matter.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
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
     * Creates a Category-object with a given title, description and super Category.
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
     * Sets the title of this Category.
     * @param title
     * The title as a String
     */

    private void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new DomainException("Title can't be empty");
        }
        if (title.contains(":")) throw new DomainException("Please do not use any ':' in your question.");
        this.title = title;
    }

    /**
     * Sets the description of this Category.
     * @param description
     * A String containing a description of this Category.
     */

    private void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new DomainException("Description can't be empty");
        }
        if (description.contains(":")) throw new DomainException("Please do not use any ':' in your question.");
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

    @Override
    public String toString() {
        String title = this.getTitle();
        String description = this.getDescription();
        String string = title + ": " + description;
        if (this instanceof SubCategory) {
            string += ": " + ((SubCategory) this).getSuperCategory().getTitle();
        }
        return string;
    }
}
