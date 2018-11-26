package model.db;

import model.domain.Category;
import model.domain.CategoryFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This Builder reads Categories from a text file to convert them into Category-objects. Afterwards a List of those objects will be returned.
 * @author Steven Zegers
 * @author Thibault Stroobants
 * @author Wout De Boeck
 */

public class CategoryDbBuilder {

    /**
     * The path where the text file containing the Categories is located.
     */

    private String path;

    /**
     * Creates a new Builder with the search path set to the given String
     * @param path
     * Path where the text file is located
     */

    public CategoryDbBuilder(String path) {
        this.setPath(path);
    }

    /**
     * Reads in all existing Categories from the text file and loads them into a List as Category-objects.
     * @return
     * A List of Category-objects.
     */

    public List<Category> readCategories() {
        List<Category> categories = new ArrayList<>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(this.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            CategoryFactory factory = new CategoryFactory();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineString = line.split(": ");
                if (lineString.length == 2) {
                    Category category = factory.createCategory(lineString[0], lineString[1], null);
                    categories.add(category);
                } else {
                    boolean found = false;
                    for (Category category : categories) {
                        if (category.getTitle().equals(lineString[2])) {
                            Category newCategory = factory.createCategory(lineString[0], lineString[1], category);
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
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categories;
    }

    /**
     * Returns the path of the text file containing the Categories.
     * @return
     * path as a String
     */

    public String getPath() {
        return path;
    }

    /**
     * Sets the file path to the given String.
     * @param path
     * The path as a String
     */

    public void setPath(String path) {
        this.path = path;
    }
}
