import hw5.MyHashMap;

public class Test {
	public static void main(String[] args) {
		MyHashMap<String, String> map = new MyHashMap<String, String>();
		for (int i = 0; i < 100; i++) {
			map.put("k" + i, "added by iteration number:" + (i + 1));
		}

		System.out.println("map size: " + map.size());
		System.out.println("value for key 'k34' " + map.get("k34"));
		System.out.println("value for key 'k55' " + map.get("k55"));

		map.remove("k34");
		System.out.println("pare with key 'k34' removed");
		map.remove("k55");
		System.out.println("pare with key 'k55' removed");
		System.out.println("map size: " + map.size());

		map.put(null, "just no key");
		System.out.println("value for null key: " + map.get(null));

		map.put(null, "looks like colision");
		System.out.println("value for null key: " + map.get(null));
	}
}