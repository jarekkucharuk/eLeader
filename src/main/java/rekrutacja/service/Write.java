package rekrutacja.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Write {

    public void writeFile(List<String> lines) throws IOException {

        Files.write(Paths.get("t:/output.xml"), lines);

    }
}
