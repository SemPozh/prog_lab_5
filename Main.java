import logics.FileHandler;
import sources.Texts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(Texts.HELLO_TEXT);
        String FINAL_NAME;


        try {
            FINAL_NAME = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println();
                System.out.print(Texts.NO_FILE_TEXT);
                FINAL_NAME = scanner.next();
            } while (!FileHandler.checkFile(FINAL_NAME));

        }


    }
}
