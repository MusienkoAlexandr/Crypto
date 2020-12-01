import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(".//module.txt", true))
        {
            //Fermat.factorize(799, fileOutputStream);
            System.out.println(Shanks.logarithm(3, 19, 113, fileOutputStream));

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
