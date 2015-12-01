public static void Shellsort(int[] a) {
    int h = 1;
    while (h < a.length/3) {
        h = h * 3 + 1;
    }
    while(h >= 1) {
        for(int i = h; i < a.length; i++) {
            for(int j = i; j-h >= 0 && a[j] < a[j-h]; j -= h) {
                exch(a, j, j-h);
            }
        }
        h = h / 3;
    }
}