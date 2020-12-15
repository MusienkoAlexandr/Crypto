import java.io.IOException;

public interface User {
    long encrypt(long message) throws IOException;
    long decrypt(long cypher) throws IOException;
    long sign(long message) throws IOException;
    boolean verify(long signing, long cypher) throws IOException;
}
