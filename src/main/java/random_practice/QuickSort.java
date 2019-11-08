package random_practice;

import java.util.Arrays;

/**
 * Created by udaythota on 10/31/19.
 * Quick sort practice
 */
public class QuickSort {
    private static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSortWrapper(arr, 0, arr.length-1);
    }

    private static void quickSortWrapper(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        int i = low, j = high;      // make left < pivot and right > pivot

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j) {
            quickSortWrapper(arr, low, j);
        }

        if (high > i) {
            quickSortWrapper(arr, i, high);
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 1, 9, 3, 6, 12, 20, 11};
        quickSort(test);
        System.out.println(Arrays.toString(test));
    }
}
