package Project2;

import java.util.ArrayList;
import java.util.List;

public class MyDataFrame {
	
	//member variables
	String[] headers;
	public ArrayList<Object> babynames;
	
	//Construct MyDataFrame with header row;
	public MyDataFrame(String[] header) {
		babynames = new ArrayList<>();
		this.headers = header;
	}
	
	//Construct MyDataFrame without header row;
	public MyDataFrame() {
		babynames = new ArrayList<>();
	}
	
	//Returns the first n rows of the data
	public MyDataFrame head(int n) {
		MyDataFrame top = new MyDataFrame(headers);
		if(babynames.size() >= n) {
			for(int i = 0; i < n; i++) {
				top.babynames.add(babynames.get(i));
			}
		}
		return top;
	}
	
	//Returns the last n rows of the data
	public MyDataFrame tail(int n) {
		MyDataFrame end = new MyDataFrame(headers);
		//make sure you have enough elements
		if(babynames.size() >= n) {
			for(int i = 1; i <= n; i++) {
				end.babynames.add(babynames.get(babynames.size()-i));
			}
		}
		return end;
	}
	
	//Returns the type of the column specified by index.
	//If the type is not uniform, returns String.
	public String dType(int index) {
		String type = null;
		if(index == 2 || index == 4) {
			type = "int";
		}
		else if(index == 0 || index == 1 || index == 3){
			type = "String";
		}
		return type;
	}
	
	//Returns the type of the column specified by name.
	//If the type is not uniform, returns String.
	public String dType(String name) {
		String type = null;
		if(name.toLowerCase().equals("year") || name.toLowerCase().equals("count")) {
			type = "int";
		}
		else if(name.toLowerCase().equals("state") || name.toLowerCase().equals("gender") || name.toLowerCase().equals("name")) {
			type = "String";
		}
		return type;
	}
	
	//Returns the column specified by index
	public MyDataFrame slice(int index) {
		MyDataFrame sli = new MyDataFrame();
		
		switch(index) {
			case 0:
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getState((MyData) babynames.get(i)));
				}
				break;
				
			case 1:
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getGender((MyData) babynames.get(i)));
				}
				break;
				
			case 2:
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getYear((MyData) babynames.get(i)));
				}
				break;
				
			case 3:
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getName((MyData) babynames.get(i)));
				}
				break;
				
			case 4:
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getCount((MyData) babynames.get(i)));
				}
				break;
		}	
		return sli;
	}
	
	//Returns the column specified by name
	public MyDataFrame slice(String name) {
		MyDataFrame sli = new MyDataFrame();
		
		switch(name) {
			case "state":
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getState((MyData) babynames.get(i)));
				}
				break;
				
			case "gender":
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getGender((MyData) babynames.get(i)));
				}
				break;
				
			case "year":
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getYear((MyData) babynames.get(i)));
				}
				break;
				
			case "name":
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getName((MyData) babynames.get(i)));
				}
				break;
				
			case "count":
				for(int i = 0; i < babynames.size(); i++) {
					sli.babynames.add(MyData.getCount((MyData) babynames.get(i)));
				}
				break;
		}
		return sli;
	}
	
	//Returns the columns specified by an index array
	public MyDataFrame slice(int[] indexArr) {
		
		MyDataFrame sli = new MyDataFrame();
		int index;
		
		for(int i = 0; i < babynames.size(); i++) {
			List<Object> row = new ArrayList<Object>();
			for(int x = 0; x < indexArr.length; x++) {
				index = indexArr[x];
				
				switch(index) {
					case 0:
						row.add(MyData.getState((MyData) babynames.get(i)));
						break;
						
					case 1:
						row.add(MyData.getGender((MyData) babynames.get(i)));
						break;
						
					case 2:
						row.add(MyData.getYear((MyData) babynames.get(i)));
						break;
						
					case 3:
						row.add(MyData.getName((MyData) babynames.get(i)));
						break;
						
					case 4:
						row.add(MyData.getCount((MyData) babynames.get(i)));
						break;
				}		
			}
			sli.babynames.add(row);
		}
		return sli;
	}
	
	//Returns the columns specified by a name array
	public MyDataFrame slice(String[] nameArr) {
		
		MyDataFrame sli = new MyDataFrame();
		String col;
		
		for(int i = 0; i < babynames.size(); i++) {
			List<Object> row = new ArrayList<Object>();
			for(int x = 0; x < nameArr.length; x++) {
				col = nameArr[x];
				
				switch(col) {
					case "state":
						row.add(MyData.getState((MyData) babynames.get(i)));
						break;
						
					case "gender":
						row.add(MyData.getGender((MyData) babynames.get(i)));
						break;
						
					case "year":
						row.add(MyData.getYear((MyData) babynames.get(i)));
						break;
						
					case "name":
						row.add(MyData.getName((MyData) babynames.get(i)));
						break;
						
					case "count":
						row.add(MyData.getCount((MyData) babynames.get(i)));
						break;
				}		
			}
			sli.babynames.add(row);
		}
		return sli;
	}	
	
	//Returns data filtered by applying "col op o" on MyDataFrame object
	//col = {state, gender, year, name, count }
	//op = { =, <, >, <=, >= }
	//o must be of type col
	public MyDataFrame filter(String col, String op, Object o) {
		MyDataFrame filtered = new MyDataFrame();
		switch(col) {
			case "state":
				for(Object row: babynames) {
					String st = MyData.getState((MyData) row);	
					if(st.equals((String) o)) {
						filtered.babynames.add(row);
					}
				}
				break;
				
			case "gender":
				for(Object row: babynames) {
					String g = MyData.getGender((MyData) row);	
					if(g.equals((String) o)) {
						filtered.babynames.add(row);
					}
				}
				break;
				
			case "year":
				for(Object row: babynames) {
					int yr = MyData.getYear((MyData) row);
					switch(op) {
						case "=":
							if(yr == (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case "<":
							if(yr < (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case ">":
							if(yr > (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case "<=":
							if(yr <= (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case ">=":
							if(yr >= (int) o) {
								filtered.babynames.add(row);
							}
							break;
					}
				}
				break;
				
			case "name":
				for(Object row: babynames) {
					String n = MyData.getName((MyData) row);	
					if(n.equals((String) o)) {
						filtered.babynames.add(row);
					}
				}
				break;
				
			case "count":
				for(Object row: babynames) {
					int c = MyData.getCount((MyData) row);
					switch(op) {
						case "=":
							if(c == (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case "<":
							if(c < (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case ">":
							if(c > (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case "<=":
							if(c <= (int) o) {
								filtered.babynames.add(row);
							}
							break;
						case ">=":
							if(c >= (int) o) {
								filtered.babynames.add(row);
							}
							break;
					}
				}
				break;
		}
		return filtered;
	}
}



















