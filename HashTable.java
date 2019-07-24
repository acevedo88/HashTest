/*
 * 
 * @author Alex Acevedo
 *  
 */

public class HashTable<T>{

	private int probeCount, duplicateCount, attemptedCount;
	private int insert, tableSize, objectQuantity;
	private double averageProbes;
	
	HashObject<T> hashTable[];
	
	@SuppressWarnings("unchecked")
	public HashTable (int objectQuantity, int tableSize) 
	{
		
		this.tableSize = tableSize;
		this.objectQuantity = 0;
		this.probeCount = 0;
		this.duplicateCount = 0;
		
		hashTable = new HashObject[tableSize];
		
	}

	public int getProbeCount() 
	{
		
		return probeCount;
		
	}

	public int getDuplicateCount() 
	{
		
		return duplicateCount;
		
	}

	public int getAttemptedCount() 
	{
		
		return attemptedCount;
		
	}

	public int getInsert() 
	{
		
		return insert;
		
	}

	public int getObjectQuantity() 
	{
		
		return objectQuantity;
		
	}

	public double getAverageProbes() 
	{
		
		this.averageProbes = (double)((double) probeCount / objectQuantity);
		return averageProbes;
		
	}
	
	public int linearHash(T key, int value)
	{
		
		int primaryHashValue = (key.hashCode()) % tableSize;
		
		//Checks if the value is negative
		if (primaryHashValue < 0)
		{
			
			primaryHashValue += tableSize;
			
		}
		
		//Hash Table index
		int linearValue = (primaryHashValue + value) % tableSize;
		
		//Another negative value check
		if (linearValue < 0)
		{
			
			linearValue += tableSize;
			
		}
		
		return linearValue;
		
	}
	
	public int doubleHash(T key, int value)
	{
		
		int primaryHashKey = key.hashCode() % (tableSize);
		
		//Checks if the value is negative
		if (primaryHashKey < 0)
		{
			
			primaryHashKey += tableSize;
			
		}
		
		int secondaryHashKey = key.hashCode() % (tableSize - 2);
		
		if (secondaryHashKey < 0)
		{
			
			secondaryHashKey += (tableSize - 2);
			
		}
		
		int secondHashKey = (1 + (secondaryHashKey));
		
		//Hash table index
		int doubleHashValue = (primaryHashKey + value * secondHashKey) % tableSize;
		
		
		//Another check for negative value
		if (doubleHashValue < 0)
		{
			
			doubleHashValue += tableSize;
			
		}
		
		
		return doubleHashValue;
		
	}
	
	//Attempts to probe table
	public void hashInserting(HashObject<T> object, int probeValue)
	{
		
		int i = 0;
		int j;
		insert++;
		attemptedCount++;
		
		while ( i < tableSize)
		{
			
			if (probeValue == 0)
			{
				
				j = linearHash(object.getKey(), i);
				
			}
			
			else if (probeValue ==1)
			{
				
				j = doubleHash(object.getKey(), i);
				
			}
			
			else
			{
				
				j = -1;
				
			}
			
			object.incrementProbeCount();
			
			if (hashTable[j] == null)
			{
				
				hashTable[j] = object;
				objectQuantity++;
				probeCount += i + 1;
				break;
				
			}
			
			else if (hashTable[j].isEqual(object))
			{
				
				duplicateCount++;
				hashTable[j].incrementDuplicateCount();
				break;
				
			}
			
			i++;
		}
		
	}
	
}