package testing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents a doubly linked list containing nodes, where each node holds a topic and a linked list of words.
 */
public class OurDLinkedList {
	
    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructs an empty doubly linked list.
     * Initializes the head and tail references to null and the size to 0.
     */
    public OurDLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Retrieves the head of the doubly linked list.
     * @return The head node of the list.
     */
 // Method to get the head of the doubly linked list
    public Node getHead() {
        return head;
    }
    /**
     * Retrieves the next node after the given node in the linked list.
     *
     * @param current The current node whose next node is to be retrieved.
     * @return The next node after the given node, or null if the current node is null or the last node.
     */
    public Node getNext(Node current) {
        return current != null ? current.after : null;
    }

    /**
     * Adds a new node with the given value at the head of the doubly linked list.
     *
     * @param newValue The value to be added at the head of the list.
     */    
    	public void addAtHead(String newValue) {
        if (size == 0) {
            head = new Node(newValue, null, null);
            tail = head;
        } else {
            Node oldHead = head;
            head = new Node(newValue, null, head);
            oldHead.before = head; // head.after.before
        }

        size++;
    }
    
    	/**
    	 * Adds a new node with the given value at the tail of the doubly linked list.
    	 *
    	 * @param newValue The value to be added at the tail of the list.
    	 */
    	public void addAtTail(String newValue) {
        if (size == 0) { //addAtHead(newValue);
            tail = new Node(newValue, null, null);
            head = tail;
        } else {
            Node oldTail = tail;
            tail = new Node(newValue, tail, null);
            oldTail.after = tail;
        }
        size++;
    }

    	/**
    	 * Adds a new node with the given value after the node containing the specified reference value in the doubly linked list.
    	 * If the reference value is not found, or the list is empty, no action is taken.
    	 *
    	 * @param referenceValue The value after which the new node is to be added.
    	 * @param newValue       The value to be added after the reference value.
    	 */
    	public void addAfter(String referenceValue, String newValue) {
        if (head == null) {
            return; // List is empty, nothing to add after
        } else {
            Node position = head;
            // Traverse the list until the referenceValue is found
            while (position != null && !position.topic.equals(referenceValue)) {
                position = position.after;
            }
            // If referenceValue is not found, or list is empty, do nothing
            if (position == null) {
                return;
            } else {
                // Create a new node with newValue
                Node newNode = new Node(newValue, position, position.after);
                
                // Prompt user to input words
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter words for the topic '" + newValue + "'. Enter blank line to finish:");
                String word = scanner.nextLine().trim();
                while (!word.isEmpty()) {
                    newNode.words.addAtEnd(word);
                    word = scanner.nextLine().trim();
                }
                
                // Adjust the references of surrounding nodes
                if (position == tail) {
                    addAtTail(newValue); // Add newValue at the tail
                } else {
                    position.after.before = newNode;
                    position.after = newNode;
                }
                // Increase the size of the list
                size++;
            }
        }
    }

    	/**
    	 * Retrieves the linked list containing words for the specified topic.
    	 *
    	 * @param topic The topic for which the linked list of words is to be retrieved.
    	 * @return The linked list containing words for the specified topic, or null if the topic is not found.
    	 */
    public OurLinkedList getWordsForTopic(String topic) {
        Node current = head;
        while (current != null) {
            if (current.getTopic().equals(topic)) {
                return current.words;
            }
            current = current.getNext();
        }
        return null; // Topic not found
    }
    
    
    
