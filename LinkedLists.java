
public class LinkedLists {
    public static void main (String [] args) {
        SLL s = new SLL();
        s.insert(8);
        s.insert(2);
        s.insert(5);
        s.insert(7);
        s.insert(4);
        s.insert(1);
        System.out.println(s);
        s.modifyList();
        System.out.println(s);
    }
}

class CircularList {
    private Node first;
    private int size;

    public CircularList () {
        first = null;
        size = 0;
    }

    public void add (int value) {
        if (first == null) {
            first = new Node(value, null);
            first.setNext(first);
        }
        else {
            Node newNode = new Node(value, first.getNext());
            first.setNext(newNode);
        }
        size++;
    }

    public int get () {
        return first.getData();
    }

    public void rotate () {
        if (first != null)
            first = first.getNext();
    }

    public int size () {
        return size;
    }

    public int count (int value) {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (get() == value)
                count++;
            rotate();
        }
        return count;
    }

    // Add your toString and remove methods here
    public String toString () {
        String result = "The list contains: ";
        if (first == null) {
            result += "Nothing!  The list is empty.";
        }
        else {
            for (int i = 0; i < size; i++) {
                result += get();
                rotate();
                if (i != size - 1) {
                    result += ", ";
                }
                else {
                    result += ".";
                }
            }
        }
        return result;
    }

    public String toString2 () {
        String result = "";
        if (first != null) {
            Node current = first;
            do {
                result += current.data + " ";
                current = current.next;
            }
            while (current != first);
        }
        return result;
    }

    public int remove () {
        int result = Integer.MIN_VALUE;
        if (first == null) {
            return result;
        }
        else if (first == first.getNext()) {
            result = first.getData();
            first = null;
            return result;
        }
        else {
            Node last = null;
            for (int i = 0; i < size; i++) {
                if (last != first) {
                    last = first;
                    rotate();
                }
            }
            result = get();
            first = first.getNext();
            size--;
            return result;
        }
    }

    private class Node {
        private int data;
        private Node next;

        public Node (int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData () {
            return data;
        }

        public Node getNext () {
            return next;
        }

        public void setNext (Node next) {
            this.next = next;
        }
    }
}
@SuppressWarnings("all")
class DLL {
    private Node first;
    private Node last;
    private int size;

    public DLL () {

    }

    public void addEnd (int data) {
        last = new Node(data, last, null);;
        if (first == null) { // if the list was previously empty
            first = last; // then the newNode is the first and the last at the same time

        }
        else {
            last.prev.next = last; // make connections in both directions
        }
        size++;
    }

    public void addFront (int data) {
        first = new Node(data, null, first);
        if (last == null) { // if the list was previously empty
            last = first; // then the newNode is the first and the last at the same time
        }
        else {
            first.next.prev = first; // make connections in both directions
        }
        size++;
    }

    public void addAfter (Node after, int data) {// add data after "after
        last = new Node(data, last, null);
        if (first == null) { // if the list was previously empty
            first = last; // then the newNode is the first and the last at the same time
        }
        else {
            last.prev.next = last; // make connections in both directions

        }
        size++;
    }

    public void orderedInsert (int toAdd) {
        if (last == null) {
            last = new Node(toAdd, null);
            last.next = last;
        }
        else {

            if (last.data < toAdd) {// add last
                last.next = new Node(toAdd, last.next);
                last = last.next;
            }
            else {
                Node curr = last.next;
                Node prev = last;
                while (curr.data < toAdd) {
                    prev = curr;
                    curr = curr.next;
                }
                if (toAdd != curr.data) {
                    prev.next = new Node(toAdd, curr);
                }
            }
        }
    }

    public void moveMinToFront () {
        // find and move the node containing the minimum value in the list to the top of the list
        if (first != null && first.next != null) {// list is not empty
            // search for the node containing the smallest value
            Node prevMin = null;
            Node min = first;// starting from
            Node prev = first;// first node
            for (Node curr = first.next; curr != null; curr = curr.next) {// look for smallest
                if (curr.data < min.data) {
                    prevMin = prev;
                    min = curr;// found
                }
                prev = curr;
            }

            if (first != min) {// first is not the minimum, swap
                prevMin.next = min.next;
                min.next = first;
                first = min;
            }
        }
    }

    public void moveMaxToEnd () {
        if (first != null && first.next != null) {// list is not empty
            // search for the node containing the smallest value
            Node prevMax = null;
            Node max = first;// starting from
            Node prev = first;// first node
            for (Node curr = first.next; curr != null; curr = curr.next) {// look for smallest
                if (curr.data > max.data) {
                    prevMax = prev;
                    max = curr;// found
                }
                prev = curr;
            }

            if (prev != max) {// max is not the last, swap
                if (prevMax != null) {
                    prevMax = max.next;
                }
                else {
                    first = first.next;
                }
                max.next = null;// make max last
                prev.next = max;
            }
        }
    }

