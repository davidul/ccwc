package davidul;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {

    ByteBuffer byteBuffer;

    public void countBytes(){
        System.out.println("Bytes " + byteBuffer.capacity());
    }

    public void countWords(){
        int count = 0;
        for(int i = 0; i < byteBuffer.capacity(); i++){
            if(byteBuffer.get(i) == 32){
                count++;
            }
        }
        System.out.println("Words " + count);
    }

    public void countLines(){
        int count = 0;
        for(int i = 0; i < byteBuffer.capacity(); i++){
            if(byteBuffer.get(i) == 10){
                count++;
            }
        }
        System.out.println("Lines " + count);
    }

    public void readFiles(){

    }

    public void readFile(File file){
        Path path = Paths.get(file.getAbsolutePath());
        try {
            byte[] fileBytes = Files.readAllBytes(path);
            byteBuffer = ByteBuffer.wrap(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
