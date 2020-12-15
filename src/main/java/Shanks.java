import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Shanks {
    public static long logarithm (long a, long b, long p, FileOutputStream out) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        long n = p - 1;
        stringBuilder.append("Знайдемо дискретний логарифм " + b + " за основою " + a + " у мультиплікативній групі лишків за модулем " +
                p + ". Порядок групи: " + n + "\n");

        int m = (int) Math.sqrt(n) + 1;
        long[][] Aj = new long[m][2];
        stringBuilder.append("m = " + m + ". Для j = 0..m-1 знайдемо a^j та відсортуємо за значенням a^j:\n");
        Aj[0][0] = 0;
        Aj[0][1] = 1;
        for (int j = 1; j < m; j++) {
            Aj[j][0] = j;
            Aj[j][1] = (Aj[j-1][1]*a) % p;
        }
        Arrays.sort(Aj, Comparator.comparingInt(arr -> (int) arr[1]));
        stringBuilder.append("j\t");
        for (int l = 0; l < m; l++) {
            stringBuilder.append(Aj[l][0] + "\t");

        }
        stringBuilder.append("\n");
        stringBuilder.append("a^j\t");
        for (int l = 0; l < m; l++) {
            stringBuilder.append(Aj[l][1] + "\t");

        }
        stringBuilder.append("\n");
        long inv_a = Base.inverse(a, p);
        long giant_step = Base.powerByModule(inv_a, m, p);
        stringBuilder.append("a^(-1) = " + inv_a + ". Тоді a^(-m) = " + giant_step + ".\n");
        long gamma = b;
        for (int i = 0; i < m; i++) {
            stringBuilder.append("b*a^(-" + i + "m) = " + gamma + ". ");
            for (int k = 0; k < m; k++) {
                if (Aj[k][1] == gamma) {
                    stringBuilder.append("Це відповідає j = " + Aj[k][0] + " в таблиці a^j. Отже, шуканий логарифм дорівнює " +
                            "i*m + j = " + (i*m + Aj[k][0]) + ".\n\n");
                    byte[] buffer = stringBuilder.toString().getBytes();
                    out.write(buffer);
                    return i*m + Aj[k][0];
                }
            }
            stringBuilder.append("Такого числа в таблиці a^j немає. Робимо наступний гігантський крок.\n");
            gamma = (gamma * giant_step) % p;
        }
        stringBuilder.append("Не вдалося знайти дискретний логарифм " + b + " за основою " + a +
                " у мультиплікативній групі лишків за модулем " + p + ".\n\n");
        byte[] buffer = stringBuilder.toString().getBytes();
        out.write(buffer);
        return -1;
    }
}
