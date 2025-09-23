import java.util.ArrayList;
import java.util.List;


/**
 * worst time complexity:
 * max heap -
 */
public class MaxHeapPriorityQueue<T extends Comparable<T>> {
    private List<T> heap;

    public MaxHeapPriorityQueue() {
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     *
     * log n (when inserting n, n * log n)
     */
    public void insert(T item) {
        heap.add(item);
        heapifyUp(heap.size() -1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Priority Queue is Empty");
        }
        return heap.get(0);
    }

    /**
     * move last element to the root and heapifyDown
     * @return
     */
    public T extractMax() {
        if (isEmpty()) {
            throw new RuntimeException("Priority Queue is Empty");
        }
        T max = heap.get(0);
        T lastItem = heap.remove(heap.size() -1);
        if (!isEmpty()) {
            heap.set(0, lastItem);
            heapifyDown(0);
        }
        return max;
    }

    public int size() {
        return heap.size();
    }
    /**
     *
     */
    /**
     *
     * @param index
     */
    private void heapifyUp(int index) {
        if (index == 0) return;

        int parentIndex = (index -1) / 2;

        if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    /**
     * if child node is smaller than parentNode, switch
     * @param index
     */
    private void heapifyDown (int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largest = index;

        //compare with left
        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(largest))> 0) {
            largest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(largest))> 0) {
            largest = rightChildIndex;
        }
        if (index != largest) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {
        MaxHeapPriorityQueue<Integer> pq = new MaxHeapPriorityQueue<>();

        // 요소 삽입
        System.out.println("요소 삽입: 3, 1, 4, 1, 5, 9, 2, 6");
        pq.insert(3);
        pq.insert(1);
        pq.insert(4);
        pq.insert(1);
        pq.insert(5);
        pq.insert(9);
        pq.insert(2);
        pq.insert(6);

        System.out.println("현재 힙 상태: " + pq);
        System.out.println("크기: " + pq.size());

        // 최고 우선순위 요소 조회
        System.out.println("\n최고 우선순위 요소: " + pq.peek());

        // 요소들을 우선순위 순으로 제거
        System.out.println("\n우선순위 순으로 제거:");
        while (!pq.isEmpty()) {
            System.out.print(pq.extractMax() + " ");
        }
        System.out.println();

        // 문자열로 테스트
        System.out.println("\n=== 문자열 우선순위 큐 테스트 ===");
        MaxHeapPriorityQueue<String> stringPQ = new MaxHeapPriorityQueue<>();
        stringPQ.insert("banana");
        stringPQ.insert("apple");
        stringPQ.insert("cherry");
        stringPQ.insert("date");

        System.out.println("문자열 힙: " + stringPQ);
        System.out.println("사전순으로 마지막: " + stringPQ.extractMax());
        System.out.println("그 다음: " + stringPQ.extractMax());
    }
}
