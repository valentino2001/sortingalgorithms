package p1.sort;

import p1.comparator.CountingComparator;

import java.util.Comparator;

/**
 * A sorting algorithm that uses the stoneSort algorithm.
 * <p>
 * StoneSort is a variation of the bubbleSort algorithm where "low elements are falling down like stones" instead of
 * "high element are rising up like bubbles".
 * @param <T> the type of the elements to be sorted.
 */
public class StoneSort<T> implements Sort<T> {

    /**
     * The comparator used for comparing the sorted elements.
     */
    private final CountingComparator<T> comparator;

    /**
     * Creates a new {@link StoneSort} instance.
     * @param comparator the comparator used for comparing the sorted elements.
     */
    public StoneSort(Comparator<T> comparator) {
        this.comparator = new CountingComparator<>(comparator);
    }

    private void swap(SortList <T> swapper, int i , int j) {
        T tmp = swapper.get(i);
        swapper.set(i, swapper.get(j));
        swapper.set(j, tmp);
    }
    @Override
    public void sort(SortList<T> sortList) {
        //resetting the comparator
        comparator.reset();
        //Get the size of the Sortlist
        int n = sortList.getSize();
        //iterate over the elements of the sortlist
        //n-2 condition is to ensure that the loop iterates up to the second to last element in the loop
        for(int i = 0; i<=n-2; i++){
            // Iterate from the last element to the (i+1)-th element
            for(int j = n-1; j>= i+1;j--){
                // Compare the elements at index (j-1) and j using the comparator
                if (comparator.compare(sortList.get(j-1), sortList.get(j) )>0){
                    // If the comparison result is greater than 0, swap the elements
                    swap(sortList,j-1,j);
                }
            }
        }
    }

    @Override
    public int getComparisonsCount() {
        return comparator.getComparisonsCount();
    }

}
