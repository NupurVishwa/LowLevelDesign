import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    private final int capacity;

    // key -> Node
    private final Map<K, Node<K, V>> map;

    // Maintains LRU order
    private final DoublyLinkedList<K, V> list;


    public LRUCache(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than 0"
            );
        }

        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }


    // GET operation
    public V get(K key) {

        // Key doesn't exist
        if (!map.containsKey(key)) {

            System.out.println("GET " + key + " -> Cache Miss");

            return null;
        }

        // Get node from HashMap
        Node<K, V> node = map.get(key);

        // This node was recently used
        // Move it to front
        list.moveToFront(node);

        System.out.println(
                "GET " + key + " -> " + node.value
        );

        return node.value;
    }


    // PUT operation
    public void put(K key, V value) {

        // Check if key already exists
        if (map.containsKey(key)) {

            Node<K, V> existingNode = map.get(key);

            // Update value
            existingNode.value = value;

            // Mark as recently used
            list.moveToFront(existingNode);

            System.out.println(
                    "PUT " + key + "=" + value +
                            " -> Updated"
            );

            return;
        }


        // Create new node
        Node<K, V> newNode =
                new Node<>(key, value);

        // Add to HashMap
        map.put(key, newNode);

        // Add to front
        list.addFirst(newNode);

        System.out.println(
                "PUT " + key + "=" + value
        );


        // Check capacity
        if (map.size() > capacity) {

            // Remove least recently used node
            Node<K, V> lruNode = list.removeLast();

            // Remove it from HashMap
            map.remove(lruNode.key);

            System.out.println(
                    "Removed LRU: " +
                            lruNode.key + "=" +
                            lruNode.value
            );
        }
    }


    public void display() {
        list.display();
    }
}