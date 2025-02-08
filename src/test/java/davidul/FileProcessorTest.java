package davidul;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileProcessorTest {

    @Test
    void countBytes() {

        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/empty.txt"));
        assertEquals(0, counter.countBytes());

        Counter counter1 = FileProcessor.getInstance().readFile(new File("src/test/resources/empty_new_lines.txt"));
        assertEquals(4, counter1.countBytes());

        Counter counter2 = FileProcessor.getInstance().readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertEquals(9, counter2.countBytes());

        Counter counter3 = FileProcessor.getInstance().readFile(new File("src/test/resources/new_line_at_end.txt"));
        assertEquals(18, counter3.countBytes());

    }

    @Test
    void countWords() {
        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/empty.txt"));
        assertEquals(0, counter.countWords());

        Counter counter1 = FileProcessor.getInstance().readFile(new File("src/test/resources/empty_new_lines.txt"));
        assertEquals(0, counter1.countWords());

        Counter counter2 = FileProcessor.getInstance().readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertEquals(2, counter2.countWords());

        Counter counter3 = FileProcessor.getInstance().readFile(new File("src/test/resources/new_line_at_end.txt"));
        assertEquals(3, counter3.countWords());
    }

    @Test
    void countLines() {
        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/empty.txt"));
        assertEquals(0, counter.countLines());

        Counter counter1 = FileProcessor.getInstance().readFile(new File("src/test/resources/empty_new_lines.txt"));
        assertEquals(4, counter1.countLines());


        Counter counter2 = FileProcessor.getInstance().readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertEquals(1, counter2.countLines());

        Counter counter3 = FileProcessor.getInstance().readFile(new File("src/test/resources/new_line_at_end.txt"));
        assertEquals(2, counter3.countLines());
    }

    @Test
    void readFiles() {
    }

    @Test
    void readFile() {
        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertNotNull(counter);
    }
}