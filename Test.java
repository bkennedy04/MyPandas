package Project2;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;


public class Test {
	
	//get all txt files from a directory
	public static ArrayList<String> getFiles(String directoryPath) {
		ArrayList<String> textFiles = new ArrayList<String>();
		File dir = new File(directoryPath);
		for(File file: dir.listFiles()) {
			if(file.getName().toLowerCase().endsWith(".txt")) {
				textFiles.add(file.getName());
			}
		}
		return textFiles;
	}
	
	//create dataframe from all babynames files
	public static MyDataFrame loadBabyNames(ArrayList<String> files, String path) throws IOException {
		
		MyPandas pandas = new MyPandas();

		//create dataframe from first and second file in list and concat them
		MyDataFrame first = pandas.readCSV(path +"\\"+ files.get(0));
		MyDataFrame sec = pandas.readCSV(path +"\\"+ files.get(1));
		MyDataFrame df = pandas.concat(first, sec);
		//loop through the rest of the files
		for(int i = 2; i < files.size(); i++) {
			//concat all files into one
			df = pandas.concat(df, pandas.readCSV(path +"\\"+ files.get(i)));
		}
		return df;
	}

	public static void main(String[] args) throws IOException {
		
		//test getFiles
		String directoryPath = "C:\\Users\\bck00\\Documents\\Fall 2017\\Java and Python\\proj2\\babynames";
		ArrayList<String> myFiles = getFiles(directoryPath);
		//System.out.println(Arrays.toString(myFiles.toArray()));
		
		//test loadBabyNames
		MyDataFrame baby = loadBabyNames(myFiles, directoryPath);
		System.out.println("Dataframe size after loading all states:");
		System.out.println(baby.babynames.size());
		
		//test head
		MyDataFrame babyhead = baby.head(5);
		System.out.println("Testing Head(5)");
		System.out.println(babyhead.babynames);

		//test tail
		MyDataFrame babytail = baby.tail(5);
		System.out.println("Testing Tail(5)");
		System.out.println(babytail.babynames);
		//if you want to get rid of brackets and commas use this print method:
		//for(int i = 0; i < babytail.babynames.size(); i++) {
			//System.out.println(babytail.babynames.get(i));
		//}
		
		//test slice on index (gender)
		MyDataFrame genderslice = baby.slice(1);
		System.out.println("Test slice on index (gender):");
		//print first element in slice
		System.out.println(genderslice.babynames.get(0));
		
		//test slice on name (name)
		MyDataFrame nameslice = baby.slice("name");
		System.out.println("Test slice on name:");
		//print first element of slice
		System.out.println(nameslice.babynames.get(0));
		
		//test slice on index array ([1,3] (gender, name))
		int[] indexArr = new int[] {1,3};
		MyDataFrame indArrSlice = baby.slice(indexArr);
		System.out.println("Test slices on index array (1,3):");
		MyDataFrame topFiveSlice = indArrSlice.head(5);
		System.out.println(topFiveSlice.babynames);
		
		//select just one item from one row
		//System.out.println("Top name: " + ((List<?>) topFiveSlice.babynames.get(0)).get(1));
		
		//test slice on name array (["state", "year", "name", "count"])
		String[] nameArr = new String[] {"state", "year", "name", "count"};
		MyDataFrame nameArrSlice = baby.slice(nameArr);
		System.out.println("Test slices on name array ([\"state\", \"year\", \"name\", \"count\"]):");
		MyDataFrame topSlice = nameArrSlice.head(5);
		System.out.println(topSlice.babynames);
		
		//test filter on name = "Brooke"
		MyDataFrame brooke = baby.filter("name", "=", "Brooke");
		MyDataFrame topBrooke = brooke.head(5);
		System.out.println("Filter on name = \"Brooke\":");
		System.out.println(topBrooke.babynames);
		MyDataFrame brooke1995 = brooke.filter("year", "=", 1995).head(5);
		System.out.println("Filter on name = Brooke and year = 1995");
		System.out.println(brooke1995.babynames);
		
		//test loc on index (1000000)
		MyDataFrame locdf = baby.loc(1000000);
		System.out.println("Size of dataframe after loc(1,000,000):");
		System.out.println(locdf.babynames.size());
		
		//test loc from index 5 to index 10
		MyDataFrame locrange = baby.loc(5, 10);
		System.out.println("Size of dataframe after loc(5, 10):");
		System.out.println(locrange.babynames.size());
		
		//test getMin on index 2 (year)
		Object minYear = baby.getMin(2);
		System.out.println("The min year is:");
		System.out.println(minYear);
		
		//test getMin on index 3 (name)
		Object minName = baby.getMin(3);
		System.out.println("The name with min length is:");
		System.out.println(minName);
		
		//test getMax on count
		Object maxCount = baby.getMax("count");
		System.out.println("The max count is:");
		System.out.println(maxCount);
	
System.out.println("\n------------------------------------------------------------------------------------\n");

		//test readcsv
		MyPandas pandas = new MyPandas();
		MyDataFrame mdf = null;
		try {
			String path = "C:\\Users\\bck00\\Documents\\Fall 2017\\Java and Python\\proj2\\babynames\\AK.TXT";
			mdf = pandas.readCSV(path);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//for (int i = 0; i < 10; i++) {
			//System.out.println(mdf.babynames.get(i).toString());
		//}
		
		//test write Csv
		try {
			pandas.writeCSV(mdf, "newFileName.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test concatenate
		MyDataFrame mdf2 = null;
		
		try {
			String path = "C:\\Users\\bck00\\Documents\\Fall 2017\\Java and Python\\proj2\\babynames\\AL.TXT";
			mdf2 = pandas.readCSV(path);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MyDataFrame concat = pandas.concat(mdf, mdf2);
		for (int i = 0; i < 10; i++) {
			System.out.println(concat.babynames.get(i));
		}
		System.out.println(mdf.babynames.size());
		System.out.println(mdf2.babynames.size());
		System.out.println(concat.babynames.size());
	}

}

