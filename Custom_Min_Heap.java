import java.util.NoSuchElementException;

//Custom min heap using array

public class Custom_Min_Heap
{
	protected item heap[];
	protected int capacity;
	protected int currentIndex;

	public Custom_Min_Heap(int size) {
		this.capacity = size;
		this.currentIndex = 0;
		this.heap = new item [size];
	}
	
	public boolean isEmpty() {
		return currentIndex == 0;
	}
	
	public boolean isFull() {
		return currentIndex == capacity;
	}
	
	public void add(item vertex) {
		if (!isFull()) {
			heap[currentIndex] = vertex;
			heapifyUp(currentIndex,vertex.key);
			currentIndex++;
		}else throw new IndexOutOfBoundsException("OVERFLOW");	
	}
	
	public item extract_min () {
		if(!isEmpty()) {
			return heapifyDown();
		}else throw new IndexOutOfBoundsException("OVERFLOW");		
	}
	
	
	protected void swap(int pos1, int pos2) {
		item temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}
	
	public void heapifyUp (int index, int weight) {
		if(hasParent(index)) {
			int parentIndex = getParentIndex(index);
			while (weight < heap[parentIndex].key) {
				swap(index,parentIndex);		
				index = parentIndex;
				if(hasParent(index)) {
					parentIndex = getParentIndex(index);
				}else break;
			}
		}
	}
	
	public item heapifyDown () {
		int index = 0;
		int left;
		int right;
		item min = heap[0];	//get min
		int weight = heap[currentIndex-1].key; //get last node key
		
		//switch root and last node
		swap(0,currentIndex-1);
		//assign last node (min) to null
		heap[currentIndex-1] = null;
		currentIndex--;
		
		while (index < currentIndex) {
			 if (!(hasLeftChild(index)) && !(hasRightChild(index))) {return min;}
			 else if(hasLeftChild(index) && !(hasRightChild(index))) {
				//only has a left child -- leaf condition
				left = getLeftChildIndex(index);
				if(heap[index].key > heap[left].key) {
					swap(left,index);
					index = left;
				}else return min;
				
			}else{		//not a leaf condition
				left = getLeftChildIndex(index);
				right = getRightChildIndex(index);
				if(heap[right].key <= heap[left].key && weight > heap[right].key) {
					swap(right,index);
					index = right;
				}else if (heap[right].key > heap[left].key && weight > heap[left].key) {
					swap(left,index);
					index = left;
				}else return min;
			}
		}
		return min;
	}
	
	public int getParentIndex(int childIndex) {
		return (childIndex+1)/2-1;
	}
	public int getLeftChildIndex (int parentIndex) {
		return (parentIndex+1)*2-1;
	}
	public int getRightChildIndex (int parentIndex) {
		return (parentIndex+1)*2;
	}
	public boolean hasParent (int index) {
		return getParentIndex(index)>=0;
	}
	public boolean hasLeftChild (int index) {
		return getLeftChildIndex(index)< currentIndex;
	}
	public boolean hasRightChild (int index) {
		return getRightChildIndex(index)<currentIndex;
	}
	
}