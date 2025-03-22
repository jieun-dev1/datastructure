import java.util.Arrays;

public class DynamicArray<T> {

    private T[] data;
    public int size = 0;
    private int capacity = 5;

    public DynamicArray() {
        data = (T[]) new Object[capacity];
    }

    //add at the end
    public void add(T value) {
        if (size == capacity) {
            resize();
        }
        data[size++] = value;
    }

    //add at the specific loc
    public void add(T value, int index) {
        checkBounds(index);
        if (size == capacity) {
            resize();
        }
        int order = size;
        for (int i = size; i > index; i--) {
            data[order] = data[order - 1];
            order--;
        }
        data[index] = value;
        size++;
    }

    public T get(int index) {
        checkBounds(index);
        return data[index];
    }

    public void set(int index, T value) {
        checkBounds(index);
        data[index] = value;
    }

    public boolean remove(T value) {
        //null should be compared with '==' not equals
        for (int i = 0; i < size; i++) {
            if (value == null) {
                if (data[i] == null)
                    return true;
            } else {
                if (value.equals(data[i])) {
                    removeAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    public int find(T value) {
        for (int i=0; i<size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public T removeAt(int index) {
        checkBounds(index);
        T removed = data[index];
        for (int i=index; i<size-1; i++){
            data[i] = data[i+1];
        }
        data[size-1] = null; //Unless, some will still refer to the data[size-1] break the reference.
        size --;
        return removed;
    }


    private void resize() {
        capacity = capacity * 2;
        data = Arrays.copyOf(data, capacity);
        System.out.println("resize to size " + capacity);
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}

class Test {
    public static void main(String[] args) {
        DynamicArray<Integer> da = new DynamicArray<>();
        da.add(2);
        System.out.println("da: " + da + " size:" + da.size);
        da.add(1, 0);
        System.out.println("da: " + da + " size:" + da.size);
        da.set(1, 3);
        System.out.println("da: " + da + " size:" + da.size);
        da.add(4);
        System.out.println("da: " + da + " size:" + da.size);
        da.add(5);
        System.out.println("da: " + da + " size:" + da.size);
        da.add(6);
        System.out.println("da: " + da + " size:" + da.size);
        da.add(7);
        System.out.println("da: " + da + " 6's located at: " + da.find(6));
        System.out.println("da: " + da + " size:" + da.size);
        da.remove(7);
        System.out.println("da: " + da + " size:" + da.size);
        da.removeAt(0);
        System.out.println("da: " + da + " size:" + da.size);
    }
}
