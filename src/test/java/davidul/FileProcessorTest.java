package davidul;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileProcessorTest {

    @BeforeAll
    static void before() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("src/test/resources/very_large.txt", "rw");
            //ByteBuffer.allocateDirect(1024 * 1024 * 1024);
            MappedByteBuffer out = file.getChannel()
                    .map(FileChannel.MapMode.READ_WRITE, 0,  1024 * 1024 * 1024); //1GB
            //1048576

            for (int k = 0; k < 1024*1024; k++) {
                for (int i = 0; i < 341; i++) {
                    out.put((byte) 'a');
                    out.put((byte) ' ');
                    out.put((byte) 10);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void after() {
        File file = new File("src/test/resources/very_large.txt");
        file.delete();
    }

    @Test
    void countBytes() {
        FileProcessor fileProcessor = new FileProcessor();

        fileProcessor.readFile(new File("src/test/resources/empty.txt"));
        assertEquals(0, fileProcessor.countBytes());

        fileProcessor.readFile(new File("src/test/resources/empty_new_lines.txt"));
        assertEquals(4, fileProcessor.countBytes());

        fileProcessor.readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertEquals(9, fileProcessor.countBytes());

        fileProcessor.readFile(new File("src/test/resources/new_line_at_end.txt"));
        assertEquals(18, fileProcessor.countBytes());

        fileProcessor.readFile(new File("src/test/resources/very_large.txt"));
        assertEquals(1073741824, fileProcessor.countBytes());
    }

    @Test
    void countWords() {
        FileProcessor fileProcessor = new FileProcessor();

        fileProcessor.readFile(new File("src/test/resources/empty.txt"));
        assertEquals(0, fileProcessor.countWords());

        fileProcessor.readFile(new File("src/test/resources/empty_new_lines.txt"));
        assertEquals(0, fileProcessor.countWords());

        fileProcessor.readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertEquals(2, fileProcessor.countWords());

        fileProcessor.readFile(new File("src/test/resources/new_line_at_end.txt"));
        assertEquals(3, fileProcessor.countWords());

        //357564417
        fileProcessor.readFile(new File("src/test/resources/very_large.txt"));
        assertEquals(357564416, fileProcessor.countWords());
    }

    @Test
    void countLines() {
        FileProcessor fileProcessor = new FileProcessor();

        fileProcessor.readFile(new File("src/test/resources/empty.txt"));
        assertEquals(0, fileProcessor.countLines());

        fileProcessor.readFile(new File("src/test/resources/empty_new_lines.txt"));
        assertEquals(4, fileProcessor.countLines());


        fileProcessor.readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertEquals(1, fileProcessor.countLines());

        fileProcessor.readFile(new File("src/test/resources/new_line_at_end.txt"));
        assertEquals(2, fileProcessor.countLines());

        fileProcessor.readFile(new File("src/test/resources/very_large.txt"));
        assertEquals(357564416, fileProcessor.countLines());
    }

    @Test
    void readFiles() {
    }

    @Test
    void readFile() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(new File("src/test/resources/no_new_line_at_end.txt"));
        assertNotNull(fileProcessor.byteBuffer);
    }
}