    // STUFF
 // Add before a value (going forward)
//    public void addBefore(String referenceValue, String newValue) {
//        if (head == null) {
//            return; // List is empty, nothing to add before
//        } else {
//            Node position = head;
//            // Traverse the list until the referenceValue is found
//            while (position != null && !position.topic.equals(referenceValue)) {
//                position = position.after;
//            }
//            // If referenceValue is not found, or list is empty, do nothing
//            if (position == null) {
//                return;
//            } else {
//                // If referenceValue is found at the head of the list
//                if (position == head) {
//                    addAtHead(newValue); // Add newValue at the head
//                } else {
//                    // Otherwise, create a new node with newValue
//                    // and insert it before the position node
//                    Node newNode = new Node(newValue, position.before, position);
//                    // Adjust the references of surrounding nodes
//                    position.before.after = newNode;
//                    position.before = newNode;
//                    // Increase the size of the list
//                    size++;
//                }
//            }
//        }
//    }
    /**
     * Adds a new node with the given value before the node containing the specified reference value in the doubly linked list.
     * If the reference value is not found, or the list is empty, no action is taken.
     *
     * @param referenceValue The value before which the new node is to be added.
     * @param newValue       The value to be added before the reference value.
     */
    public void addBefore(String referenceValue, String newValue) {
        if (head == null) {
            return; // List is empty, nothing to add before
        } else {
            Node position = head;
            // Traverse the list until the referenceValue is found
            while (position != null && !position.topic.equals(referenceValue)) {
                position = position.after;
            }
            // If referenceValue is not found, or list is empty, do nothing
            if (position == null) {
                return;
            } else {
                // If referenceValue is found at the head of the list
                if (position == head) {
                    addAtHead(newValue); // Add newValue at the head
                    Node newNode = new Node(newValue, position.before, position);
                    Scanner scanner = new Scanner(System.in);

                    // Prompt user to input words
                    System.out.println("Enter words for the topic '" + newValue + "'. Enter blank line to finish:");
                    String word = scanner.nextLine().trim();
                    while (!word.isEmpty()) {
                        newNode.words.addAtEnd(word);
                        word = scanner.nextLine().trim();
                    }
                } else {
                    // Otherwise, create a new node with newValue
                    // and insert it before the position node
                    Node newNode = new Node(newValue, position.before, position);
                    Scanner scanner = new Scanner(System.in);

                    // Prompt user to input words
                    System.out.println("Enter words for the topic '" + newValue + "'. Enter blank line to finish:");
                    String word = scanner.nextLine().trim();
                    while (!word.isEmpty()) {
                        newNode.words.addAtEnd(word);
                        word = scanner.nextLine().trim();
                    }
                    
                    // Adjust the references of surrounding nodes
                    position.before.after = newNode;
                    position.before = newNode;
                    // Increase the size of the list
                    size++;
                }
            }
        }
    }

    
    //MODIFY
    
    
    // Add before value (going forward)
    
//    public void addBefore(int referenceValue, int newValue) {
//    	if
//    }
    /**
     * Removes the head node from the doubly linked list and returns its value.
     * If the list is empty, returns null.
     *
     * @return The value of the head node that has been removed, or null if the list is empty.
     */
    public String removeHead() {
        if (head == null) {
            return null;
        } else if (size == 1) {
            Node temp = head;
            head = null;
            tail = null;
            size--;
            return temp.topic;
        } else {
            Node temp = head;
            head = head.after;
            head.before = null;
            size--;
            return temp.topic;
        }
    }

    /**
     * Removes the tail node from the doubly linked list and returns its value.
     * If the list is empty, returns null.
     *
     * @return The value of the tail node that has been removed, or null if the list is empty.
     */
    public String removeTail() {
    	if (tail == null) {
    		return null;
    	}
    	else if (size == 1) { // THIS IS THE SAME AS removeHead();
//    		Node temp = tail;
//    		tail = null;
//    		head = null;
//    		size--;
//    		return temp.value;
    		
    		return removeHead();
    	}
    	else {
    		Node temp = tail;
    		tail = tail.before;
    		tail.after = null;
    		size--;
    		return temp.topic;
    	}
    }
    
    /**
     * Removes the node containing the specified topic from the doubly linked list and returns its value.
     * If the list is empty or the topic is not found, returns null.
     *
     * @param topic The topic to be removed from the list.
     * @return The value of the removed node, or null if the list is empty or the topic is not found.
     */
    public String removeValue(String topic) {
        if (head == null) {
            return null; // List is empty, nothing to remove
        } else {
            Node position = head;
            while (position != null && !position.topic.equals(topic)) {
                position = position.after;
            }
            if (position != null) {
                if (position == head) {
                    return removeHead(); // Remove from head
                } else if (position == tail) {
                    return removeTail(); // Remove from tail
                } else {
                    Node beforePosition = position.before;
                    Node afterPosition = position.after;

                    beforePosition.after = afterPosition;
                    afterPosition.before = beforePosition;
                    size--;
                    return position.topic;
                }
            }
            return null; // Topic not found
        }
    }

