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

public class LargeFileTest {
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
    void countLines() {
        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/very_large.txt"));
        assertEquals(357564416, counter.countLines());
    }

    @Test
    void countWords() {
        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/very_large.txt"));
        assertEquals(357564417, counter.countWords());
    }

    @Test
    void countBytes() {
        Counter counter = FileProcessor.getInstance().readFile(new File("src/test/resources/very_large.txt"));
        assertEquals(1073741824, counter.countBytes());
    }
}
