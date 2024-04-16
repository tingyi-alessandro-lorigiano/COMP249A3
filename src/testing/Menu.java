package testing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Represents a menu for interacting with a doubly linked list of topics and a linked list of words.
 * Allows users to perform various operations on the lists.
 */
public class Menu {
    private OurDLinkedList dll;
    private OurLinkedList sll;
    private Scanner scanner;
    /**
     * Constructs a new Menu object.
     * Initializes a doubly linked list for topics (dll), a linked list for words (sll), and a scanner for user input.
     */
    public Menu() {
        dll = new OurDLinkedList();
        sll = new OurLinkedList();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the menu options of the Vocabulary Control Center.
     * This method prints a menu to the console with various options for managing vocabulary topics.
     * Options include browsing topics, inserting new topics, removing topics, searching for words,
     * loading from and saving to files, and more.
     * Users are prompted to enter their choice via the console.
     */
    public void displayMenu() {
    	System.out.println("-----------------------------");
    	System.out.println("Vocabulary Control Center");
    	System.out.println("-----------------------------");

        System.out.println("1. Browse a topic");
        System.out.println("2. Insert a new topic before another one");
        System.out.println("3. Insert a new topic after another one");
        System.out.println("4. Remove a topic");
        System.out.println("5. Modify a topic");
        System.out.println("6. Search topics for a word");
        System.out.println("7. Load from a file");
        System.out.println("8. Show all words starting with a certain letter");
        System.out.println("9. Save to file");

        System.out.println("0. Exit");

        System.out.print("Enter your choice: ");
    }
    
    /**
     * Loads data from a file into the program.
     * Reads each line from the specified input file, parses it, and adds topics and words to the data structure accordingly.
     * Empty lines in the input file are skipped.
     * Topics are identified by lines starting with "#" symbol, and words are added under their respective topics.
     * Words are added to a singly linked list (SLL) associated with each topic in the doubly linked list (DLL).
     * Leading and trailing whitespace in each line are trimmed before processing.
     * 
     * @throws IOException if an I/O error occurs while reading the input file.
     */
    
    private void loadFromFile() {
        System.out.print("Enter the name of the input file: ");
        String fileName = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentTopic = null;

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }
                if (line.startsWith("#")) {
                    currentTopic = line.substring(1).trim();
                    dll.addAtTail(currentTopic); // Add the topic to the DLL
                } else if (currentTopic != null) {
                    // Add the word to the SLL under the current topic
                    OurLinkedList topicWords = dll.getWordsForTopic(currentTopic);
                    if (topicWords != null) {
                        topicWords.addAtEnd(line);
                    }
                }
            }
            System.out.println("\nDone loading.");
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
    private boolean topicAdded = false;

    
    /**
     * Inserts a new topic before the specified reference topic in the doubly linked list.
     * This method prompts the user to enter the topic before which they want to insert a new topic,
     * as well as the new topic to insert. It then calls the {@code addBefore} method of the
     * {@link OurDLinkedList} class to insert the new topic before the reference topic.
     * After successful insertion, a confirmation message is printed to the console.
     */
    public void insertTopicBefore() {
    	if (dll.getSize() == 0 && !topicAdded) {
            System.out.println("No topics found. Adding a new topic directly.");
            System.out.println("Enter the new topic:");
            String newTopic = scanner.nextLine();
            dll.addAtHead(newTopic);
            System.out.println("New topic added successfully.");
            topicAdded = true; // Set the flag to true after adding the first topic
            return;
        }

        System.out.println("\nEnter the topic before which you want to insert a new topic:");
        String referenceTopic = scanner.nextLine();

        System.out.println("Enter the new topic to insert:");
        String newTopic = scanner.nextLine();

        // Call the addBefore method of OurDLinkedList
        dll.addBefore(referenceTopic, newTopic);

        System.out.println("New topic inserted successfully.");
    }
    /**
     * Inserts a new topic after the specified reference topic in the doubly linked list.
     * This method prompts the user to enter the topic after which they want to insert a new topic,
     * as well as the new topic to insert. It then calls the {@code addAfter} method of the
     * {@link OurDLinkedList} class to insert the new topic after the reference topic.
     * After successful insertion, a confirmation message is printed to the console.
     */
    public void insertTopicAfter() {
        System.out.println("\nEnter the topic before which you want to insert a new topic:");
        String referenceTopic = scanner.nextLine();

        System.out.println("Enter the new topic to insert:");
        String newTopic = scanner.nextLine();

        // Call the addAfter method of OurDLinkedList
        dll.addAfter(referenceTopic, newTopic);

        System.out.println("New topic inserted successfully.");
    }
//    private void addBeforeToDoublyLinkedList() {
//        System.out.print("Enter value to add to doubly linked list: ");
//        String value = scanner.nextLine();
//        dll.addAtTail(value);
//        System.out.println("Value added to doubly linked list.");
//    }
//    
//    private void addAfterToDoublyLinkedList() {
//        System.out.print("Enter value to add to doubly linked list: ");
//        String value = scanner.nextLine();
//        dll.addAtTail(value);
//        System.out.println("Value added to doubly linked list.");
//    }
//
//    private void addToSinglyLinkedList() {
//        System.out.print("Enter value to add to singly linked list: ");
//        String value = scanner.nextLine();
//        sll.addAtEnd(value);
//        System.out.println("Value added to singly linked list.");
//    }
//
//    private void removeFromDoublyLinkedList() {
//        String removedValue = dll.removeHead();
//        if (removedValue != null) {
//            System.out.println("Removed from doubly linked list: " + removedValue);
//        } else {
//            System.out.println("Doubly linked list is empty.");
//        }
//    }
//
//    private void removeFromSinglyLinkedList() {
//        String removedValue = sll.removeHead();
//        if (removedValue != null) {
//            System.out.println("Removed from singly linked list: " + removedValue);
//        } else {
//            System.out.println("Singly linked list is empty.");
//        }
//    }
//
//    private void displayDoublyLinkedList() {
//        System.out.println("Doubly linked list:");
//        dll.displayForward();
//    }
//
//    private void displaySinglyLinkedList() {
//        System.out.println("Singly linked list:");
//        sll.display();
//    }
    
