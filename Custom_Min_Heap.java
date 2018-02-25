

//Custom min heap using array

public class Custom_Min_Heap
{
	protected item heap[];
	protected int capacity;
	protected int currentIndex;
	protected int [] bookeep;

	public Custom_Min_Heap(int size) {
		this.capacity = size;
		this.currentIndex = 0;
		this.heap = new item [size];
		this.bookeep = new int [size+1]; //+1 to skip index zero value
	}
	
	public boolean isEmpty() {
		return currentIndex == 0;
	}
	
	public boolean isFull() {
		return currentIndex == capacity;
	}
	
	// add element
	public void add(item vertex) {
		if (!isFull()) {
			heap[currentIndex] = vertex;
			bookeep[vertex.V]  = currentIndex;
			heapifyUp(currentIndex,vertex.key);
			currentIndex++;
		}else throw new IndexOutOfBoundsException("OVERFLOW");	
	}
	
	// get the min value and remove it
	public item extract_min () {
		if(!isEmpty()) {
			item min = heap[0];	//get min
			int weight = heap[currentIndex-1].key; //get last node key
			//switch root and last node
			swap(0,currentIndex-1);
			//assign last node (min) to null
			heap[currentIndex-1] = null;
			currentIndex--;
			heapifyDown(0, weight);
			return min;
		}else throw new IndexOutOfBoundsException("OVERFLOW");		
	}
	
	// swap two vertex
	protected void swap(int pos1, int pos2) {
		//swap index info first
		bookeep[heap[pos1].V] = pos2;
		bookeep[heap[pos2].V] = pos1;
		//swap content next
		item temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}

	// shift vertex up
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
	
	// shift vertex down
	public void heapifyDown (int index, int weight) {
		int left;
		int right;
		while (index < currentIndex) {
			 if (!(hasLeftChild(index)) && !(hasRightChild(index))) {return;}
			 else if(hasLeftChild(index) && !(hasRightChild(index))) {
				//only has a left child -- leaf condition
				left = getLeftChildIndex(index);
				if(heap[index].key > heap[left].key) {
					swap(left,index);
					index = left;
				} else return ;
				
			}else {		//not a leaf condition
				left = getLeftChildIndex(index);
				right = getRightChildIndex(index);
				if(heap[right].key <= heap[left].key && weight > heap[right].key) {
					swap(right,index);
					index = right;
				}else if (heap[right].key > heap[left].key && weight > heap[left].key) {
					swap(left,index);
					index = left;
				}else return ;
			}
		}
		return ;
	}
	
	public void decreaseKey(int i, int weight) {
	    if (heap[bookeep[i]].key < weight) {

	        throw new IllegalArgumentException("Key is larger than original key.");
	    }
	    heap[bookeep[i]].key = weight;
	    heapifyUp(bookeep[i],weight);
	    heapifyDown(bookeep[i],weight);
	}
	public int getVertexIndex (int vert) {
		return bookeep[vert];
	}
	private int getParentIndex(int childIndex) {
	    return (childIndex-1) / 2;
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