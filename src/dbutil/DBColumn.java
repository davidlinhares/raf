package dbutil;

public class DBColumn {
	private String name;
	private String type;
	private int size;
	
	public DBColumn(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		String result = "TEXT";
		switch(type) {
			case "int":
				result = "INTEGER";
				break;
			case "String":
				result = "VARCHAR(255)";
				break;
			case "boolean":
				result = "TINYINT(1)";
				break;
			default:
				break;
		}
		return result;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}
