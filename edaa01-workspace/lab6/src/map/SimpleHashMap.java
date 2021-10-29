package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	int size;
	Entry<K, V>[] table;
	double loadF;
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	public SimpleHashMap() {
		size = 0;
		table = (Entry<K,V>[]) new Entry[16];
		loadF = 0.75;
	}
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		size = 0;
		table = (Entry<K,V>[]) new Entry[capacity];
		loadF = 0.75;
	}

	
	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return this.value;
		}
		
		@Override
		public String toString() {
			return (getKey()+"="+getValue());
		}

	}

	@Override
	public V get(Object object) {
		if(find(index((K) object), (K) object) != null) {
		return find(index((K) object), (K) object).getValue();
		}else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		Entry<K,V> newEntry = new Entry<K,V>(key, value);
		Entry<K,V> entry = find(index, key);
		
		if(entry != null) {
			V temp = entry.getValue();
			entry.setValue(value);
			return temp;
		}
		else {
			newEntry.next = table[index];
			table[index] = newEntry;
			size++;
			rehash();
			return null;
		}
	}

	@Override
	public V remove(Object object) {
		int index = index((K) object);
		if(find(index, (K) object) == null) {
			return null;
		}else {
			Entry<K,V> temp = table[index];
			if(temp.getKey().equals((K) object)) {
				V removed = temp.getValue();
				table[index] = table[index].next;
				size--;
				return removed;
			}else {
			while(temp.next != null) {
				if(temp.next.getKey().equals((K) object)) {
					V removed = temp.next.getValue();
					temp.next = temp.next.next;
					size--;
					return removed;
					}
				temp = temp.next;
				}
			}
			return null;
		}
	}

	@Override
	public int size() {
		return this.size;
	}
	public String show() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i<table.length; i++) {
			builder.append(i+" ");
			builder.append(show(table[i])+"\n");
		}
		return builder.toString();
	}
	
	private String show(Entry<K, V> entry) {
		String temp = "";
		if(entry != null) {
			temp = show(entry.next);
			temp = (entry.toString()+" "+temp);
		}
		return temp;
	}
	
	private int index(K key) {
		int index = key.hashCode() % table.length;
		if (index < 0 ) {
		index = index + table.length;
		}
		return index;
	}
	
	//table = newTable, då kan man inte sätta temp = table[i] i forloopen
	private void rehash(){
		if(((double) size() / (double) table.length) > loadF) {
			Entry<K, V>[] oldTable = table;
			Entry<K, V>[] newTable = (Entry<K,V>[]) new Entry[table.length*2];
			table = newTable;
			size = 0;
			for(int i = 0; i<oldTable.length; i++) {
				Entry<K, V> temp = oldTable[i];
				while(temp != null) {
					put(temp.getKey(), temp.getValue());
					temp = temp.next;
				}
			}

		}
	}
	
	private Entry<K,V> find(int index, K key){
		Entry<K,V> temp = table[index];
		while(temp != null) {
			if(temp.getKey().equals(key)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}


}
