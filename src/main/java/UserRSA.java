import java.io.FileOutputStream;
import java.io.IOException;

public class UserRSA implements User {
    public String name;
    private long d;
    public long e;
    public long n;
    private long p;
    private long q;
    private FileOutputStream out;
    public UserRSA(String name, long e, long n, FileOutputStream out) throws IOException {
        this.out = out;
        StringBuilder sb = new StringBuilder();
        sb.append("Ініціалізуємо нового користувача RSA з іменем " + name + " з відкритими ключами n = " + n +
                ", e = " + e +".\n\n");
        this.name = name;
        this.e = e;
        this.n = n;
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        sb = new StringBuilder();
        long [] pq = Fermat.factorize(n, out);
        this.p = pq[0];
        this.q = pq[1];
        this.d = Base.inverse(e, (p-1)*(q-1));
        sb.append("Тоді таємний параметр d = e^(-1) (mod (p-1)(q-1)) = " + this.d + ".\n\n");
        buffer = sb.toString().getBytes();
        this.out.write(buffer);
    }

    @Override
    public long encrypt(long message) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Зашифруємо повідомлення " + message + ", призначене користувачу " + this.name + ".\n");
        long cypher = Base.powerByModule(message, e, n);
        sb.append("Шифртекст C = M^e (mod n) = " + cypher + ".\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return cypher;
    }

    @Override
    public long decrypt(long cypher) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Розшифруємо шифртекст " + cypher + ", що надійшов користувачу " + this.name + ".\n");
        long message = Base.powerByModule(cypher, d, n);
        sb.append("Початкове повідомлення M = C^d (mod n) = " + message + ".\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return message;
    }

    @Override
    public long sign(long message) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Підпишемо повідомлення " + message + " від руки користувача " + this.name + ".\n");
        long signing = Base.powerByModule(hash(message), d, n);
        sb.append("Цифровий підпис S = H^d (mod n) = " + signing + ".\nВ даному випадку хеш-функція H(M) = M.\n\n");
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return signing;
    }

    @Override
    public boolean verify(long signing, long cypher) throws IOException {
        boolean verification;
        StringBuilder sb = new StringBuilder();
        sb.append("Перевіримо справжність підпису " + signing + " користувача " + this.name + " для повідомлення " +
                cypher + ".\n");
        long h = Base.powerByModule(signing, e, n);
        sb.append("Якщо цифровий підпис S справжній, то S^e = H(M) .\nВ даному випадку хеш-функція H(M) = M.\n" +
                "У нас S^e = " + h + ", H(M) = " + hash(cypher) + ".\n");
        if (h == hash(cypher)) {
            verification = true;
            sb.append("Отже, підпис справжній.\n\n");
        }
        else {
            verification = false;
            sb.append("Отже, підпис не є справжнім.\n\n");
        }
        byte[] buffer = sb.toString().getBytes();
        this.out.write(buffer);
        return verification;
    }

    public long hash(long message) {
        return message;
    }
}
