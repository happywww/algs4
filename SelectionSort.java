public class SelectionSort {
    public static void sort(int[] a) {
        for(int i = 0; i < a.length; i++) {
            int min = i;
            for(int j = i+1; j < a.length; j++) {
                if(a[j] < a[min]) {
                    min = j;
                }
            }
            Utils.exch(a, i, min);
        }
    }
}