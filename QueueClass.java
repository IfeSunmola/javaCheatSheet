
public class QueueClass {
    public static void main (String [] args) {
        QueueDLL q = new QueueDLL();
        q.enqueue(23);
        q.enqueue(45);
        q.enqueue(0);
        q.enqueue(10);
        q.enqueue(5);
        System.out.println("Should be 23 45 0 10 5:\n" + q);
        System.out.println("Should be 23: " + q.peek());

        System.out.println("Should be 23: " + q.dequeue());
        System.out.println("List should be 45, 0, 10, 5:\n" + q);
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        System.out.println("Should be true: " + q.isEmpty());
    }
}

class QueueDLL {
    private Node first;
    private Node last;
    private int size;

    public void enqueue (int newData) {// add newData at the end of the list
        last = new Node(newData, last, null);;
        if (first == null) { // if the list was previously empty
            first = last; // then the newNode is the first and the last at the same time

        }
        else {
            last.prev.next = last; // make connections in both directions
        }
        size++;
    }

    public int dequeue () {// remove from the front of the list and return it
        int firstData = Integer.MIN_VALUE;
        if (!isEmpty()) {
            firstData = first.data;
            if (first == last) {// only one item in the list
                first = last = null;
            }
            else {
                first = first.next;
                first.prev = null;
            }
            size--;
        }
        return firstData;
    }

    public int peek () {// returns item in front of the list
        if (!isEmpty()) {
            return first.data;
        }
        return -1;
    }

    public boolean isEmpty () {// returns true if the list has no items
        return size == 0; // || first == null
    }

    public String toString () { 
        String result = "";
        Node curr = first;
        if (!isEmpty()) {
            while (curr != null) {
                result += curr.data + " ";
                curr = curr.next;
            }
            result += "(" + peek() + " is at the front)";
        }
        return result;
    }

    private class Node {
        Node next;
        Node prev;
        int data;

        private Node (int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