    /**
     * Modifies a word node within the specified topic.
     * If the topic or the old word is not found, prints a message accordingly.
     *
     * @param topic   The topic in which the word node is to be modified.
     * @param oldWord The old word to be replaced.
     * @param newWord The new word to replace the old word.
     */
    public void modifyWordNode(String topic, String oldWord, String newWord) {
        OurLinkedList topicList = getWordsForTopic(topic);
        if (topicList != null) {
            OurLinkedList.Node wordNode = topicList.getHead();
            while (wordNode != null) {
                if (wordNode.getData().equals(oldWord)) {
                    wordNode.setData(newWord);
                    System.out.println("Word '" + oldWord + "' modified to '" + newWord + "' in topic '" + topic + "'.");
                    return;
                }
                wordNode = wordNode.getNext();
            }
            System.out.println("Word '" + oldWord + "' not found in topic '" + topic + "'.");
        } else {
            System.out.println("Topic '" + topic + "' not found.");
        }
    }
    /**
     * Adds a word to the specified topic in the doubly linked list.
     * If the topic is not found, prints a message accordingly.
     *
     * @param topic The topic to which the word is to be added.
     */
    public void addWordToTopic(String topic) {
        OurLinkedList topicList = getWordsForTopic(topic);
        
        if (topicList != null) {
            // Prompt user to input words
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word for the topic '" + topic + "':");
            String word = scanner.nextLine().trim();

            // Add the word at the head of the topicList
            topicList.addAtHead(word);
            System.out.println("Word '" + word + "' added to the topic '" + topic + "'.");

            // Close the scanner to prevent resource leak
            scanner.close();
        } else {
            System.out.println("Topic '" + topic + "' not found.");
        }
    }
    
    /**
     * Retrieves a list of words starting with the specified letter from all topics in the doubly linked list.
     *
     * @param letter The starting letter of the words to retrieve.
     * @return An ArrayList containing words starting with the specified letter from all topics.
     */
    public ArrayList<String> getWordsStartingWithLetter(char letter) {
        ArrayList<String> wordsStartingWithLetter = new ArrayList<>();
        Node current = head;
        while (current != null) {
            OurLinkedList wordsList = current.getWords();
            if (wordsList != null) {
                OurLinkedList.Node wordNode = wordsList.getHead();
                while (wordNode != null) {
                    String word = wordNode.getData();
                    if (!word.isEmpty() && word.charAt(0) == letter) {
                        wordsStartingWithLetter.add(word);
                    }
                    wordNode = wordNode.getNext();
                }
            }
            current = current.getNext();
        }
        return wordsStartingWithLetter;
    }
    
    /**
     * Searches for a specified word in the topics and returns the topic containing the word.
     * If the word is found, returns the topic; otherwise, returns null.
     *
     * @param word The word to search for.
     * @return The topic containing the specified word, or null if the word is not found.
     */
    public String searchWord(String word) {
        OurDLinkedList.Node current = head;

        while (current != null) {
            OurLinkedList wordsList = current.getWords();
            if (wordsList != null) {
                OurLinkedList.Node currentWord = wordsList.getHead();
                while (currentWord != null) {
                    if (currentWord.getData().equalsIgnoreCase(word)) {
                        return current.getTopic(); // Word found
                    }
                    currentWord = currentWord.getNext();
                }
            }
            current = current.getNext();
        }

        return null; // Word not found
    }
    
    

    /**
     * Extracts words starting with the specified letter from all topics and stores them in an ArrayList.
     * The extracted words are sorted alphabetically.
     *
     * @param letter The starting letter of the words to extract.
     * @return An ArrayList containing words starting with the specified letter from all topics, sorted alphabetically.
     */
    public ArrayList<String> extractWordsStartingWithLetter(char letter) {
        ArrayList<String> extractedWords = new ArrayList<>();
        Node current = head;
        while (current != null) {
            OurLinkedList.Node wordNode = current.getWords().getHead();
            while (wordNode != null) {
                String word = wordNode.getData();
                if (!word.isEmpty() && word.charAt(0) == letter) {
                    extractedWords.add(word);
                }
                wordNode = wordNode.getNext();
            }
            current = current.getNext();
        }
        // Sort the extracted words
        Collections.sort(extractedWords);
        return extractedWords;
    }

