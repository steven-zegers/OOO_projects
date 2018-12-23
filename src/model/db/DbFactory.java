package model.db;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public class DbFactory {

	public DbFactory() {

	}

	public Database getDatabase(DbType dbType) {
		Database output = null;
		switch (dbType) {
			case CATEGORYDBTEXT:
				output = CategoryDBText.getInstance();
				break;
			case QUESTIONDBTEXT:
				output = QuestionDBText.getInstance();
				break;
			case CATEGORYDBEXCEL:
				output = CategoryDBExcel.getInstance();
				break;
			case QUESTIONDBEXCEL:
				output = QuestionDBExcel.getInstance();
				break;
			default:
				throw new DbException("Database type doesn't exist");
		}
		return output;
	}

}
