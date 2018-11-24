package model.db;

import model.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDB extends ArrayList<Category> {
    List<Category> categories;
	private static final long serialVersionUID = 1L;

	public CategoryDB() {
		CategoryDbBuilder builder = new CategoryDbBuilder("src/model/db/groep.txt");
		this.categories = builder.readCategories();
		//readCategories();
	}

    private void readCategories() {
	    for (Category cat : categories) {
	        System.out.print(cat.getTitle() + ": " + cat.getDescription());
	        if (cat.getSuperCategory() != null) {
                System.out.print(": " + cat.getSuperCategory().getTitle());
            }
        }
    }

    public Category getCategory(String title) {
	    System.out.println(title);
		for (Category category : categories) {
		    System.out.println(category.getTitle());
			if (category.getTitle().toLowerCase().equals(title.toLowerCase())) {
			    System.out.print("hello");
				return category;
			}
		}
		throw new DbException("This title is not recognized as a saved category");
	}

	public List<Category> getCategories() {
	    return this.categories;
    }

}