package davidul;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void countBytes() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(new File("src/test/resources/test.txt"));
        assertEquals(9, fileProcessor.countBytes());
    }

    @Test
    void countWords() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(new File("src/test/resources/test.txt"));
        assertEquals(2, fileProcessor.countWords());
    }

    @Test
    void countLines() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(new File("src/test/resources/test.txt"));
        assertEquals(1, fileProcessor.countLines());
    }

    @Test
    void readFiles() {
    }

    @Test
    void readFile() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(new File("src/test/resources/test.txt"));
        assertNotNull(fileProcessor.byteBuffer);
    }
}