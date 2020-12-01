import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(".//module.txt", true))
        {
            Fermat.factorize(799, fileOutputStream);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
