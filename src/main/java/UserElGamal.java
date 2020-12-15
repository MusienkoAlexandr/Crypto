import java.io.FileOutputStream;
import java.io.IOException;

public class UserElGamal {

    public String name;
    public long p;
    public long base;
    private long cypherSecretKey;
    private long signSecretKey;
    public long cypherPublicKey;
    public long signPublicKey;
    private FileOutputStream out;


    public UserElGamal(String name, long p, long base, long cypherSecretKey,
                       long signSecretKey, FileOutputStream out) throws IOException {
        this.out = out;
        StringBuilder sb = new StringBuilder();
        sb.append("Ініціалізуємо нового користувача системи Ель-Гамаля з іменем " + name +
                "\nз відкритими параметрами p = " + p + ", твірним a = " + base + "\nта таємними ключами: " +
                "для шифрування x = " + cypherSecretKey + ", для підпису s = " + signSecretKey + ".\n");
        this.name = name;
        this.p = p;
        this.base = base;
        this.cypherSecretKey = cypherSecretKey;
        this.signSecretKey = signSecretKey;
        this.cypherPublicKey = Base.powerByModule(base, cypherSecretKey, p);
        this.signPublicKey = Base.powerByModule(base, signSecretKey, p);
        sb.append("Тоді відкритими ключами " + name + " є:\nдля шифрування b = a^x (mod p) = " + this.cypherPublicKey +
                "\nдля підпису v = a^s (mod p) = " + this.signPublicKey + ".\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
    }

    public long[] encrypt(long message, long key) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Зашифруємо для користувача " + this.name + " повідомлення m = " + message +
                ".\nВ якості разового таємного ключа використаємо k = " + key + ".\n");
        long[] cypher = new long[2];
        cypher[0] = Base.powerByModule(base, key, p);
        cypher[1] = (Base.powerByModule(cypherPublicKey, key, p)*message) % p;
        sb.append("Отримуємо шифртекст (c1, c2), де\nc1 = a^k (mod p) = " + cypher[0] +
                ",\nc2 = b^k*m (mod p) = " + cypher[1] + ".\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return cypher;
    }


    public long decrypt(long[] cypher) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Розшифруємо для користувача " + this.name + " отриманий шифртекст (c1, c2), де  с1 = "
                + cypher[0] + ", c2 = " + cypher[1] + ".\n");
        long message = (Base.powerByModule(Base.inverse(cypher[0], p), cypherSecretKey, p) * cypher[1]) % p;
        sb.append("Вихідне повідомлення m = c1^(-x)*c2 (mod p) = " + message + ".\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return message;
    }


    public long[] sign(long message, long key) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Підпишемо повідомлення m = " + message + " від руки користувача " + name +
                ",\n в якості разового таємного ключа візьмемо e = " + key + ".\n");
        long[] signing = new long[2];
        signing[0] = Base.powerByModule(base, key, p);
        signing[1] = (Base.inverse(key, p - 1) * (message - signSecretKey * signing[0])) % (p - 1);
        signing[1] = (signing[1] >= 0) ? signing[1] : p - 1 + signing[1];
        sb.append("Отримуємо підпис (S1, S2), де\nS1 = a^e (mod p) = " + signing[0] +
                ",\nS2 = (m - s*S1)*e^(-1) (mod p - 1) = " + signing[1] + ".\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return signing;
    }


    public boolean verify(long[] signing, long cypher) throws IOException {
        StringBuilder sb = new StringBuilder();
        long[] u = new long[2];
        boolean verification;
        sb.append("Перевіримо справжність цифрового підпису (S1, S2) користувача " + name +
                " для повідомлення m = " + cypher + ", де\nS1 = " + signing[0] + ", S2 = " + signing[1] + ".\n");
        u[0] = (Base.powerByModule(signPublicKey, signing[0], p) * Base.powerByModule(signing[0], signing[1], p)) % p;
        u[1] = Base.powerByModule(base, cypher, p);
        sb.append("Обчислюємо u1 = v^(S1)*S1^(S2) (mod p) = " + u[0] + ", u2 = a^m (mod p) = " + u[1] + ".\n");
        if (u[0] == u[1]) {
            verification = true;
            sb.append("u1 дорівнює u2, отже, підпис справжній.\n\n");
        }
        else {
            verification = false;
            sb.append("u1 не дорівнює u2, отже, підпис не є справжнім.\n\n");
        }
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return verification;
    }
}
