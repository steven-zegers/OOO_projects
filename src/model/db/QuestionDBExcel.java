package model.db;

import java.util.List;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class QuestionDBExcel extends ExcelDatabase {

	private static QuestionDBExcel uniqueInstance = new QuestionDBExcel();

	private QuestionDBExcel() {
		this.uniqueInstance = this;
	}

	@Override
	public List<String[]> readFile() {
		return null;
	}

	@Override
	public List<String> getTitles() {
		return null;
	}

	@Override
	public void addItem(Object item) {}

	@Override
	public Object getItem(String item) {
		return null;
	}

	@Override
	public void deleteItem(String title) {}

	@Override
	public List getItems() {
		return null;
	}

	@Override
	public List readItems(List text) {
		return null;
	}

	public static QuestionDBExcel getInstance() {
		return uniqueInstance;
	}

}
