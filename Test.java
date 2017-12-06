package Project2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;

/**
 * Project 2 - Java  
 * The Test class offers tests for the interfaces implemented
 * in the MyDataFrame and MyPandas classes. It prints these tests to both the
 * console and methodTests.txt.
 * 
 * @author Brooke Kennedy
 * @author Jamie Chen
 * @since 2017-12-05
 */
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

		//create dataframe from first and second file in list and concat them
		MyDataFrame first = MyPandas.readCSV(path +"\\"+ files.get(0));
		MyDataFrame sec = MyPandas.readCSV(path +"\\"+ files.get(1));
		MyDataFrame df = MyPandas.concat(first, sec);
		//loop through the rest of the files
		for(int i = 2; i < files.size(); i++) {
			//concat all files into one
			df = MyPandas.concat(df, MyPandas.readCSV(path +"\\"+ files.get(i)));
		}
		return df;
	}

	public static void main(String[] args) throws IOException {
		
		//all tests will be printed in this file
		PrintWriter writer = new PrintWriter("methodTests.txt", "UTF-8");
		
		//test getFiles
		String directoryPath = "C:\\Users\\bck00\\Documents\\Fall 2017\\Java and Python\\proj2\\babynames";
		ArrayList<String> myFiles = getFiles(directoryPath);
		//System.out.println(Arrays.toString(myFiles.toArray()));
		
		//test loadBabyNames (this tests readCSV and concat)
		MyDataFrame baby = loadBabyNames(myFiles, directoryPath);
		System.out.println("Dataframe size after loading all states:");
		writer.println("Dataframe size after loading all states:");
		System.out.println(baby.babynames.size());
		writer.println(baby.babynames.size());
		
		//test head
		MyDataFrame babyhead = baby.head(5);
		System.out.println("Testing Head(5):");
		writer.println("\nTesting Head(5)");
		System.out.println(babyhead.babynames);
		writer.println(babyhead.babynames);

		//test tail
		MyDataFrame babytail = baby.tail(5);
		System.out.println("Testing Tail(5):");
		writer.println("\nTesting Tail(5):");
		System.out.println(babytail.babynames);
		writer.println(babytail.babynames);
		
		//test dType on index
		String typeIndex = baby.dType(2);
		System.out.println("The type of index 2 (year) is:");
		writer.println("\nThe type of index 2 (year) is:");
		System.out.println(typeIndex);
		writer.println(typeIndex);
		
		//test dType on name
		String typeName = baby.dType("name");
		System.out.println("The type of name column is:");
		writer.println("\nThe type of name column is:");
		System.out.println(typeName);
		writer.println(typeName);
		
		//test slice on index (gender)
		MyDataFrame genderslice = baby.slice(1);
		System.out.println("Test slice on index (gender):");
		writer.println("\nTest slice on index (gender):");
		//print first element in slice
		System.out.println(genderslice.babynames.get(0));
		writer.println(genderslice.babynames.get(0));
		
		//test slice on name (name)
		MyDataFrame nameslice = baby.slice("name");
		System.out.println("Test slice on name:");
		writer.println("\nTest slice on name:");
		//print first element of slice
		System.out.println(nameslice.babynames.get(0));
		writer.println(nameslice.babynames.get(0));
		
		//test slice on index array ([1,3] (gender, name))
		int[] indexArr = new int[] {1,3};
		MyDataFrame indArrSlice = baby.slice(indexArr);
		System.out.println("Test slices on index array (1,3):");
		writer.println("\nTest slices on index array (1,3):");
		MyDataFrame topFiveSlice = indArrSlice.head(5);
		System.out.println(topFiveSlice.babynames);
		writer.println(topFiveSlice.babynames);
		
		//test slice on name array (["state", "year", "name", "count"])
		String[] nameArr = new String[] {"state", "year", "name", "count"};
		MyDataFrame nameArrSlice = baby.slice(nameArr);
		System.out.println("Test slices on name array ([\"state\", \"year\", \"name\", \"count\"]):");
		writer.println("\nTest slices on name array ([\"state\", \"year\", \"name\", \"count\"]):");
		MyDataFrame topSlice = nameArrSlice.head(5);
		System.out.println(topSlice.babynames);
		writer.println(topSlice.babynames);
		
		//test filter on name = "Brooke"
		MyDataFrame brooke = baby.filter("name", "=", "Brooke");
		MyDataFrame topBrooke = brooke.head(5);
		System.out.println("Filter on name = \"Brooke\":");
		writer.println("\nFilter on name = \"Brooke\":");
		System.out.println(topBrooke.babynames);
		writer.println(topBrooke.babynames);
		MyDataFrame brooke1995 = brooke.filter("year", "=", 1995).head(5);
		System.out.println("Filter on name = Brooke and year = 1995");
		writer.println("\nFilter on name = Brooke and year = 1995");
		System.out.println(brooke1995.babynames);
		writer.println(brooke1995.babynames);
		MyDataFrame brookeCount = brooke1995.filter("count", ">", 50);
		System.out.println("Filter on name = Brooke, year = 1995, and count > 50:");
		writer.println("\nFilter on name = Brooke, year = 1995, and count > 50:");
		System.out.println(brookeCount.babynames);
		writer.println(brookeCount.babynames);
		
		//test loc on index (1000000)
		MyDataFrame locdf = baby.loc(1000000);
		System.out.println("Size of dataframe after loc(1,000,000):");
		writer.println("\nSize of dataframe after loc(1,000,000):");
		System.out.println(locdf.babynames.size());
		writer.println(locdf.babynames.size());
		
		//test loc from index 5 to index 10
		MyDataFrame locrange = baby.loc(5, 10);
		System.out.println("Dataframe after loc(5, 10):");
		writer.println("\nDataframe after loc(5, 10):");
		System.out.println(locrange.babynames);
		writer.println(locrange.babynames);
		
		//test getMin on index 2 (year)
		Object minYear = baby.getMin(2);
		System.out.println("The min year is:");
		writer.println("\nThe min year is:");
		System.out.println(minYear);
		writer.println(minYear);
		
		//test getMin on index 3 (name)
		Object minName = baby.getMin(3);
		System.out.println("The name with min length is:");
		writer.println("\nThe name with min length is:");
		System.out.println(minName);
		writer.println(minName);
		
		//test getMax on count
		Object maxCount = baby.getMax("count");
		System.out.println("The max count is:");
		writer.println("\nThe max count is:");
		System.out.println(maxCount);
		writer.println(maxCount);
	
		//test getMax on state
		Object maxSt = baby.getMax("state");
		System.out.println("The max state is:");
		writer.println("\nThe max state is:");
		System.out.println(maxSt);
		writer.println(maxSt);
		
		//test sort on count specified by index
		MyDataFrame smaller = baby.head(100);
		MyDataFrame sortedCount = smaller.sort(4);
		System.out.println("Dataframe after sorting by count on babynames.head(100):");
		writer.println("\nDataframe after sorting by count on babynames.head(100):");
		System.out.println(sortedCount.babynames);
		writer.println(sortedCount.babynames);
		
		//test sort on name specified by name
		MyDataFrame sortedName = smaller.sort("name");
		System.out.println("Dataframe after sorting by name on babynames.head(100):");
		writer.println("\nDataframe after sorting by name on babynames.head(100):");
		System.out.println(sortedName.babynames);
		writer.println(sortedName.babynames);
		
		//test write csv (write sortedName to csv)
		writer.println("\nWrite above dataframe to csv. (See 'sortedName.csv')");
		try {
			MyPandas.writeCSV(sortedName, "sortedName.csv");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		writer.close();
	}
}

