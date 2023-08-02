package p1.sort;

import p1.comparator.CountingComparator;

import java.util.Comparator;

/**
 * A hybrid sorting algorithm. It uses a combination of quickSort and insertionSort.
 * <p>
 * quickSort is used for sorting the lists of size greater than or equal to k.
 * <p>
 * insertionSort is used for sorting the lists of size less than k.
 *
 * @param <T> the type of the elements to be sorted.
 *
 * @see Sort
 */
public class HybridSort<T> implements Sort<T> {

    private void swap(SortList <T> swapper, int i , int j){
        //method swaps index i with index j
        //saving element at index i in temporary variable
        T tmp = swapper.get(i);
        //overwriting element on index i with element on index j
        swapper.set(i, swapper.get(j));
        //overwriting element at index j with element of temporary variable
        swapper.set(j,tmp);
    }

    /**
     * The threshold for switching from quickSort to insertionSort.
     */
    private int k;

    /**
     * The comparator used for comparing the sorted elements.
     */
    private final CountingComparator<T> comparator;

    /**
     * Creates a new {@link HybridSort} instance.
     *
     * @param k          the threshold for switching from quickSort to insertionSort.
     * @param comparator the comparator used for comparing the sorted elements.
     */
    public HybridSort(int k, Comparator<T> comparator) {
        this.k = k;
        this.comparator = new CountingComparator<>(comparator);
    }

    @Override
    public void sort(SortList<T> sortList) {
        comparator.reset();
        quickSort(sortList, 0, sortList.getSize() - 1);
    }

    @Override
    public int getComparisonsCount() {
        return comparator.getComparisonsCount();
    }

    /**
     * Returns the current threshold for switching from quickSort to insertionSort.
     * @return the current threshold for switching from quickSort to insertionSort.
     */
    public int getK() {
        return k;
    }

    /**
     * Sets the threshold for switching from quickSort to insertionSort.
     * @param k the new threshold.
     */
    public void setK(int k) {
        this.k = k;
    }

    /**
     * Sorts the given {@link SortList} using the quickSort algorithm.
     * It will only consider the elements between the given left and right indices (both inclusive).
     * Elements with indices less than left or greater than right will not be altered.
     * <p>
     * Once the size of the list is less than k, the algorithm switches to insertionSort.
     * @param sortList the {@link SortList} to be sorted.
     * @param left The leftmost index of the list to be sorted.
     * @param right The rightmost index of the list to be sorted.
     */
    public void quickSort(SortList<T> sortList, int left, int right) {
        //more then one element
        if(left < right) {
            //checking if number of containing elements are less then in k
            if (right - left + 1 < k) {
                //calling recursively insertionsort, if number of elements in sorting sectioan are less then in k
                insertionSort(sortList, left, right);
                //stopping quicksort after calling insertionsort
                return;
            }
        }
        //more then one element
        if(left < right){
            //initializing variable p as partition with given parameters, p partition index
            //p is the index : where all elements to the left are smaller than p, and right bigger than p
            int p = partition(sortList, left, right);
            //sort left part of list
            quickSort(sortList, left, p);
            //sort right part of list
            quickSort(sortList, p + 1, right);
        }
    }

    /**
     * Partitions the given {@link SortList} between the given left and right indices (both inclusive).
     * Elements with indices less than left or greater than right will not be altered.
     * <p>
     * The pivot is the element at the given left index.
     *
     * @param sortList the {@link SortList} to be partitioned.
     * @param left The leftmost index of the list to be partitioned.
     * @param right The rightmost index of the list to be partitioned.
     * @return An index between left and right (both inclusive) such that all elements to the left or at the index are less than or equal to the pivot,
     * and all elements to the right of the index are greater than or equal to the pivot.
     */
    public int partition(SortList<T> sortList, int left, int right) {
        //initializing variable pivot as left part of sortlist
        T pivot = sortList.get(left);
        //move from left resp. right
        //pl,pr are iterator
        int pl = left-1;
        int pr = right+1;
        //store current value
        int p = pr;
        //while pl is smaller then pr
        while(pl<pr){
            do{
                //moving pl forwards
                pl++;
                // UNTIL A[pl]>=pivot; //move left up
            }while(comparator.compare(sortList.get(pl),pivot ) < 0);

            do{
                //moving pr backwards
                pr--;
                //UNTIL A[pr]=<pivot; //move right down
            }while(comparator.compare(sortList.get(pr),pivot ) > 0);
            // checking if we need to swap pl and pr
            if(pl<pr){
                //swap pl and pr
                swap(sortList,pl,pr);
            }
            //store current value
            p = pr;
        }
        //// A[l..p] left, A[p+1..r] right
        return p;
    }

    /**
     * Sorts the given {@link SortList} using the insertionSort algorithm.
     * It will only consider the elements between the given left and right indices (both inclusive).
     * Elements with indices less than left or greater than right will not be altered.
     *
     * @param sortList the {@link SortList} to be sorted.
     * @param left The leftmost index of the list to be sorted.
     * @param right The rightmost index of the list to be sorted.
     */
    public void insertionSort(SortList<T> sortList, int left, int right) {
        //iteration in for loop from left+1 to right (over indices of sortlist)
        //left+1: need to inkrement left, bc the left element of left should be j and j should not be out of list
        for (int i = left +1; i <=right ; i++) {
            //initializing the key of sortlist. Key gets value of i (for-loop)
            T key = sortList.get(i);
            //j: search for insertion point backwards
            int j = i -1;
            //while element j is greater or equal of index left & j greater then keyvalue
            //>o checks if element is greater then the second element
            //move elements to the right until insertionpoint is found
            while (j >= left && comparator.compare(sortList.get(j),key)>0){
                //move element to right
                sortList.set(j+1,sortList.get(j));
                //moving backwards in the list to the last element
                j--;
            }
            //setting new key on next element
            sortList.set(j+1,key);
        }

    }

}
