package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.domain.Category;
import model.db.CategoryDB;

public class CategoryController {

	private CategoryDB categories = new CategoryDB();

	public ArrayList<Category> getCategoriesDB() {
		return this.categories;
	}

	public ObservableList<Category> getCategories() {
		ObservableList<Category> categories = FXCollections.observableArrayList();
		for (Category category : this.getCategoriesDB()) {
			categories.add(category);
		}
		return categories;
	}

	private class NewButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}