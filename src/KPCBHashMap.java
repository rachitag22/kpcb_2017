import java.util.Arrays;

public class KPCBHashMap<T> {

	public Node<String>[] keyTable;
	public Node<T>[] valueTable;

	private int size = 0;
	private int capacity = 0;
	
	public static class Node<T> {

		private T data;
		public Node<T> next;

		private Node(T data) {
			this.data = data;
			next = null;
		}

		@SuppressWarnings("unchecked")
		private static <T> Node<T>[] makeArray(int size) {
			return new Node[size];
		}
	}

	public KPCBHashMap(int size) {
		keyTable = Node.makeArray(size);
		valueTable = Node.makeArray(size);
		this.capacity = size;
	}

	public int getSize() {
		return size;
	}

	public boolean contains(String key) {

		if (!(containsHash(key))) {
			return false;
		}

		else {

			Node<String> curr = keyTable[hash(key)];

			while (curr != null) {

				if (curr.data.equals(key)) {
					return true;
				}

				curr = curr.next;
			}
			
			return false;
		}
	}

	public boolean containsHash(String key) {

		if (keyTable[hash(key)] == null) {
			return false;
		}

		return true;
	}
	
	public T get(String key) {
		
		if (!(contains(key))) {
			return null;
		}
		
		Node<String> curr = keyTable[hash(key)];
		Node<T> valueCurr = valueTable[hash(key)];
		
		int numNode = 0;
		
		while (!(curr.data.equals(key))) {
			curr = curr.next;
			numNode++;
		}
		
		int nodeCounter = 0;
		
		while (valueCurr != null) {
			if (nodeCounter == numNode) {
				return valueCurr.data;
			}

			nodeCounter++;
			valueCurr = valueCurr.next;
		}
		
		return null;		
	}
	
	public boolean set(String key, T value) {
		
		if (size >= capacity) {
			return false;
		}

		if (contains(key)) {

			Node<String> curr = keyTable[hash(key)];
			Node<T> valueCurr = valueTable[hash(key)];
			
			int numNode = 0;
			
			while (!(curr.data.equals(key))) {
				numNode++;
				curr = curr.next;
			}
			
			int nodeCounter = 0;
			
			while (valueCurr != null) {

				
				if (nodeCounter == numNode) {
					valueCurr.data = value;
					return true;
				}

				nodeCounter++;
				valueCurr = valueCurr.next;
			}

			
		}

		else if (!(containsHash(key))) {
			int hash = hash(key);

			keyTable[hash] = new Node<String>(key);
			valueTable[hash] = new Node<T>(value);
			
			size++;
			
			return true;
		}

		else {

			Node<String> curr = keyTable[hash(key)];

			while (curr != null) {

				if (curr.next == null) {
					curr.next = new Node<String>(key);
					break;
				}

				curr = curr.next;
			}

			Node<T> valueCurr = valueTable[hash(key)];

			while (valueCurr != null) {

				if (valueCurr.next == null) {

					valueCurr.next = new Node<T>(value);
					size++;
					return true;
				}

				valueCurr = valueCurr.next;
			}
		}
		
		return false;
	}

	public int hash(String key) {
		int p = 2147483647;
		int hash = Math.abs(p * key.hashCode()) % capacity;
		return hash;
	}

	public T delete(String key) {

		Node<String> prev = null;
		Node<String> curr = keyTable[hash(key)];
		
		Node<T> valuePrev = null;
		Node<T> valueCurr = valueTable[hash(key)];

		if (!(contains(key))) {
			return null;
		}

		if (curr.next == null) {

			keyTable[hash(key)] = null;
			valueTable[hash(key)] = null;
			size--;
			
			return valueCurr.data;
		}

		if (curr.data.equals(key)) {

			keyTable[hash(key)] = curr.next;
			valueTable[hash(key)] = valueCurr.next;
			size--;
			return valueCurr.data;
		}

		prev = curr;
		curr = curr.next;
		
		int numNode = 0;
		
		while (curr != null) {

			numNode++;
			
			if (curr.data.equals(key)) {
				prev.next = curr.next;
				size--;
			}

			prev = curr;
			curr = curr.next;
		}
		
		valuePrev = valueCurr;
		valueCurr = valueCurr.next;
		int nodeCounter = 0;
		
		while (valueCurr != null) {

			nodeCounter++;
			
			if (nodeCounter == numNode) {
				valuePrev.next = valueCurr.next;
				size--;
				return valueCurr.data;
			}

			valuePrev = valueCurr;
			valueCurr = valueCurr.next;
		}
		
		return null;
	}
	
	public float load() {
		return (float) size / capacity;
	}
	
	public void print() {
	
		System.out.println("HashMap");
		System.out.println("Size: " + size);
		System.out.println("Capacity: " + capacity);

		System.out.println("Key/Value Pairs:");
		for (int i = 0; i < keyTable.length; i++) {
			Node<String> keyCurr = keyTable[i];
			Node<T> valueCurr = valueTable[i];
			
			while (keyCurr != null && valueCurr != null) {
				System.out.println("(" + keyCurr.data + ", " + valueCurr.data + ")");
				keyCurr = keyCurr.next;
				valueCurr = valueCurr.next;
			}
		}
		
	}
	
}
