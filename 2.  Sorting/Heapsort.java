public class Heapsort {

    public static void sort(int[] a) {
        int N = a.length-1;
        for(int i = N/2; i >= 1; i--) {
            sink(a, i, N);
        }
        while(N >= 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(int[] a, int k, int n) {
        while (2*k <= n) {
            int i = 2*k;
            if(i < n && a[i] < a[i+1]) {
                i++;
            }
            if(a[k] >= a[i]) {
                break;
            }
            exch(a, k, i);
            k = i;
        }
    }
}