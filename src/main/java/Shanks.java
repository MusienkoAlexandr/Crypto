import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;

public class Shanks {
    public static int logarithm (int a, int b, int p, FileOutputStream out) {
        int n = p - 1;
        int m = (int) Math.sqrt(n) + 1;
        int [][] Aj = new int[m][2];
        Aj[0][0] = 0;
        Aj[0][1] = 1;
        for (int j = 1; j < m; j++) {
            Aj[j][0] = j;
            Aj[j][1] = (int) Math.pow(a, j) % p;
        }
        Arrays.sort(Aj, Comparator.comparingInt(arr -> arr[1]));
        int inv_a = Base.inverse(a, p);
        int giant_step = inv_a;
        for (int l = 2; l <= m; l++) {
            giant_step = giant_step*inv_a % p;
        }
        int gamma = b;
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < m; k++) {
                if (Aj[k][1] == gamma) {
                    return i*m + Aj[k][0];
                }
            }
            gamma = (gamma * giant_step) % p;
        }
        return -1;
    }
}
