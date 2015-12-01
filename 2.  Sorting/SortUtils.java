public static void exch(int[] a, int i, int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
}

public static void show(int[] a) {
    for(int e : a) {
        System.out.print(e + " ");
    }
    System.out.println();
}

public static boolean isSorted(int[] a) {
    for(int i = 1; i < a.length - 1; i++) {
        if(a[i] > a[i+1]) {
            return false;
        }
    }
    return true;
}

public static void main(String[] args) {
    Random r = new Random();
    int[] a = new int[10];
    for(int i = 0; i < a.length; i++) {
        a[i] = r.nextInt(80);
    }
    show(a);
    sort(a);
    show(a);
    System.out.print(isSorted(a));
}