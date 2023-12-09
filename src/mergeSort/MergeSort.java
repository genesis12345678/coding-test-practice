package mergeSort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9,2,4,6,8,0};
        sort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int arrLength) {

        if (arrLength < 2) {
            return;
        }

        int mid = arrLength / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[arrLength - mid];

        for (int i = 0; i < mid; i++) {
            leftArray[i] = arr[i];
        }
        for (int i = mid; i < arrLength; i++) {
            rightArray[i - mid] = arr[i];
        }


        sort(leftArray, leftArray.length);

        sort(rightArray, rightArray.length);

        merge(arr, leftArray, rightArray, leftArray.length, rightArray.length);
    }

    public static void merge(int[] arr, int[] leftArray, int[] rightArray, int leftArrayLength, int rightArrayLength) {
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;


        while (leftIndex < leftArrayLength && rightIndex < rightArrayLength) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                arr[currentIndex] = leftArray[leftIndex];
                currentIndex++;
                leftIndex++;
            } else {
                arr[currentIndex] = rightArray[rightIndex];
                currentIndex++;
                rightIndex++;
            }
        }

        while (leftIndex < leftArrayLength) {
            arr[currentIndex] = leftArray[leftIndex];
            currentIndex++;
            leftIndex++;
        }

        while (rightIndex < rightArrayLength) {
            arr[currentIndex] = rightArray[rightIndex];
            currentIndex++;
            rightIndex++;
        }
    }


}
