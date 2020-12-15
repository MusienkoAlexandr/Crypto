import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(".//exam.txt", true))
        {
            //Fermat.factorize(3880941257L, fileOutputStream);
            //Shanks.logarithm(6, 32, 229, fileOutputStream);
            //UserRSA alice = new UserRSA("Аглая", 16561, 3933700003L, fileOutputStream);
            //UserRSA bob = new UserRSA("Броніслав", 17471, 4068384967L, fileOutputStream);
            //bob.verify(482467467L, 710171332L);
            //alice.decrypt(710171332L);
            //bob.encrypt(24081572);
            //alice.sign(24081572);
            //UserDiffieHellman boris = new UserDiffieHellman("Борис", 509, 2, 103, "Анна",
            //        172, fileOutputStream);
            UserElGamal u1 = new UserElGamal("Адріяна", 229, 6,
                    113, 85, fileOutputStream);
            u1.decrypt(u1.encrypt(65, 7));
            u1.verify(u1.sign(123, 79), 123);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
