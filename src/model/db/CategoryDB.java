package model.db;

import model.domain.Category;
import model.domain.SubCategory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDB extends ArrayList<Category> {
    List<Category> categories;
    private String path = "src/model/db/groep.txt";
	private static final long serialVersionUID = 1L;

	public CategoryDB() {
		CategoryDbBuilder builder = new CategoryDbBuilder(path);
		this.categories = builder.readCategories();
		//readCategories();
	}

    private void readCategories() {
	    for (Category cat : categories) {
	        System.out.print(cat.getTitle() + ": " + cat.getDescription());
	        if (cat instanceof SubCategory) {
	            SubCategory subCategory = (SubCategory) cat;
                System.out.print(": " + subCategory.getSuperCategory().getTitle());
            }
        }
    }

    public Category getCategory(String title) {
		for (Category category : categories) {
			if (category.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return category;
			}
		}
		throw new DbException("This title is not recognized as a saved category");
	}

	public List<Category> getCategories() {
	    return this.categories;
    }

    public void addCategory(Category category) {
	    categories.add(category);
        try {
            String title = category.getTitle();
            String description = category.getDescription();
            String string = title + ": " + description;
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (category instanceof SubCategory) {
                string += ": " + ((SubCategory) category).getSuperCategory();
            }
            bufferedWriter.write(string);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}