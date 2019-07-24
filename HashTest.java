import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 
 * @author Alex Acevedo
 * 
 */

public class HashTest {
	
	private static int size, n, randomValue;
	private static long timeValue;
	private static int upperValueLimit = 96000;
	private static int lowerValueLimit = 95500;
	
	@SuppressWarnings("rawtypes")
	private static HashTable linearTable;
	@SuppressWarnings("rawtypes")
	private static HashTable doubleTable;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		//Uses prime factor tester to generate table size
		for (int i = lowerValueLimit; i < upperValueLimit; i++)
		{
			
			boolean firstValue = primeTest(i);
			boolean secondValue = primeTest(i+2);
			
			if (firstValue == true && secondValue == true)
			{
				
				upperValueLimit = i;
				size = i + 2;
				
			}
		}
		
		System.out.println("A good table size is found: " + size);
		
		double alpha = Double.parseDouble(args[1]);
		n = (int) ((alpha * size) + 1);
		
		
		/////////Command line argument section///////////
		
		/////// 1 for Random value
		if(Integer.parseInt(args[0]) == 1)
		{
			
			System.out.println("Data source type: Random Number \n");
			
			linearTable = new HashTable(n, size);
			doubleTable = new HashTable(n, size);
			
			while (linearTable.getObjectQuantity() <= n && doubleTable.getObjectQuantity() <= n) 
			{ 
				Random random = new Random();
				randomValue = random.nextInt();

				HashObject linearObject = new HashObject<Integer>(randomValue);
				HashObject doubleObject = new HashObject<Integer>(randomValue);

				linearTable.hashInserting(linearObject, 0);
				doubleTable.hashInserting(doubleObject, 1);

			}
			
			if (args.length == 2) 
			{
				
				System.out.println("Using Linear Hashing....");
				System.out.println("Input " + linearTable.getObjectQuantity() + " elements, of which "
						+ linearTable.getDuplicateCount() + " duplicates");
				System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
						+ linearTable.getAverageProbes() + "\n");

				System.out.println("Using Double Hashing....");
				System.out.println("Input " + doubleTable.getObjectQuantity() + " elements, of which "
						+ doubleTable.getDuplicateCount() + " duplicates");
				System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
						+ doubleTable.getAverageProbes());
			}

			if (args.length == 3) 
			{

				if (Integer.parseInt(args[2]) == 0) 
				{
					
					System.out.println("Using Linear Hashing....");
					System.out.println("Input " + linearTable.getObjectQuantity() + " elements, of which "
							+ linearTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ linearTable.getAverageProbes() + "\n");

					System.out.println("Using Double Hashing....");
					System.out.println("Input " + doubleTable.getObjectQuantity() + " elements, of which "
							+ doubleTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ doubleTable.getAverageProbes());

				}

				//Creates a file dump
				if (Integer.parseInt(args[2]) == 1) 
				{ 

					File linearDump = new File("linear-dump");
					File doubleDump = new File("double-dump");
					
					try {
						PrintWriter linearWriter = new PrintWriter(linearDump);
						PrintWriter doubleWriter = new PrintWriter(doubleDump);

						for (int i = 0; i < size; i++) 
						{
							if (linearTable.hashTable[i] != null) 
							{
								linearWriter.write("table[" + i + "]:" + linearTable.hashTable[i].toString() + '\n');
							}

							if (doubleTable.hashTable[i] != null) 
							{
								doubleWriter.write("table[" + i + "]:" + doubleTable.hashTable[i].toString() + '\n');
							}

						}
						
						linearWriter.close();
						doubleWriter.close();

					} 
					catch (IOException e) 
					{
						System.out.println("Error!");
						
					}

					System.out.println("Using Linear Hashing....");
					System.out.println("Input " + linearTable.getObjectQuantity() + " elements, of which "
							+ linearTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ linearTable.getAverageProbes() + "\n");

					System.out.println("Using Double Hashing....");
					System.out.println("Input " + doubleTable.getObjectQuantity() + " elements, of which "
							+ doubleTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ doubleTable.getAverageProbes());
				}

			}

		}



		///////2 for Current Time

		if (Integer.parseInt(args[0]) == 2) 
		{
			System.out.println("Data source type: Current Time   \n \n");

			linearTable = new HashTable(n, size);
			doubleTable = new HashTable(n, size);

			while (linearTable.getObjectQuantity() <= n && doubleTable.getObjectQuantity() <= n) 
			{
				timeValue = System.currentTimeMillis();
				HashObject linearObject = new HashObject<Long>(timeValue);
				HashObject doubleObject = new HashObject<Long>(timeValue);

				linearTable.hashInserting(linearObject, 0);
				doubleTable.hashInserting(doubleObject, 1);
			}

			if (args.length == 2) 
			{
				System.out.println("Using Linear Hashing....");
				System.out.println("Input " + linearTable.getInsert() + " elements, of which "
						+ linearTable.getDuplicateCount() + " duplicates");
				System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
						+ linearTable.getAverageProbes() + "\n");

				System.out.println("Using Double Hashing....");
				System.out.println("Input " + doubleTable.getInsert() + " elements, of which "
						+ doubleTable.getDuplicateCount() + " duplicates");
				System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
						+ doubleTable.getAverageProbes());
			}

			if (args.length == 3) 
			{

				if (Integer.parseInt(args[2]) == 0) 
				{
					System.out.println("Using Linear Hashing....");
					System.out.println("Input " + linearTable.getInsert() + " elements, of which "
							+ linearTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ linearTable.getAverageProbes() + "\n");

					System.out.println("Using Double Hashing....");
					System.out.println("Input " + doubleTable.getInsert() + " elements, of which "
							+ doubleTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ doubleTable.getAverageProbes());
				}

				if (Integer.parseInt(args[2]) == 1) 
				{

					File linearDump = new File("linear-dump");
					File doubleDump = new File("double-dump");
					try {
						PrintWriter linearWriter = new PrintWriter(linearDump);
						PrintWriter doubleWriter = new PrintWriter(doubleDump);

						for (int i = 0; i < size; i++) 
						{
							if (linearTable.hashTable[i] != null) 
							{
								linearWriter.write("table[" + i + "]:" + linearTable.hashTable[i].toString() + '\n');
							}

							if (doubleTable.hashTable[i] != null) 
							{
								doubleWriter.write("table[" + i + "]:" + doubleTable.hashTable[i].toString() + '\n');
							}

						}
						
						linearWriter.close();
						doubleWriter.close();

					} 
					
					catch (IOException e) 
					{
						System.out.println("Error!");
					}

					System.out.println("Using Linear Hashing....");
					System.out.println("Input " + linearTable.getInsert() + " elements, of which "
							+ linearTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ linearTable.getAverageProbes() + "\n");

					System.out.println("Using Double Hashing....");
					System.out.println("Input " + doubleTable.getInsert() + " elements, of which "
							+ doubleTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ doubleTable.getAverageProbes());
				}

			}
		}


		////////3 for word list 

		if (Integer.parseInt(args[0]) == 3) {

			System.out.println("Data source type: word-list \n \n");

			File file = new File("word-list");
			Scanner scan = null;
			try {
				scan = new Scanner(file);
			} catch (FileNotFoundException e) {
				System.out.println("Error!");
			}

			HashTable linearTable = new HashTable(n, size);
			HashTable doubleTable = new HashTable(n, size);

			while (scan.hasNextLine()) 
			{
				String fileLine = scan.nextLine();

				HashObject linearObject = new HashObject<String>(fileLine);
				HashObject doubleObject = new HashObject<String>(fileLine);

				if (linearTable.getObjectQuantity() < n) 
				{
					linearTable.hashInserting(linearObject, 0);
				}

				if (doubleTable.getObjectQuantity() < n) 
				{
					doubleTable.hashInserting(doubleObject, 1);
				}

			}

			if (args.length == 2) 
			{
				System.out.println("Using Linear Hashing....");
				System.out.println("Input " + linearTable.getInsert() + " elements, of which "
						+ linearTable.getDuplicateCount() + " duplicates");
				System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
						+ linearTable.getAverageProbes() + "\n");

				System.out.println("Using Double Hashing....");
				System.out.println("Input " + doubleTable.getInsert() + " elements, of which "
						+ doubleTable.getDuplicateCount() + " duplicates");
				System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
						+ doubleTable.getAverageProbes());
			}

			if (args.length == 3) 
			{

				if (Integer.parseInt(args[2]) == 0) 
				{

					System.out.println("Using Linear Hashing....");
					System.out.println("Input " + linearTable.getInsert() + " elements, of which "
							+ linearTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ linearTable.getAverageProbes() + "\n");

					System.out.println("Using Double Hashing....");
					System.out.println("Input " + doubleTable.getInsert() + " elements, of which "
							+ doubleTable.getDuplicateCount() + " duplicates");
					System.out.println("load factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ doubleTable.getAverageProbes());
				}

				if (Integer.parseInt(args[2]) == 1) 
				{

					try {
						PrintWriter linearWriter = new PrintWriter("linear-dump");
						PrintWriter doubleWriter = new PrintWriter("double-dump");

						for (int i = 0; i < size; i++) 
						{
							if (linearTable.hashTable[i] != null) 
							{
								linearWriter.write("table[" + i + "]:" + linearTable.hashTable[i].toString() + '\n');
							}

							if (doubleTable.hashTable[i] != null) 
							{
								doubleWriter.write("table[" + i + "]:" + doubleTable.hashTable[i].toString() + '\n');
							}
						}
						
						linearWriter.close();
						doubleWriter.close();

					} 
					catch (IOException e) 
					{
						System.out.println("Error!");
					}

					System.out.println("Using Linear Hashing....");
					System.out.println("Input " + linearTable.getInsert() + " elements, of which "
							+ linearTable.getDuplicateCount() + " duplicates");
					System.out.println("Load Factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ linearTable.getAverageProbes() + "\n");

					System.out.println("Using Double Hashing....");
					System.out.println("Input " + doubleTable.getInsert() + " elements, of which "
							+ doubleTable.getDuplicateCount() + " duplicates");
					System.out.println("Load Factor = " + Double.parseDouble(args[1]) + ", Avg. no. of probes "
							+ doubleTable.getAverageProbes());

				}

			}
		}

	}
			
				
	
	public static boolean primeTest(int number) {
		Random rand = new Random();
		int baseValue = rand.nextInt(number - 1) + 1;
		int powerValue = number - 1;

		String binaryValue = Integer.toBinaryString(powerValue);

		int count = 0;
		double res1 = 0;

		while (count < binaryValue.length()) 
		{
			if (count == 0) 
			{
				res1 = Math.pow(baseValue, 2) % number;
			} 
			else 
			{
				if (binaryValue.charAt(count) == '1') 
				{
					res1 = Math.pow(((res1 * baseValue) % number), 2);
				} 
				else 
				{
					res1 = Math.pow(res1 % number, 2);
				}
			}
			count++;
		}

		int baseValue2 = rand.nextInt(number - 1) + 1;

		count = 0;
		double res2 = 0;

		while (count < binaryValue.length()) 
		{
			if (count == 0) 
			{
				res2 = Math.pow(baseValue2, 2) % number;
			} 
			else 
			{
				if (binaryValue.charAt(count) == '1') 
				{
					res2 = Math.pow(((res2 * baseValue2) % number), 2);
				} 
				else 
				{
					res2 = Math.pow(res2 % number, 2);
				}
			}
			count++;

		}
		return (res1 == 1 && res2 == 1);
	}
	
	
}
