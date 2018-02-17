package DFS;

import java.util.NoSuchElementException;

//custom stack using array

public class Custom_Stack 
{
    private int arr[];
    private int size;
    private int index = 0;
	
	public Custom_Stack(int size)
	{
		this.size = size;
		arr = new int[size];
	}
	
    public int size() 
    {
        return index;
    }
    
	public boolean isFull()
	{
		return index == size;
	}
	
	public boolean isEmpty()
	{
		return index == 0;
	}
	
    public void push(int element) 
    {
        if (isFull()) 
        {
            throw new StackOverflowError("OVERFLOW");
        }

        arr[index] = element;
        index++;	
    }
    
    public int pop() 
    {
        if (isEmpty()) 
        {
            throw new NoSuchElementException("UNDERFLOW");
        }
        return arr[--index];
    }
}
