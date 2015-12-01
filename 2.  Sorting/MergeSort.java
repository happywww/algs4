public class MergeSort  {
    private static int[] aux;

    public static void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length-1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if(hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(int[] a, int lo, int mid, int hi) {
        System.arraycopy(a, 0, aux, 0, a.length);
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) {
                a[k] = aux[j];
                j++;
            } else {
                if (j > hi) {
                    a[k] = aux[i];
                    i++;
                } else {
                    if(aux[i] <= aux[j]) {
                        a[k] = aux[i];
                        i++;
                    } else {
                        a[k] = aux[j];
                        j++;
                    }
                }
            }
        }
    }
}