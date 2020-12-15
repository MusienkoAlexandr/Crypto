import java.io.FileOutputStream;
import java.io.IOException;

public class UserDiffieHellman implements User{

    public String name;
    private long secretValue;
    private long sharedSecretValue;
    public long base;
    public long p;
    private FileOutputStream out;

    public UserDiffieHellman(String name, long p, long base, long secretValue, String otherName,
                             long messageForConnection, FileOutputStream out) throws IOException {
        this.out = out;
        StringBuilder sb = new StringBuilder();
        sb.append("Ініціалізуємо нового користувача алгоритму Діффі-Хелмана з іменем " + name + " з таємним значенням x = " +
                secretValue + ".\nВідкриті параметри: модуль p = " + p + ", твірний g = " + base + ".\n");
        this.name = name;
        this.secretValue = secretValue;
        this.base = base;
        this.p = p;
        this.sharedSecretValue = Base.powerByModule(messageForConnection, secretValue, p);
        sb.append("Користувач " + this.name + " отримав від користувача " + otherName + " повідомлення Y = g^y = " +
                messageForConnection + ".\n" + this.name + " у свою чергу надсилає X = g^x = " +
                Base.powerByModule(base, secretValue, p) + "\nі обчислює спільне таємне значення Y^x = g^(xy) = " +
                this.sharedSecretValue + ". \n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);

    }

    @Override
    public long encrypt(long message) throws IOException {
        return 0;
    }

    @Override
    public long decrypt(long cypher) throws IOException {
        return 0;
    }

    @Override
    public long sign(long message) throws IOException {
        return 0;
    }

    @Override
    public boolean verify(long signing, long cypher) throws IOException {
        return false;
    }
}