    private void removeNode (Node toRemove) {
        if (last == first) {// only one Node in the list
            first = last = null;
        }
        else if (toRemove == first) {// removing the first Node
            first = first.next;
            first.prev = null;
        }
        else if (toRemove == last) { // removing the last Node
            last = last.prev;
            last.next = null;
        }
        else {// removing a Node in the middle of the list
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
    }

    @Override
    public String toString () {
        Node current = first;
        String result = "";
        while (current != null) {
            result += current.data + " ";
            current = current.next;
        }
        return result;
    }

    public String toStringReversed () {
        Node current = last;
        String result = "";
        while (current != null) {
            result += current.data + " ";
            current = current.prev;
        }
        return result;
    }

    public void orderedInsertRec (Node toInsert, Node current) {
        // Recursive method to do an ordered insertion (ascending order) of the Node toInsert,
        // starting from the Node current and looking toward the first Node of the list.
        if (current == null) // base case, if we get to this point, we have to add to the front
            addFront(toInsert.data);
        else if (toInsert.data > current.data) // bigger than current, so we have to stop and insert after current
        {
            if (current == last) // adding at the end
                addEnd(toInsert.data);
            else // adding in the middle of the list
            {
                toInsert.next = current.next;
                current.next = toInsert;
                toInsert.prev = current;
                toInsert.next.prev = toInsert;
            }
        }
        else // make a recursive call
            orderedInsertRec(toInsert, current.prev);
    }

    public void traverseRec () {
        traverseRec(first);
    }

    private void traverseRec (Node node) {
        if (node != null) {
            // do something
            traverseRec(node.next);
        }
    }

    public void traversReverseRec () {
        traversReverseRec(first);
    }

    private void traversReverseRec (Node node) {
        if (node != null) {
            traversReverseRec(node.next);
            // do something
        }
    }

    public Node search (int key) {
        return search(key, first);// return the found node
    }

    private Node search (int key, Node node) {
        if (node != null) {
            if (node.data == key) {
                return node;
            }
            return search(key, node.next);
        }
        return null;
    }

    public void sort () {// with insertion sort, no recursion, O(N^2), BEST CASE IS N
        Node current = first.next;
        while (current != null) {
            Node nextNode = current.next;
            Node search = current.prev;

            while (search != null && search.data > current.data) {
                search = search.prev;
            }
            // remove and re insert current
            removeNode(current);
            if (search == null) {
                current.prev = null;
                addFront(current.data);
            }
            else {
                addAfter(search, current.data);
            }
            current = nextNode;// move to next node
        }
    }

    public void insertionSort () {
        if (first == null || first == last) { // empty list or list with only 1 element, nothing to do
            return;
        }
        insertionSortRec(first.next); // can start from second Node
    }

    // Private recursive version of the insertionSort method.
    private void insertionSortRec (Node current) {

        if (current == null) {// base case
            return; // we're done
        }
        removeNode(current); // removing the current Node first
        Node nextNode = current.next; // current.getNext() hasn't been modified yet; saving it in a variable
        // using ordered insertion to put it back in the right spot:
        orderedInsertRec(current, current.prev); // note that current.getPrevious() hasn't been modified yet;
        insertionSortRec(nextNode);
    }

    private class Node {
        private Node prev;// previous node
        private Node next;// next node
        private int data;

        private Node (int data, Node prev, Node next) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }

        private Node (int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}

class SLL {
    private Node first;

    public SLL () {

    }

    public void insert (int new_data) {
        Node new_node = new Node(new_data, first);
        first = new_node;
    }

    // Node newNode = new Node(temp, first);
    // first = newNode;
    public void modifyList () {
        Node curr = first;
        while (curr != null) {
            if (curr.data % 2 == 0) {// even
                int temp = curr.data;
                Node toAdd = new Node(temp, null);
                if (first == null) {
                    first = toAdd;// empty linked list, first = last
                }
                else {
                    Node last = first;
                    while (last.next != null) {
                        last = last.next;
                    }
                    last.next = toAdd;
                }
            }
            else {// odd
                  // if(curr.next == null){
                  // curr = null;
                  // }
                  // curr.data = curr.next.data;
                  // curr.next = curr.next.next;
            }
            curr = curr.next;
        }
    }

    public String toString () {
        String result = "";
        Node curr = first;
        while (curr != null) {
            result += curr.data + " ";
            curr = curr.next;
        }
        return result;
    }

    public void insertionSort () {// average and worst case is N^2. Best is N
        Node beforeCurr = first;
        Node current = first.next;
        Node next, pos;
        while (current != null) {
            next = current.next;
            pos = findPos(current.data);

            if (pos == beforeCurr)
                beforeCurr = current;
            else {
                // ListRemoveAfter(list, beforeCurr);
                // if (pos == null)
                // ListPrepend(list, current);
                // else
                // ListInsertAfter(list, pos, current);
            }

            current = next;
        }
    }

    private Node findPos (int data) {
        Node currNodeA = null;
        Node currNodeB = first;

        while (currNodeB != null && data > currNodeB.data) {
            currNodeA = currNodeB;
            currNodeB = currNodeB.next;
        }
        return currNodeA;
    }

    private class Node {
        private Node next;// next node
        private int data;

        private Node (int data, Node next) {
            this.next = next;
            this.data = data;
        }
    }
}