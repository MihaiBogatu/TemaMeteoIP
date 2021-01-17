package ro.mta.se.lab;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    private static String[][] file;
    public static String[][] readFile(String path) {
            try{
                // count lines number
                File myObj = new File(path);
                Scanner myReader = new Scanner(myObj);
                int lines=0;
                while (myReader.hasNextLine()) {
                    lines ++;
                    String s = myReader.nextLine();
                }

                // store data in matrix
                file = new String[lines][5];
                myReader = new Scanner(myObj);
                int line=0,column=0;
                while (myReader.hasNextLine()) {
                    Scanner scanner = new Scanner(myReader.nextLine());
                    while (scanner.hasNext()) {
                        file[line][column] = scanner.next();
                        column ++;
                    }
                    line ++;
                    column = 0;
                }

                myReader.close();
                return file;
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while reading file.");
                e.printStackTrace();
            }
        return file;
    }
}