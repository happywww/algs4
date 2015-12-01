public class Quick3way {

    public static void sort(int[] a) {
        sort(a, 0, a.length-1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if(lo >= hi) return;
        int lt = lo, i = lo+1, gt = hi;
        int v = a[lo];
        while(i <= gt) {
            if(a[i] < v) {
                exch(a, i, lt);
                i++;
                lt++;
            } else {
                if(a[i] == v) {
                    i++;
                } else {
                    if(a[i] > v) {
                        exch(a, i, gt);
                        gt--;
                    }
                }
            }
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
}