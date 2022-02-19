
public class DequeClass {
    public static void main (String [] args) {
        Deque dq = new Deque();
        dq.pushBack(45);
        System.out.println(dq);
        dq.pushBack(71);
        System.out.println(dq);
        dq.pushFront(97);
        System.out.println(dq);
        dq.pushFront(68);
        System.out.println(dq);
        dq.popBack();
        System.out.println(dq);
    }
}

class Deque {
    private Node first;
    private Node last;
    private int size;

    public void pushBack (int newData) {// add newData at the end of the list
        last = new Node(newData, last, null);;
        if (first == null) { // if the list was previously empty
            first = last; // then the newNode is the first and the last at the same time

        }
        else {
            last.prev.next = last; // make connections in both directions
        }
        size++;
    }

    public void pushFront (int newData) {
        first = new Node(newData, null, first);
        if (last == null) { // if the list was previously empty
            last = first; // then the newNode is the first and the last at the same time
        }
        else {
            first.next.prev = first; // make connections in both directions
        }
        size++;
    }

    public int popFront () {// remove from the front of the list and return it
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

    public int popBack () {
        int backData = Integer.MIN_VALUE;
        if (!isEmpty()) {
            backData = last.data;
            if (first == last) {// only one item in the list
                first = last = null;
            }
            else {
                last = last.prev;
                last.next = null;
            }
            size--;
        }
        return backData;
    }

    public int peekFront () {// returns item in front of the list
        if (!isEmpty()) {
            return first.data;
        }
        return -1;
    }

    public int peekBack () {// returns item in front of the list
        if (!isEmpty()) {
            return last.data;
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
            // result += "(" + peekFront() + " is at the front and " + peekBack() + " is at the back)";
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
