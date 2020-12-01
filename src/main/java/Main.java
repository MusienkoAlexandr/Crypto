import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(".//module.txt", true))
        {
            Fermat.factorize(3880941257L, fileOutputStream);
            Shanks.logarithm(6, 32, 229, fileOutputStream);
            //RSA rsa = new RSA(24, 45, 67);

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
