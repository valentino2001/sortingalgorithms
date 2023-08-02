package p1.sort;

import java.util.Arrays;

/**
 * Optimizes the {@link HybridSort} by trying to find the k-value with the lowest number of read and write operations..
 */
public class HybridOptimizer {

    /**
     * Optimizes the {@link HybridSort} by trying to find the k-value with the lowest number of read and write operations.
     * The method will try all k-values starting from and return the k-value with the lowest number of read and write operations.
     * It will stop once if found the first local minimum or reaches the maximum possible k-value for the size of the given array.
     *
     * @param hybridSort the {@link HybridSort} to optimize.
     * @param array the array to sort.
     * @return the k-value with the lowest number of read and write operations.
     * @param <T> the type of the elements to be sorted.
     */
    public static <T> int optimize(HybridSort<T> hybridSort, T[] array) {
        // Initialize the sum of operations counter
        int sumOfOps = 0;
        // Iterate over the array
        //if k bigger than array.length,quicksort is never called
        for(int k=0; k< array.length; k++ ){
            // Set the value of 'k' in the HybridSort instance
            hybridSort.setK(k);
            // Create a new ArraySortList instance using the array
            ArraySortList<T> arraySortList = new ArraySortList<T>(array);
            // Perform the sorting operation using the hybridSort instance
            hybridSort.sort(arraySortList);
            // Get the read and write operations counts from the arraySortList
            int readOps = arraySortList.getReadCount();
            int writeOps = arraySortList.getWriteCount();
            // Calculate the total operations count for this iteration
            int tmp = readOps+writeOps;
            // Compare the total operations count with the current sumOfOps
            //checking for the first minimum in array
            if(tmp <= sumOfOps){
                // If the total operations count is less than or equal to the current sumOfOps, update sumOfOps
                sumOfOps = tmp;
            }else{
                // If the total operations count is greater than sumOfOps, we have reached the optimal 'k' value
                if(k > 0){
                    //returning the optimal value k-1, because k is not optimal
                    return  k-1;
                }else {
                    // If k is 0, update sumOfOps with the current total operations count
                    sumOfOps = tmp;
                }
            }
        }
        // Return array.length-1 as the optimal 'k' value if no previous return statement was executed
        return array.length-1;
    }

}
