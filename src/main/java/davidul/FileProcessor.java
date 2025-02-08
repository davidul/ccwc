package davidul;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public List<Counter> readFiles(File[] files) {
        List<Counter> counters = new ArrayList<>();
        for (File file : files) {
            Counter counter = readFile(file);
            if(counter != null) {
                counters.add(counter);
            }
        }
        return counters;
    }

    public Counter readFile(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            byte[] fileBytes = Files.readAllBytes(path);
            return new Counter(ByteBuffer.wrap(fileBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
