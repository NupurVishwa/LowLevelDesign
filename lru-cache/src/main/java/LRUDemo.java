public class LRUDemo {

    public static void main(String[] args) {

        // Cache can store only 3 items
        LRUCache<Integer, String> cache =
                new LRUCache<>(3);


        System.out.println("===== INSERT 1 =====");

        cache.put(1, "A");
        cache.display();


        System.out.println("\n===== INSERT 2 =====");

        cache.put(2, "B");
        cache.display();


        System.out.println("\n===== INSERT 3 =====");

        cache.put(3, "C");
        cache.display();


        System.out.println("\n===== GET 1 =====");

        cache.get(1);
        cache.display();


        System.out.println("\n===== INSERT 4 =====");

        cache.put(4, "D");
        cache.display();


        System.out.println("\n===== GET 2 =====");

        cache.get(2);
        cache.display();


        System.out.println("\n===== INSERT 5 =====");

        cache.put(5, "E");
        cache.display();
    }
}