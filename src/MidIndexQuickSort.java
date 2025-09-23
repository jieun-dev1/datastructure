/**
 * 왜 같은 수가 연속으로 배치되나?
 *
 * 파티션의 성질: 각 파티션 후에는 피벗보다 작은 값들이 왼쪽, 큰 값들이 오른쪽에 위치
 * 같은 값의 집중: 피벗과 같은 값들은 파티션 경계 근처에 모이게 됨
 * 재귀적 정렬: 각 부분배열에서 같은 과정이 반복되면서 같은 값들이 점점 더 가까이 모임
 * 최종 수렴: 모든 재귀가 끝나면 같은 값들이 연속된 위치에 배치됨
 *
 *
 * [pivot보다 작은 값들] [pivot보다 크거나 같은 값들]
 *                     ↑
 *                   part2 (반환된 start)
 *
 *
 */

public class MidIndexQuickSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        else quickSort(arr, 0, arr.length -1);

    }
    public static void quickSort(int[] arr, int start, int end){
        //Get the right split partition's first value
        int part2 = partition(arr, start, end);
        //explore where smaller than pivor
        if (start < part2 -1) {
            quickSort(arr, start, part2-1);
        }
        //explore where bigger and equal to pivot
        if (part2 < end) {
            quickSort(arr, part2, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start + end) / 2];
        while (start <= end) {
            while (arr[start] < pivot) start++;
            while (arr[end] > pivot) end--;
            if (start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void printArray(int[] arr) {
        for (int data : arr) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {3,9,4,7,5,0,1,6,8,2};
        int[] dupArr = {3,8,8,9,5,6,1,6,4,2};

        printArray(dupArr);
        sort(dupArr);
        printArray(dupArr);
    }
}