    /**
     * Returns the number of elements (topics) in the doubly linked list.
     *
     * @return The number of elements (topics) in the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Displays the topics in the doubly linked list in forward order.
     * If the list is empty, prints a message indicating so.
     */
    public void displayForward() {
        if (size == 0) {
            System.out.println("The list is empty");
        } else {
            System.out.println("The list has " + size + " element(s):");
            Node position = head;
            while (position != null) {
                System.out.println(position.topic);
                position = position.after;
            }
        }
    }
    /**
     * Displays the topics in the doubly linked list in backward order.
     * If the list is empty, prints a message indicating so.
     */
    public void displayBackward() {
        if (size == 0) {
            System.out.println("The list is empty");
        } else {
            System.out.println("The list has " + size + " element(s):");
            Node position = tail;
            while (position != null) {
                System.out.println(position.topic);
                position = position.before;
            }
        }
    }
    
    /**
     * Retrieves the topics from the doubly linked list and returns them as an array of strings.
     *
     * @return An array of strings containing the topics stored in the list.
     */
    public String[] getTopics() {
        String[] topics = new String[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            topics[index++] = current.getTopic();
            current = current.getNext();
        }
        return topics;
    }
    /**
     * Displays the content of the specified topic.
     * If the topic is found, prints the content of the topic including each word.
     * If the topic is not found or has no content, prints an appropriate message.
     *
     * @param chosenTopic The topic for which to display the content.
     */
    public void displayTopicContent(String chosenTopic) {
        OurDLinkedList.Node chosenNode = findNodeByTopic(chosenTopic);
        if (chosenNode != null) {
            System.out.println("\nContent of topic '" + chosenTopic + "':\n");
            OurLinkedList wordsList = chosenNode.getWords();
            if (wordsList != null && wordsList.getSize() > 0) {
                OurLinkedList.Node currentNode = wordsList.getHead();
                int count = 1;
                while (currentNode != null) {
                    System.out.print(count + ": " + currentNode.getData() + " ");
                    if (count%5 == 0) {
                    	System.out.println("\n");
                    }
                    currentNode = currentNode.getNext();
                    count++;
                }
                System.out.println();
            } else {
                System.out.println("No content available for this topic.");
            }
        } else {
            System.out.println("Topic not found.");
        }
    }
    /**
     * Saves all content (topics and their words) to a file.
     * Each topic is prefixed with '#' and followed by its associated words.
     * Topics are separated by a blank line.
     *
     * @param fileName The name of the file to which the content will be saved.
     */
    public void saveToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);

            Node current = head;
            while (current != null) {
            	
                writer.write("#" + current.getTopic() + "\n");
                System.out.println(current.getTopic());
                OurLinkedList wordsList = current.getWords();
                if (wordsList != null && wordsList.getSize() > 0) {
                    OurLinkedList.Node currentNode = wordsList.getHead();
                    while (currentNode != null) {
                        writer.write(currentNode.getData() + "\n");
                        currentNode = currentNode.getNext();
                    }
                }
                writer.write("\n"); // Separate topics with a blank line
                current = current.getNext();
            }

            writer.close();
            System.out.println("Content saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }





    /**
     * Finds the node in the doubly linked list with the specified topic.
     *
     * @param topic The topic to search for.
     * @return The node containing the specified topic, or null if the topic is not found.
     */
    public Node findNodeByTopic(String topic) {
        Node current = head;
        while (current != null) {
            if (current.getTopic().equals(topic)) {
                return current;
            }
            current = current.getNext();
        }
        return null; // Topic not found
    }
    /**
     * Inner class representing a node in the doubly linked list.
     */
    private class Node {

//        private int value;
        private Node before;
        private Node after;
        private String topic;
    	private OurLinkedList words;
    	/**
         * Constructs a new node with the specified topic, previous node, and next node.
         *
         * @param topic  The topic of the node.
         * @param before The node before this node.
         * @param after  The node after this node.
         */
        public Node(String topic, Node before, Node after) {
            this.topic = topic;
            this.before = before;
            this.after = after;
            
            this.words = new OurLinkedList();
            
        }
        

        /**
         * Retrieves the topic of the node.
         *
         * @return The topic of the node.
         */
        public String getTopic() {
            return topic;
        }

        /**
         * Retrieves the node after this node.
         *
         * @return The node after this node.
         */
        public Node getNext() {
            return after;
        }
        /**
         * Retrieves the linked list of words associated with this node.
         *
         * @return The linked list of words.
         */
        public OurLinkedList getWords() {
            return words;
        }
        	
    }


}