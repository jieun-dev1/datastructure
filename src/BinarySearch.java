
public class BinarySearch {

    public int binarySearchRecursion(int[] intArray, int left, int right, int target) {
        if (left > right) return -1;

        int mid = (left + right) / 2;
        if (target == intArray[mid]) return mid;

        else if (intArray[mid] > target) {
            return binarySearchRecursion(intArray, left, mid-1, target);
        } else {
            return binarySearchRecursion(intArray, mid+1, right, target);
        }
    }

    public int binarySearch(int[] intArray, int target) {
        int left = 0;
        int right = intArray.length -1 ;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > intArray[mid]) {
                left = mid + 1;
            } else if (target < intArray[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int recursionResult = bs.binarySearchRecursion(new int[]{0,11,20,30,44,55,66}, 0, 6, 20);
        int result = bs.binarySearch(new int[]{0,11,20,30,44,55,66},  20);

        System.out.println("recursionResult: " + recursionResult);

        System.out.println("result: " + result);
    }
}
