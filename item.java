public class item {
	//Vertex
		int V;
		//Key value of the vertex
		int key;
		
		public item (int V, int key) {
			this.V = V;
			this.key = key;
		}
		public String toString()
		{
			return String.format("%d - %d", V, key);
		}

}
