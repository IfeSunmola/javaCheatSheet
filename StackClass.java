
public class StackClass {
    public static void main (String [] args) {
        // Stack s = new Stack();
        StackDLL s = new StackDLL();
        s.push(39);
        s.push(51);
        s.push(52);
        s.pop();
        System.out.println(s);
        s.push(72);
        System.out.println(s);
        s.pop();

//        s.push(23);
//        s.push(13);
//        s.push(9);
        System.out.println(s);
        // System.out.println(s.toString2());
        System.out.println(s.isEmpty());
        s.pop();
        s.pop();
        System.out.println(s.isEmpty());
    }
}

class Stack {
    // for stacks with arrays, I add and remove from the back of the array which means the last data
    // in the array is always the top
    private static final int MAX_SIZE = 3;
    private int [] items;
    private int size;

    public Stack () {
        items = new int [MAX_SIZE];
        size = 0;
    }

    public void push (int newData) {
        // add to the top of the stack (back of the array)
        if (size >= items.length) {// no space, resize
            resize();
        }
        items[size++] = newData;
        // size++;
    }

    public int pop () {
        // remove and return the item on top of the stack
        if (!isEmpty()) {// only try popping if the list is not empty
            size--;// try --size;
            return items[size];
        }
        return Integer.MIN_VALUE;
    }

    public int top () {
        // returns the items on top of the stack
        return items[size - 1];
    }

    public boolean isEmpty () {
        // true if the stack has no items
        return size == 0;
    }

    public int getLength () {
        // return the number of items in the stack
        return size;
    }

    public void clear () {
        size = 0;
    }

    public void swap () {
        int top, second;
        top = pop();
        second = pop();
        push(top); // Push the top first
        push(second); // The second becomes the top
    }

    private void resize () {
        int [] newArray = new int [items.length * 2];
        for (int i = 0; i < items.length; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public String toString () {
        String result = "";
        for (int i = size - 1; i >= 0; i--) {
            result += items[i] + " ";
        }
        result += " (top is first index " + top() + ")";
        return result;
    }
}

class StackDLL {
    private Node first;// top
    private Node last;
    private int size;

    public StackDLL () {

    }

    public void push (int data) {// add to the front of the linked list
        first = new Node(data, null, first);
        if (last == null) { // if the list was previously empty
            last = first; // then the newNode is the first and the last at the same time

        }
        else {
            first.next.prev = first; // make connections in both directions
        }
        size++;
    }

    public int pop () {// remove the first item and return it
        int top = Integer.MIN_VALUE;
        if (!isEmpty()) {
            top = first.data;
            if (first == last) {// only one item in the list
                first = last = null;
            }
            else {
                first = first.next;
                first.prev = null;
            }
            size--;
        }

//        if (!isEmpty()) {
//            top = first.data;
//            first = first.next;
//        }
        return top;
    }

    public boolean isEmpty () {
        return size == 0;// || first = null
    }

    public String toString () {
        String result = "";
        if (!isEmpty()) {
            Node curr = first;
            while (curr != null) {
                result += curr.data + " ";
                curr = curr.next;
            }
            result += " (top is first data " + first.data + ")";
        }
        return result;
    }
    @SuppressWarnings("all")
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
