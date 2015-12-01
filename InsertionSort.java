package SortingAlgorithms;


/**
 * Created by Daniel on 2015/12/1.
 */
public class InsertionSort {
    public static void sort(int[] a) {
        for(int i = 1; i < a.length; i++) {
            for(int j = i; j > 0 && a[j] < a[j-1]; j--) {
                Sort.exch(a, j, j-1);
            }
        }
    }
}
