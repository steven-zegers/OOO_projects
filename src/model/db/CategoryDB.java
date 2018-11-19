package model.db;

import model.domain.Category;

import java.util.ArrayList;

public class CategoryDB extends ArrayList<Category> {

	private static final long serialVersionUID = 1L;

	public CategoryDB() {
		this.add(new Category("Design principles", "The SOLID design principles"));
		this.add(new Category("Design patterns", "Design patterns discussed this year"));
		this.add(new Category("Java", "Java extra's"));
		this.add(new Category("UML", "Technique of drawing a class diagram"));
	}

	public Category getCategory(String title) {
		for (Category category : this) {
			if (category.getTitle().toLowerCase().equals(title.toLowerCase())) {
				return category;
			}
		}
		throw new DbException("This title is not recognized as a saved category");
	}

}