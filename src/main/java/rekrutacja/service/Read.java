package rekrutacja.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {

    public List<String> readFile() {

        List<String> lines = new ArrayList<>();
        File accountsFile = new File("input.xml");

        try {
            Scanner scanner = new Scanner(accountsFile);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file to read.");
        }

        return lines;
    }
}
