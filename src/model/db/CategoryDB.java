package model.db;

import model.domain.Category;
import model.domain.SubCategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CategoryDB contains all Categories to be used in tests. It is made via the Builder class of the same name.
 * It reads in the Categories stored in a text file and writes new ones to that file as well.
 * @author
 */

public class CategoryDB extends ArrayList<Category> {

    /**
     *  A List of Categories in which the Categories are stored when the program is running.
     */

    List<Category> categories;

    /**
     * The path where the file containing the Categories is located.
     */

    private String path = "src/model/db/groep.txt";
	private static final long serialVersionUID = 1L;


    /**
     * Creates a new CategoryDB by calling it's Builder class.
     * No parameter's are to be given, as the file path is defined statically.
     * Categories will be loaded into the list upon creation.
     */

	public CategoryDB() {
		CategoryDbBuilder builder = new CategoryDbBuilder(path);
		this.categories = builder.readCategories();
		//readCategories();
	}

    /**
     * Reads in all Categories already stored in the List of Categories.
     */

    private void readCategories() {
	    for (Category cat : categories) {
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

    public Category getCategory(String title) {
		for (Category category : categories) {
			if (category.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return category;
			}
		}
		throw new DbException("This title is not recognized as a saved category");
	}

    /**
     * Returns the List of Categories.
     * @return
     * categories as a List of Category-objects
     */

	public List<Category> getCategories() {
	    return this.categories;
    }

    /**
     * Adds a category to the List of Categories and writes it to the text file for later use.
     * @param category
     * The Category that should be added to the database.
     */

    public void addCategory(Category category) {
	    categories.add(category);
        try {
            String title = category.getTitle();
            String description = category.getDescription();
            String string = title + ": " + description;
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (category instanceof SubCategory) {
                string += ": " + ((SubCategory) category).getSuperCategory().getTitle();
            }
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}