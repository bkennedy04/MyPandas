package Project2;

public class MyData {
	
	//variables of a single row
	public String state;
	public String gender;
	public int year;
	public String name;
	public int count;
		 
	//constructor to initialize row
	public MyData (String state, String gender, int year, String name, int count) { 
		this.state = state;
		this.gender = gender;
		this.year = year;
		this.name = name;
		this.count = count;
	 }
	
	public static String getState(MyData row) {
		return row.state;
	}
	public static String getGender(MyData row) {
		return row.gender;
	}
	public static int getYear(MyData row) {
		return row.year;
	}
	public static String getName(MyData row) {
		return row.name;
	}
	public static int getCount(MyData row) {
		return row.count;
	}
	
	//define how to print row
	@Override
	public String toString() {
		
		String print;
		 
		//ensure equal spacing
		if(name.length() >= 8) {
			print = this.state + "\t" + this.gender + "\t" + this.year + "\t" + this.name + "\t" + this.count + "\n";
		}
		else {
			print = this.state + "\t" + this.gender + "\t" + this.year + "\t" + this.name + "\t\t" + this.count + "\n";
		}
			 
		return print;
	}
	 
}