public class DoublyLinkedList<K, V> {

    private Node<K, V> head;
    private Node<K, V> tail;

    // Add a node at the front
    // Front = Most Recently Used
    public void addFirst(Node<K, V> node) {

        // If list is empty
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        // Connect new node with current head
        node.next = head;
        head.prev = node;

        // Make new node the head
        head = node;
    }

    // Move an existing node to the front
    public void moveToFront(Node<K, V> node) {

        // Already at front
        if (node == head) {
            return;
        }

        // Remove node from current position
        removeNode(node);

        // Add it at front
        addFirst(node);
    }

    // Remove a node from anywhere in the list
    public void removeNode(Node<K, V> node) {

        // Connect previous node to next node
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // Node is head
            head = node.next;
        }

        // Connect next node to previous node
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // Node is tail
            tail = node.prev;
        }

        // Clean references
        node.prev = null;
        node.next = null;
    }

    // Remove the least recently used node
    public Node<K, V> removeLast() {

        if (tail == null) {
            return null;
        }

        Node<K, V> lruNode = tail;

        removeNode(lruNode);

        return lruNode;
    }

    // Display cache order
    public void display() {

        Node<K, V> current = head;

        System.out.print("Cache Order: ");

        while (current != null) {
            System.out.print(
                    "[" + current.key + "=" + current.value + "]"
            );

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }
}