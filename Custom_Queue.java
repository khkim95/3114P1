import java.util.NoSuchElementException;

//Custom queue using array

public class Custom_Queue
{
	protected int front, rear, currentSize;
	protected int array[];
	private int capacity;
	
	public Custom_Queue(int size)
	{
		this.capacity = size;
		front = this.currentSize = 0;
		rear = capacity - 1;
		array = new int[this.capacity];
	}
	
	//function to check if queue is empty
	public boolean isEmpty()
	{
		return currentSize == 0;
	}
	
	//function to check if queue is full
	public boolean isFull()
	{
		return currentSize == capacity;
	}
	
	//function to add an element to the queue
	public void add(int item)
	{
		if(isFull())
			throw new IndexOutOfBoundsException("OVERFLOW");
		else
		{
			rear = (rear + 1) % capacity;
			array[rear] = item;
			currentSize++;
		//System.out.println(item + " is added to queue");
		}
	}
	
	//function to remove front element of the queue and return the element
	public int remove()
	{
		if(isEmpty())
			throw new NoSuchElementException("UNDERFLOW");
		else
		{
			int current = array[front];
			//System.out.println(current + " is removed from the queue");
			front = (front+1) % capacity;
			currentSize--;		
			return current;
		}
	}
}
