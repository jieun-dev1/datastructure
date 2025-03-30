

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}

//type parameter
public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }
    public SinglyLinkedList(T node) {
        this.head = new Node<>(node);
        this.size = 1;
    }

    public void addFirst(T value){
        Node<T> previous = this.head;
        this.head = new Node<>(value);
        this.head.next = previous;
        size ++ ;
    }

    public void addLast(T value){
        if (head == null) {
            head = new Node<>(value);
        }
        Node<T> current = head;
        //as it assigns curr = curr.next, then, access to next,
        // we should make sure curr.next is not null. to avoid nullPointerException
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node<>(value);
        size ++ ;
    }

    public void addAt(int index, T value){
        Node<T> previous = getNode(index-1);
        Node<T> curr = getNode(index);
        Node<T> newCurr = new Node<>(value);
        previous.next = newCurr;
        newCurr.next = curr;
        size ++ ;
    }

    public void removeFirst(){
        //head changed
        if (head.next != null) {
            head = head.next;
        }
        size --;
    }

    public void removeLast(){
        //find tail
        Node<T> curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        //when head.next.next = null
        curr.next = null;
        size --;
    }
    public void removeAt(int index) {
        System.out.println("size: "+size);
        Node<T> previousNode = getNode(index-1);
        Node<T> nextNode;
        if (index >= size-1){
            nextNode = null;
        } else {
            nextNode = getNode(index + 1);
        }
        previousNode.next = nextNode;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    private Node<T> getNode(int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + "size: " + size);
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public int indexOf(T value) {
        Node<T> node = head;
        int index = 0;
        while (node !=null) {
            if (node.value == value) {
                return index;
            }
            node = node.next;
            index ++;
        }

        return -1 ;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ls = new SinglyLinkedList<>(2);
        ls.head = new Node(2);
        ls.addFirst(1);
        System.out.println("After adding 1 at first: "+ ls);
        ls.addLast(10);
        System.out.println("After adding 10 at last: "+ ls);
        System.out.println("Getting index[2]: " + ls.get(2));
        System.out.println("10's index: " + ls.indexOf(10));
        ls.addAt(2, 3);
        System.out.println("After adding 3 at index 2: " + ls);
        ls.addAt(3, 4);
        System.out.println("After adding 4 at index 3: " + ls);
        ls.removeFirst();
        System.out.println("After Removing first: " + ls);
        ls.removeAt(1);
        System.out.println("After Removing value at index 1: " + ls);
        ls.removeLast();
        System.out.println("After Removing Last: " + ls);
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.value);
            sb.append(" -> ");
            current = current.next;
        }
        return sb.toString();
    }
}


