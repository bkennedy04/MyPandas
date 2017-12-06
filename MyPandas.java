package Project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyPandas {
	
	public static MyDataFrame readCSV(String path) throws IOException {
		
		String[] headers = {"State", "Gender", "Year", "Name", "Count"};
		
		//create class of myDataFrame
		MyDataFrame mydf= new MyDataFrame(headers);
		
		// File to read in, path has the file location and name
		File file = new File(path);
		
		// Read file into the babynames arrayList created in MyDataFrame class
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				// Parse line into separate columns
				String[] data = line.split(",");
				String state = data[0];
				String gender = data[1];
				int year = Integer.parseInt(data[2]);
				String name = data[3];
				int births = Integer.parseInt(data[4]);
				
				// From there, create object MyData
				MyData row = new MyData(state, gender, year, name, births);
				
				// Insert MyData into babynames
				mydf.babynames.add(row);
			}
		}
		
		return mydf;
	}
	
	public static void writeCSV(MyDataFrame data, String path) throws IOException {
		
		// File to write out, path has the file location and output file name
		FileWriter writer = new FileWriter(path);
		
		// Loop through and write each of the headers, if headers exists
		if(data.headers != null) {
			for (int i = 0; i < data.headers.length; i++) {
				writer.write(data.headers[i] + ",");
			}
		}
		writer.write("\n");
		// writer out each row of babynames;
		for (int i = 0; i < data.babynames.size(); i++) {
			writer.write(MyData.getState((MyData) data.babynames.get(i)) +","+ MyData.getGender((MyData) data.babynames.get(i)) +","+ MyData.getYear((MyData) data.babynames.get(i)) +","+ MyData.getName((MyData) data.babynames.get(i)) +","+ MyData.getCount((MyData) data.babynames.get(i)) +"\n");
		}
		writer.close();
	}
	
	public static MyDataFrame concat(MyDataFrame df1, MyDataFrame df2) {
		//create final_mdf
		String[] headers = {"State", "Gender", "Year", "Name", "Count"};
		MyDataFrame final_mdf = new MyDataFrame (headers);
		
		//Append each row in df1 to final_mdf
		for (int i = 0; i < df1.babynames.size(); i++) {
			final_mdf.babynames.add(df1.babynames.get(i));
		}
		
		//Append each row in df2 to final_mdf
		for (int i = 0; i < df2.babynames.size(); i++) {
			final_mdf.babynames.add(df2.babynames.get(i));
		}
		return final_mdf;
	}

}
