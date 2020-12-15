import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Base {
    public static long inverse (long a, long module) throws IOException {
        long [] x = new long[2];
        long d = extendedGCD(a, module, x);
        if (d > 1) {
            return 1357;
        }
        if (x[0] < 0) {
            return (module + x[0]);
        } else {
            return x[0];
        }

    }
    public static long extendedGCD (long a, long b, long[] x) {
        //  a < b
        if (a == 0) {
            x[0] = 0;
            x[1] = 1;
            return b;
        }
        long [] x1 = new long[2];
        long d = extendedGCD(b%a, a, x1);
        x[0] = x1[1] - (b / a) * x1[0];
        x[1] = x1[0];
        return d;
    }
    public static long powerByModule (long base, long power, long module) {
        long result = base;
        for (long i = 2; i <= power; i++) {
            result = (result * base) % module;
        }
        return result;
    }
}
