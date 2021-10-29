package map;

import java.util.Random;
public class Main {

	public static void main(String[] args) {
		Random rand = new Random(897432);
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>(10);
        for (int i = 0; i < 13; i++) {
            Integer r = rand.nextInt(10000);
            map.put(r, r);
        }
        map.put(141, 141);
        map.put(321, 321);
        // map.remove(3681);
        // map.remove(6709);
        // map.remove(141);
        System.out.println(map.show());
	}
}
