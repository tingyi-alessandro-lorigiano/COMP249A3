package testing;


import java.util.LinkedList;

public class myLinkedList {
    
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        
        // Add your code here
        
//        linkedList.push("a");
//        linkedList.push("b");
//
//        linkedList.push("c");
//        linkedList.pop();
        
        
        //LinkedList as a Queue
        linkedList.offer("a");
        linkedList.offer("b");

        linkedList.offer("c");
        linkedList.poll();
        System.out.println(linkedList);

        
    }
}