    /**
     * Displays a list of topics stored in the doubly linked list and prompts the user to pick a topic.
     * Once the user selects a topic, the method displays the content of the chosen topic.
     * It prints a numbered list of topics retrieved from the {@link OurDLinkedList#getTopics()} method,
     * allowing the user to select a topic by entering the corresponding number.
     * After the user makes a choice, the method calls {@link OurDLinkedList#displayTopicContent(String)}
     * to display the content of the chosen topic.
     * If the user selects "0" to exit, the program terminates.
     */
    public void browseTopics() {
        System.out.println("\n-------------------------------");
        System.out.println("Pick a topic");
        System.out.println("-------------------------------");
        String[] topics = dll.getTopics();
        for (int i = 0; i < topics.length; i++) {
            System.out.println((i + 1) + ". " + topics[i]);
            
            	
        }
        System.out.println("0. Exit");
        System.out.println("-------------------------------");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice >= 1 && choice <= topics.length) {
            String chosenTopic = topics[choice - 1];
            dll.displayTopicContent(chosenTopic);
        } else if (choice == 0) {
            System.out.println("Exiting...");
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        }
        // Handle the user's choice accordingly
        // You might want to add validation here to ensure the user's choice is within the valid range
    

//    public static void main(String[] args) {
//        Menu menu = new Menu();
//        while (true) {
//            menu.displayMenu();
//            int choice = Integer.parseInt(menu.scanner.nextLine());
//            menu.processChoice(choice);
//        }
    /**
	 * Prompts the user to enter a filename and saves the content of the doubly linked list to the specified file.
	 * The method reads the filename from the console input and calls the {@link OurDLinkedList#saveToFile(String)} method
	 * of the doubly linked list to save its content to the file.
	 * If the file cannot be saved due to an error, an appropriate error message is displayed.
	 */
    public void saveToFile() {
    	System.out.println("Enter the filename to save the content to: ");
    	String filename = scanner.nextLine();
    	dll.saveToFile(filename);
    }
    /**
     * Prompts the user to enter the topic they want to remove.
     * The method reads the topic name from the console input and calls the {@link OurDLinkedList#removeValue(String)} method
     * of the doubly linked list to remove the specified topic.
     * If the topic is successfully removed, a confirmation message is displayed.
     * If the topic is not found in the list, a message indicating that the topic was not found is displayed.
     */
    public void removeTopic() {
        System.out.println("Enter the topic you would like to remove: ");
        String topicToRemove = scanner.nextLine();
        String removedTopic = dll.removeValue(topicToRemove);
        if (removedTopic != null) {
            System.out.println(removedTopic + " removed.");
        } else {
            System.out.println("Topic not found.");
        }
    }
    
