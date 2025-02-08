package davidul;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {

    ByteBuffer byteBuffer;

    public int countBytes() {
        return byteBuffer.capacity();
    }

    public int countWords() {
        int count = 0;
        int state = 0;
        char[] charArray = new String(byteBuffer.array()).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '\\') {
                count++;
                continue;
            }
            if (Character.isLetterOrDigit(charArray[i])) {
                if (state == 0) {
                    count++;
                    state = 1;
                }
            } else {
                state = 0;
            }
        }
        return count;
    }

    public int countLines() {
        int count = 0;
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            if (byteBuffer.get(i) == 10) {
                count++;
            }
        }
        return count;
    }

    public void readFiles() {

    }

    public void readFile(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            byte[] fileBytes = Files.readAllBytes(path);
            byteBuffer = ByteBuffer.wrap(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
