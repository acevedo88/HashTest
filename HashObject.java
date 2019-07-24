/*
 * 
 * @author Alex Acevedo
 *  
 */

public class HashObject<T>
{
	private int probeCount;
	private int duplicateCount;
	private T key;
	
	public HashObject(T object)
	{
		
		this.key = object;
		
	}
	
	public void incrementProbeCount()
	{
		
		probeCount++;
		
	}
	
	public void incrementDuplicateCount()
	{
		
		duplicateCount++;
		
	}
	
	public T getKey()
	{
		
		return this.key;
		
	}

	public int getProbeCount() 
	{
		
		return probeCount;
		
	}

	public int getDuplicateCount() 
	{
		
		return duplicateCount;
		
	}
	
	public T getObject()
	{
		
		return this.key;
		
	}
	
	public boolean isEqual(HashObject<T> element)
	{
		
		if (element.getKey().equals(key))
		{
			
			return true;
			
		}
		
		else 
		{
		
			return false;
			
		}
		
	}
	
	public String toString()
	{
		
		return " " + this.key.toString() + " " + duplicateCount + " " + probeCount; 
		
	}
	
	
}