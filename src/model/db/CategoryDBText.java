package model.db;

import model.domain.Category;
import model.domain.CategoryFactory;
import model.domain.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * CategoryDBText contains all Categories to be used in tests. It is made via the Builder class of the same name.
 * It reads in the Categories stored in a text file and writes new ones to that file as well.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class CategoryDBText extends TextDatabase<Category> {

    private static CategoryDBText uniqueInstance = new CategoryDBText();

    /**
     *  A List of Categories in which the Categories are stored when the program is running.
     */

    //List<Category> categories;


    /**
     * Creates a new CategoryDBText by calling it's Builder class.
     * No parameter's are to be given, as the file path is defined statically.
     * Categories will be loaded into the list upon creation.
     */

	private CategoryDBText() {
	    super("groep.txt");
		//this.categories = readItems(readFile());
        this.uniqueInstance = this;
		//readCategories();
	}


    /**
     * Reads in all Categories already stored in the List of Categories.
     */

    private void readCategories() {
	    for (Category cat : this.getItems()) {
	        System.out.print(cat.getTitle() + ": " + cat.getDescription());
	        if (cat instanceof SubCategory) {
	            SubCategory subCategory = (SubCategory) cat;
                System.out.print(": " + subCategory.getSuperCategory().getTitle());
            }
        }
    }

    /**
     * Returns a specific Category, called by title. An exception will be thrown if the Category does not exist within the List.
     * @param title
     * The title of the Category which should be looked for
     * @return
     * The asked Category if it exists within the List
     */

    @Override
    public Category getItem(String title) {
		for (Category category : this.getItems()) {
		    System.out.println(category.getTitle().toLowerCase());
		    System.out.println(title.toLowerCase());
			if (category.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return category;
			}
		}
		throw new DbException("This title is not recognized as a saved category");
	}

    @Override
    protected boolean titleAlreadyExists(Category item) {
        return this.getTitles().contains(item.getTitle());
    }

    @Override
	public List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        for (Category category : this.getItems()) {
            titles.add(category.getTitle());
        }
        return titles;
    }

	@Override
    public List<Category> readItems(List<String[]> text) {
        List<Category> categories = new ArrayList<>();
        CategoryFactory factory = new CategoryFactory();
        for (String[] line : text) {
            if (line.length == 2) {
                Category category = factory.createCategory(line[0], line[1], null);
                categories.add(category);
            } else {
                boolean found = false;
                for (Category category : categories) {
                    if (category.getTitle().equals(line[2])) {
                        Category newCategory = factory.createCategory(line[0], line[1], category);
                        categories.add(newCategory);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new DbException("There is no such super category");
                }
            }
        }
        return categories;
    }

    public static CategoryDBText getInstance() {
        return uniqueInstance;
    }

}