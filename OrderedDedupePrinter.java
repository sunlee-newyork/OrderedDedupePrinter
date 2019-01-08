import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

import com.hal.generator.StreamGenerator;

public class OrderedDedupePrinter {

	private static final StreamGenerator streamGenerator = new StreamGenerator();

	private static void filterAndPrint(InputStream inputStream) {
		int i;
		String c;
		HashMap<String,Boolean> dupeChecker = new HashMap<>();

		try {
			while ((i = inputStream.read()) != -1) {
				c = String.valueOf(i);

				if (dupeChecker.containsKey(c) == false) {
					System.out.println(c);
					dupeChecker.put(c, true);
				}
			}	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			InputStream inputStream = null;

			if (args.length == 0) {
				throw new Exception("At least one argument is required.");
			} 

			else if (args[0].equals("a")) {
				System.out.println("Running generateA()...");
				inputStream = streamGenerator.generateA();
				filterAndPrint(inputStream);
			} 

			else if (args[0].equals("b")) {
				System.out.println("Running generateB() with param " + args[1]);
				inputStream = streamGenerator.generateB(Integer.parseInt(args[1]));
				filterAndPrint(inputStream);
			}
		} catch (Throwable throwable) {
			System.out.println(throwable.toString());
		}
	}
}