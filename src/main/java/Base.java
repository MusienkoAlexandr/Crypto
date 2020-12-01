public class Base {
    public static int inverse (int a, int module) {
        int [] x = new int[2];
        int d = extendedGCD(a, module, x);
        if (d > 1) {
            return -1;
        } else {
            return x[0];
        }
    }
    public static int extendedGCD (int a, int b, int[] x) {
        //  a < b
        if (a == 0) {
            x[0] = 0;
            x[1] = 1;
            return b;
        }
        int [] x1 = new int[2];
        int d = extendedGCD(b%a, a, x1);
        x[0] = x1[1] - (b / a) * x1[0];
        x[1] = x1[0];
        return d;
    }
}
