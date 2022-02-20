package map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleHashMap<Integer, Integer> m = new SimpleHashMap<Integer, Integer>(10);
		SimpleHashMap<Integer, Integer> m16 = new SimpleHashMap<Integer, Integer>();
		
		for (int i = 0; i < 40; i++) {
			m.put(i, i);
		}
		m.put(177, 177);
		m.put(149, 149);
		m.put(165,  165);
		m.put(88,  88);
		m.put(99,  99);
		m.put(141,  141);
		m.put(-14, -14);
		m.put(-18, 18);
		
		m16.put(1, 1);
		m16.put(17, 17);
		m16.put(33, 33);
		m16.put(49, 49);
		m16.put(65,  65);
		m16.put(3, 3);
		m16.put(55, 55);
		
		
		System.out.println(m.show());
		System.out.println(m16.show());
	}

}
       