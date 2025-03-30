import java.util.Arrays;
import java.util.EmptyStackException;

//Java does not allow to create a Generic Array like new E[10] after compile, java will lose its type (type erasure)
//in order to use E type, must change type.
//IDE will warn as when Object -> E, it cannot check E. if there's wrong type, can trigger runtime exception.
//But this stack class will only use the generic, hence it's type safe. d
public class Stack<E> {
    private static final int DEFAULT_CAPACITY = 20;
    private Object[] stack;
    private int topIndex;  //top index (will be inserted here)

    public Stack(int capacity) {
        stack = new Object[capacity];
        topIndex = -1; //so that when push the first element, topIndex = 0;
    }

    private void resize() {
        if (isFull()) {
            int newCapacity = stack.length * 2;
            stack = Arrays.copyOf(stack, newCapacity);
            return;
        }
        if (topIndex < stack.length / 2) {
            int halfCapacity = stack.length / 2;
            stack = Arrays.copyOf(stack, Math.max(halfCapacity, DEFAULT_CAPACITY));
        }
    }

    public E push(E item) {
        if (isFull()) {
            resize();
        }
        //here if I do, stack[topIndex++], it wil do stack[-1] = item and topIndex = 0.
        topIndex ++;
        stack[topIndex] = item;
        return item;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        Object element = stack[topIndex];
        stack[topIndex] = null;
        topIndex --;
        return (E) element;
    }

    @SuppressWarnings("unchecked")
    public E peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return (E) stack[topIndex];
    }

    public Boolean isFull() {
        return topIndex == stack.length - 1;
    }

    public Boolean isEmpty() {
        return stack.length == 0;
    }

    //start from stack top to bottom, find where it's located (from the top)
    public int search(E value) {
        for (int i = topIndex; i>=0; i--) {
            if (stack[i] == value) {
                return topIndex - i +1;
            }
        }
        return -1; //if not exist
    }

    @Override
    public String toString() {
        return Arrays.toString(stack);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);

        stack.pop();

        System.out.println(stack.peek());

        System.out.println(stack.search(2)); //

    }
}
