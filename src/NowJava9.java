import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NowJava9 {

    public static void main(String[] args) throws IOException {

    	File file = new File("text.txt");

//		if (file.delete()) {
//			System.out.println(file.getName() + " is deleted!");
//		} else {
//			System.out.println("Delete operation is failed.");
//
//		}

    	FileWriter writer = new FileWriter("text.txt",true);
    	List<String> list = Arrays.asList("foo", "bar", "baz");
    	writer.write("Batch Migrated On \""+(getCurrentTimeStamp()).toString()+"\" --["+generateStringFromList(list)+"]");
    	writer.write("\n");
    	writer.write("\n");
    	
		writer.close();
        	
    }
 
    
	private static String generateStringFromList(List<String> usernames) {
		String joined = "'";
		joined = joined.concat(String.join("','", usernames));
		joined = joined.concat("'");
		return joined;
	}
    
	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
