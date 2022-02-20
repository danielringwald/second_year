package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] array;
	private int size;
	private int capacity;

	public SimpleHashMap() {
		array = (Entry<K, V>[]) new Entry[16];
		capacity = 16;
		size = 0;
	}

	public SimpleHashMap(int capacity) {
		array = (Entry<K, V>[]) new Entry[capacity];
		this.capacity = capacity;
		size = 0;
	}

	@Override
	public V get(Object k) {
		// TODO Auto-generated method stub
		K key = (K) k;
		int index = Math.abs(k.hashCode() % capacity);

		if (array[index] == null) {
			return null;
		}

		Entry<K, V> e = array[index];

		while (!e.getKey().equals(k)) {
			e = e.next;
			if (e == null) {
				return null;
			}
		}

		if (e.getKey().equals(k)) {
			return e.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public V put(K k, V v) {
		// TODO Auto-generated method stub

		// om vi går över kapacitet kör vi rehash()
		if ((double) (size + 1) / (double) capacity > 0.75) {
			rehash();
		}

		
		//Anger index som plats det nya elementet ska läggas på
		int index = Math.abs(k.hashCode() % capacity);
		// Om indexplatsen är tom lägger vi bara in den
		if (array[index] == null) {
			Entry<K, V> e = new Entry<K, V>(k, v);
			array[index] = e;
			size++;
			return null;
		}

		Entry<K, V> e = array[index];
		// om först entryn e har samma key som det nya elementet vi försöker lägga in
		//Spara e's värde i value, byter värdet mot det nya värdet och sedan returnera value
		if (e.getKey().equals(k)) {
			V value = e.getValue();
			e.setValue(v);
			return value;
		}

		// Om nästa entry inte är null kollar vi om den är samma som det vi försöker
		// lägga in, ändrar enligt
		// regler, annars hoppas vi vidare till nästa entry
		while (e.next != null) {
			if (e.next.getKey().equals(k)) {
				V value = e.next.getValue();
				e.next.setValue(v);
				return value;
			}
			e = e.next;
		}

		// nu har vi kollat igenom alla Entry<K, V> och inte hittat en Entry med samma key,
		//då sätter vi in på next vår nya Entry och returnerar null
		e.next = new Entry<K, V>(k, v);
		size++;
		return null;
	}

	private void rehash() {
		size=0;
		//Dubbla kapaciteten
		capacity = capacity * 2;
		//Spara gammal vektor
		Entry<K, V>[] oldArray = array;
		//vektor-attributet blir en ny Entry-vektor med vår nya capacity
		array = (Entry<K, V>[]) new Entry[capacity];

		//Gå igenom alla index i gammal vektor
		//Om e är skilt från null använder vi put och sätter in i vår tomma vektor vi har i attributet
		//While-loop tills e blir null då går man till nästa index i oldArray
		for (int i = 0; i < oldArray.length; i++) {
			Entry<K, V> e = oldArray[i];
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}

	@Override
	public V remove(Object k) {
		// TODO Auto-generated method stub
		K key = (K) k;

		int index = key.hashCode() % capacity;
		Entry<K, V> e = array[index];

		// 1. Listan är null.
		if (e == null) {
			return null;
		}
//		2. key finns i det första elementet i listan.
		if (e.getKey().equals(key)) {
			V value = e.getValue();
			array[index] = e.next;
			size--;
			return value;
		}
//		3. key finns senare i listan.
//		4. key finns inte i listan.
		while (e.next != null) {
			if (e.next.getKey().equals(key)) {
				V value = e.next.getValue();
				e.next = e.next.next;
				size--;
				return value;
			}
			e = e.next;
		}
		return null;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public String show() {
		//Använder en StringBuilder, om e skilt från null, gå in i en while-loop och lägg till varje e
		//Skifta genom 
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < capacity; i++) {
			Entry<K, V> e = array[i];
			if (e != null) {
				sb.append(i + " ");
				while (e != null) {
					sb.append(e.toString() + " ");
					e = e.next;
				}
				sb.append("\n");
			}

		}
		return sb.toString();
	}

	private int index(K key) {
		return key.hashCode() % capacity;
	}

	private Entry<K, V> find(int index, K key) {
		if (array[index(key)].getKey().equals(key)) {
			return array[index(key)];
		} else if (array[index(key)].next == null) {
			return null;
		}
		return find(index, array[index(key)].next.getKey());
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			return this.value = value;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return key + " = " + value;
		}

	}

}
