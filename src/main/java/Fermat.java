import java.io.FileOutputStream;
import java.io.IOException;

public class Fermat {

    public static long[] factorize(long n, FileOutputStream out) throws IOException {
        long [] result = new long[2];
        long t = (long)Math.ceil(Math.sqrt(n));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Розкладемо " + n + " у добуток методом Ферма.\n");
        long s = 0;
        long i;
        for (i = 0; i < t; i++) {
            stringBuilder.append("Крок " + (i + 1) + ". t = " + t + ", t^2 = " + (t*t) + ", s^2 = " + (t*t - n));
            s = (long) Math.sqrt(t*t - n);
            if (s*s == t*t - n) {
                stringBuilder.append(", s = " + s + ". Отже, " + n + " = " + (t - s) + " * " + (t + s) + ".\n\n");
                break;
            } else {
                stringBuilder.append(", s^2 не є повним квадратом. Шукаємо далі.\n");
                t++;
            }
        }
        if (i == t){
            stringBuilder.append("Не вдалося ;(\n\n");
            result[0] = result[1] = -1;
        }
        else {
            result[0] = t;
            result[1] = s;
        }
        byte[] buffer = stringBuilder.toString().getBytes();
        out.write(buffer);
        return result;
    }
}
