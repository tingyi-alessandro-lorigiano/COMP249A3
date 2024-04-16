package testing;
/**
 * Represents a singly linked list.
 */

public class OurLinkedList {

    private Node head;
    //private Node tail;
    private int size;
    /**
     * Constructs an empty linked list.
     */
    public OurLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Adds a new node with the specified value at the beginning of the linked list.
     *
     * @param newValue The value to be added at the beginning of the linked list.
     */
    public void addAtHead(String newValue) {
        head = new Node(newValue, head);
        size++;
    }

    /**
     * Adds a new node with the specified value at the end of the linked list.
     * If the linked list is empty, adds the node at the head.
     *
     * @param newValue The value to be added at the end of the linked list.
     */
    public void addAtEnd(String newValue) {
        if (head == null) {
            addAtHead(newValue);
        } else {
            Node position = head;
            while (position.next != null) {
                position = position.next;
            }
            position.next = new Node(newValue, null);
            size++;
        } 
    }
    

    /**
     * Adds a new node with the specified value after the node containing the specified value.
     * If the specified value does not exist in the linked list, the new node is not added.
     *
     * @param value    The value after which the new node should be added.
     * @param newValue The value to be added.
     */
    public void addAfter(String value, String newValue) {
        Node position = head;
        while (position != null && position.data != value) {
            position = position.next;
        }
        if (position != null) {
            position.next = new Node(newValue, position.next);
            size++;
        }
    }

    /**
     * Removes and returns the value at the head of the linked list.
     *
     * @return The value removed from the head of the linked list, or null if the list is empty.
     */
    public String removeHead() {
        if (head == null) {
            return null;
        } else {
            Node temp = head;
            head = head.next;
            size--;
            return temp.data;
        }
    }

    /**
     * Removes and returns the value at the end of the linked list.
     *
     * @return The value removed from the end of the linked list, or null if the list is empty.
     */
    public String removeEnd() {
        if (head == null) {
            return null;
        } else if (size == 1) {
            String value = head.data;
            head = null;
            size--;
            return value;
        } else {
            Node position = head;
            while (position.next.next != null) {
                position = position.next;
            }
            String value = position.next.data;
            position.next = null;
            size--;
            return value;
        }
    }

    /**
     * Removes the first occurrence of the specified value from the linked list.
     *
     * @param valueToRemove The value to be removed from the linked list.
     * @return The value removed from the linked list, or null if the specified value is not found.
     */
    public String removeValue(String valueToRemove) {
        if (head == null) {
            return null;
        } else if (head.data == valueToRemove) {
            Node temp = head;
            head = head.next;
            size--;
            return temp.data;
        } else {
            Node position = head;
            while (position.next != null && position.next.data != valueToRemove) {
                position = position.next;
            }
            if (position.next != null) {
                Node temp = position.next;
                position.next = position.next.next;
                size--;
                return temp.data;
            } else {
                return null;
            }
        }
    }

    /**
     * Removes and returns the value after the first occurrence of the specified value in the linked list.
     *
     * @param data The value after which the value to be removed is located.
     * @return The value removed from the linked list, or null if the specified value is not found or if there are less than 2 elements in the list.
     */
    public String removeAfter(String data) {
        if (size < 2) {
            return null;
        } else {
            Node position = head;
            while (position != null && position.data != data) {
                position = position.next;
            }
            if (position == null || position.next == null) {
                return null;
            } else {
                String tempValue = position.next.data;
                position.next = position.next.next;
                return tempValue;
            }
        }
    }

    /**
     * Retrieves the number of nodes in the linked list.
     *
     * @return The number of nodes in the linked list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Displays the content of the linked list.
     * If the list is empty, prints a message indicating so.
     */
    public void display() {
        if (size == 0) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Your list has " + size + " element(s): ");
            Node position = head;
            while (position != null) {
                System.out.println(position.data);
                position = position.next;
            }
        }
    }
    
    /**
     * Retrieves the first word stored in the linked list.
     *
     * @return The first word stored in the linked list, or null if the list is empty.
     */
    public String getFirstWord() {
        if (head == null) {
            return null; // List is empty
        }
        return head.getWord(); // Assuming getWord() method is defined in Node class
    }
    /**
     * Retrieves the head node of the linked list.
     *
     * @return The head node of the linked list.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Represents a node in a linked list.
     */
    class Node {

        /** Reference to the next node in the linked list. */
        private Node next;
        /** Data stored in the node. */
        private String data;
        /**
         * Constructs a node with null data and null reference to the next node.
         */
        public Node() {
        	data = null;
            next = null;
        }
        /**
         * Constructs a node with specified data and reference to the next node.
         *
         * @param data The data to be stored in the node.
         * @param next The reference to the next node.
         */
        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * Retrieves the data stored in the node.
         *
         * @return The data stored in the node.
         */
        public String getData() {
            return data;
        }
        /**
         * Sets the data stored in the node.
         *
         * @param data The data to be stored in the node.
         */
        public void setData(String data) {
            this.data = data;
        }
        /**
         * Retrieves the reference to the next node.
         *
         * @return The reference to the next node.
         */
        public Node getNext() {
            return next;
        }
        /**
         * Retrieves the word stored in the node.
         *
         * @return The word stored in the node.
         */
        public String getWord() {
            return data;
        }

    }
}