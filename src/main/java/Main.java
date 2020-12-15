import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(".//exam.txt", true))
        {
            UserRSA u1 = new UserRSA("Анна", 13, 3053, fileOutputStream);
            u1.encrypt(123);
            u1.decrypt(1894);

            UserElGamal u2 = new UserElGamal("Анна", 509, 2, 118, 118, fileOutputStream);
            UserElGamal u3 = new UserElGamal("Борис", 509, 2, 202, 509, fileOutputStream);
            u3.encrypt(123, 12);
            u2.decrypt(new long[]{128, 38});

            UserElGamal u4 = new UserElGamal("Борис", 509, 2, 1, 1, fileOutputStream);
            u4.decrypt(new long[]{12, 13});

            UserElGamal u5 = new UserElGamal("Світлана", 509, 2, 118, 118, fileOutputStream);
            u5.verify(new long[]{60,445}, 108);
            u5.verify(new long[]{360,31}, 201);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
