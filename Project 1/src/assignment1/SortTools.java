// SortTools.java

/*
 * EE422C Project 1 submission by
 * Zahra Atzuri
 * zfa84
 * 15500
 * Spring 2018
 * Slip days used: 0
 */

package assignment1;

import java.util.Arrays;

public class SortTools {
    /**
     * This method tests to see if the given array is sorted.
     * @param nums is the array
     * @param n is the size of the input to be checked
     * @return true if array is sorted
     */
    public static boolean isSorted(int[] nums, int n) {
        for (int i = 1; i < n; i++) {
            if (nums[i-1] > nums[i])
                return false;   // stop checking if its greater
        }
        return true;
    }

    /**
     * This method tests to see if a value is found in the array.
     * @param nums is the array
     * @param n is the size of the input to be checked
     * @param v is the value to be checked
     * @return int if value is found
     */
    public static int find(int[] nums, int n, int v) {
        int min = 0;    // lowest index
        int max = n-1;  // highest index

        while (min <= max) {
            int mid = (max + min) / 2;  // middle index
            // the value is in the middle
            if (nums[mid] == v)
                return mid;
            // the value is less than the middle
            else if (nums[mid] > v)
                max = mid - 1;
            // the value is greater than the middle
            else if (nums[mid] < v)
                min = mid + 1;
        }
        return -1;
    }

    /**
     * This method creates a new array with the value (if necessary) and new length.
     * @param nums is the array
     * @param n is the size of the input to be checked
     * @param v is the value to be checked
     * @return a new array
     */
    public static int[] insertGeneral(int[] nums, int n, int v) {
        // value is not found
        if (find(nums, n, v) == -1) {
            // make a new array with n+1 length, inserts value, and sort array
            int[] newArr = Arrays.copyOf(nums,n+1); // O(n)
            newArr[n] = v;
            insertSort(newArr,newArr.length);
            return newArr;
        }
        // value is found
        else {
            // copy old array to a new one
            int[] newArr = Arrays.copyOf(nums,n);
            return newArr;
        }
    }

    /**
     * This method inserts a value in an array in the specified length.
     * @param nums is the array
     * @param n is the size of the input to be checked
     * @param v is the value to be checked
     * @return the size of the input
     */
    public static int insertInPlace(int[] nums, int n, int v) {
        // value is not found
        if (find(nums, n, v) != -1) {
            return n;
        }
        // value is found
        else {
            nums[n] = v;    // insert value at index n

            // swap from the index n to the start of array
            for (int i = n; i > 0; i--) {
                if(nums[i-1] > nums[i]) {
                    int temp =  nums[i-1];
                    nums[i-1] = nums[i];
                    nums[i] = temp;
                }
            }
            return n+1;
        }
    }

    /**
     * This method inserts a value in an array in the specified length.
     * @param nums is the array
     * @param n is the size of the input to be checked
     */
    public static void insertSort(int[] nums, int n) {
        // outer loop
        for(int i = 1; i < n; i++) {
            // inner loop to sort in between
            for (int j = i; j > 0; j--) {
                // swap values at index
                if(nums[j-1] > nums[j]) {
                    int temp =  nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                } else
                    break;  // for O(n) in best case
            }
        }
    }
}
