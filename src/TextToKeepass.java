import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextToKeepass {
	
	private static int entries;
	private static final int DATA = 5;
	private static String ds[][];
	private static Path path;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		usage();
		
		Scanner scan = new Scanner(System.in);
		setUpDS(scan);
		getPath(scan);
		parseFile();
		writeFile(scan);
		
		scan.close();
	}
	
	private static void usage(){
		System.out.println("Usage: In your text file, each entry must follow the format: ");
		System.out.println("\"Account Title");
		System.out.println("Username");
		System.out.println("Password");
		System.out.println("Optional: Website");
		System.out.println("Optional: Comments\"");
		System.out.println();
	}
	private static void setUpDS(Scanner scan){
		System.out.println("Enter the number of entries to be created:");
		entries = Integer.parseInt(scan.nextLine());

		ds = new String[entries][DATA];
		
		for(int entry = 0; entry < entries; entry++){
			for( int dataint = 0; dataint< DATA; dataint++){
				ds[entry][dataint]= "";
			}
		}
		
	}
	private static void getPath(Scanner scan){
		System.out.println("Please enter the absolute path of the text document to convert");
		String loc = scan.nextLine();
		path = Paths.get(loc);

	}
	private static void parseFile(){

		try {
			String tmp = null; 
			
			BufferedReader read = Files.newBufferedReader(path);
			
			for( int i = 0; i < entries; i++){
				for( int x = 0; x < DATA; x++){
					
					tmp = read.readLine();
					if(tmp == null)
						break;
					
					if(tmp.equals(""))
						break;
					else
						ds[i][x] = tmp; 
					
				}
				
				if(tmp == null)
					break;
			}
			read.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Unable to open file");
		}
	}

	private static void writeFile(Scanner scan){
		System.out.println("Enter a name and absolute path for the new file:");
		String name = scan.nextLine();
		
		Path newPath = Paths.get(name);
		
		try {
			BufferedWriter bw = Files.newBufferedWriter(newPath);
			
			for(int numEntry = 0; numEntry < entries; numEntry++){
				String tmp = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", ds[numEntry][0],
						ds[numEntry][1], ds[numEntry][2], ds[numEntry][3], ds[numEntry][4]);
				bw.write(tmp);
				bw.newLine();
			}
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Unable to write to a new file");
		}
	}
}