    /**
     * Displays all the words in the doubly linked list that start with the specified letter.
     * The method first converts the input letter to a char and then calls the {@link OurDLinkedList#getWordsStartingWithLetter(char)}
     * method of the doubly linked list to retrieve all words starting with the specified letter.
     * If such words exist, they are sorted alphabetically and printed to the console.
     * If no words are found, a message indicating that no words were found starting with the specified letter is displayed.
     *
     * @param letter The letter to search for words starting with.
     */
    public void showWordsStartingWithLetter(String letter) {
        char firstLetter = letter.charAt(0); // Convert the input String to a char

        // Get all words starting with the specified letter
        ArrayList<String> words = dll.getWordsStartingWithLetter(firstLetter);
        if (words != null && !words.isEmpty()) {
            // Sort the words
            Collections.sort(words);
            // Print the sorted words
            System.out.println("Words starting with '" + firstLetter + "':");
            for (String word : words) {
                System.out.println(word);
            }
        } else {
            System.out.println("No words found starting with '" + firstLetter + "'.");
        }
    }


    
    /**
     * Prompts the user to enter a word and searches for it in the vocabulary.
     * The method reads the word from the console input, trims leading and trailing whitespace,
     * and then calls the {@link OurDLinkedList#searchWord(String)} method of the doubly linked list
     * to find the topic associated with the word.
     * If the word is found, the topic it belongs to is printed to the console.
     * If the word is not found, a message indicating that the word was not found is displayed.
     */
    public void searchWord() {
        System.out.print("Enter the word to search: ");
        String word = scanner.nextLine().trim();

        String topicOfWord = dll.searchWord(word);

        if (topicOfWord != null) {
            System.out.println("Word '" + word + "' found in topic: " + topicOfWord);
        } else {
            System.out.println("Word '" + word + "' not found.");
        }
    }
    /**
     * Prompts the user to choose a topic to modify and provides options to add, remove, or change words within the topic.
     * The user is presented with a menu to select the desired action.
     */
    public void modifyTopic() {
        System.out.println("\nEnter the topic you want to modify:");
        String topicToModify = scanner.nextLine();
        
        // Implement logic to modify the topic here
        // You might prompt the user to choose from options like adding a word, removing a word, or changing a word

        // For example:
        System.out.println("-----------------------------");
        System.out.println("Modify Topics Menu");
        System.out.println("-----------------------------");
        System.out.println("a. Add a word");
        System.out.println("r. Remove a word");
        System.out.println("c. Change a word");
        System.out.println("0. Exit");
        System.out.println("-----------------------------");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "a":
                dll.addWordToTopic(topicToModify);
                break;
//            case "r":
//                removeWordFromTopic(topicToModify);
//                break;
//            case "c":
//            	modifyWordNode(topicToModify);
//                break;
            case "0":
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


    /**
     * Main method of the Menu class.
     * Creates a new instance of the Menu class and enters a loop to display the menu options
     * and handle user input until the user chooses to exit the program.
     * The user input is validated to ensure it corresponds to a valid menu option,
     * and appropriate actions are taken based on the chosen option.
     * 
     * @param args The command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Menu menu = new Menu();

        while (true) {
            menu.displayMenu();
            String input = menu.scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        menu.browseTopics();
                        break;
                    case 2:
                        menu.insertTopicBefore();
                        break;
                    case 3:
                        menu.insertTopicAfter();
                        break;
                    case 4:
                        menu.removeTopic();
                        break;
                    case 5:
                    	menu.modifyTopic();
                        // Modify a topic
                        break;
                    case 6:
                        menu.searchWord();

                        // Display singly linked list
                        break;
                    case 7:
                        menu.loadFromFile();
                        break;
                    case 8:
                    	System.out.print("Enter a letter to show words starting with that letter: ");
                        String letterInput = menu.scanner.nextLine();
                        if (letterInput.length() == 1 && Character.isLetter(letterInput.charAt(0))) {
                        	menu.showWordsStartingWithLetter(String.valueOf(letterInput.charAt(0)));
                        } else {
                            System.out.println("Invalid input. Please enter a single letter.");
                        }
                        break;
                    case 9:
                        menu.saveToFile();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
            }
        }
    }


}
