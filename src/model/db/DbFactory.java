package model.db;

public class DbFactory {

	public DbFactory() {

	}

	public Database getDatabase(String className) {
		Database output;
		try {
			Class dbClass = Class.forName("model.db."+ className);
			Object dbObject = dbClass.newInstance();
			output = (Database) dbObject;
		} catch (Exception e) {
			throw new DbException(e);
		}
		return output;
	}

}
