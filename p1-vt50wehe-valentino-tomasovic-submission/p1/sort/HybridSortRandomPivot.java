package p1.sort;

import java.util.Comparator;
import java.util.Random;

/**
 * An Variation of the {@link HybridSort} algorithm that uses a random pivot for partitioning.
 * @param <T> the type of the elements to be sorted.
 */
public class HybridSortRandomPivot<T> extends HybridSort<T> {

    /**
     * The random number generator used for generating the random pivot.
     */
    Random random = new Random();

    /**
     * Creates a new {@link HybridSortRandomPivot} instance.
     * @param k the threshold for switching from quickSort to insertionSort.
     * @param comparator the comparator used for comparing the sorted elements.
     */
    public HybridSortRandomPivot(int k, Comparator<T> comparator) {
        super(k, comparator);
    }

    private void swap(SortList <T> swapper, int i , int j){
        T tmp = swapper.get(i);
        swapper.set(i, swapper.get(j));
        swapper.set(j,tmp);
    }

    @Override
    public int partition(SortList<T> sortList, int left, int right) {
        int randomPivotElement = (int) ((Math.random()*(right-left+1))+left);
        swap(sortList,randomPivotElement,left);
        return partition(sortList,left,right);
    }
}
