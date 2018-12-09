package model.db;

public class DbFactory {

	public DbFactory() {

	}

	public Database getDatabase(String className) {
		Database output = null;
		switch (className) {
			case "CategoryDBText":
				output = CategoryDBText.getInstance();
				break;
			case "QuestionDBText":
				output = QuestionDBText.getInstance();
				break;
			case "CategoryDBExcel":
				output = CategoryDBExcel.getInstance();
				break;
			case "QuestionDBExcel":
				output = QuestionDBExcel.getInstance();
				break;
			default:
				throw new DbException("Classname doesn't exist");
		}
		return output;
	}

}
