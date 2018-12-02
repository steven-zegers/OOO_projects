package model.db;

import model.domain.Category;
import model.domain.CategoryFactory;
import model.domain.Question;
import model.domain.SubCategory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CategoryDB contains all Categories to be used in tests. It is made via the Builder class of the same name.
 * It reads in the Categories stored in a text file and writes new ones to that file as well.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class CategoryDB implements Database<Category> {

    /**
     *  A List of Categories in which the Categories are stored when the program is running.
     */

    List<Category> categories;

    /**
     * The path where the file containing the Categories is located.
     */

    private String path = "src/model/db/groep.txt";


    /**
     * Creates a new CategoryDB by calling it's Builder class.
     * No parameter's are to be given, as the file path is defined statically.
     * Categories will be loaded into the list upon creation.
     */

	public CategoryDB() {
		this.categories = readItems(readFile());
		//readCategories();
	}

	public String getPath() {
	    return this.path;
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

	public List<String> getCategoryTitles() {
        List<String> titles = new ArrayList<>();
        for (Category category : categories) {
            titles.add(category.getTitle());
        }
        return titles;
    }
    @Override
    public List<String[]> readFile() {
        String line;
        List<String[]> linesInFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineString = line.split(": ");
                linesInFile.add(lineString);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesInFile;
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

    /**
     * Adds a category to the List of Categories and writes it to the text file for later use.
     * @param category
     * The Category that should be added to the database.
     */
    @Override
    public void addItem(Category category) {
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
            bufferedWriter.newLine();
            bufferedWriter.write(string);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the List of Categories.
     * @return
     * categories as a List of Category-objects
     */
    @Override
    public List<Category> getItems() {
            return this.categories;
    }
}