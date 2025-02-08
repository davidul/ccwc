package davidul;

import java.nio.ByteBuffer;

public class Counter {
    private final ByteBuffer byteBuffer;

    public Counter(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public int countBytes() {
        return byteBuffer.capacity();
    }

    public int countWords() {
        int count = 0;
        int state = 0;

        byteBuffer.rewind();
        while(byteBuffer.hasRemaining()){
            byte b = byteBuffer.get();
            if(b == 32 || b == 10){
                state = 0;
            } else if(state == 0){
                state = 1;
                count++;
            }
        }

//        char[] charArray = new String(byteBuffer.array()).toCharArray();
//        for (int i = 0; i < charArray.length; i++) {
//            if (charArray[i] == '\\') {
//                count++;
//                continue;
//            }
//            if (Character.isLetterOrDigit(charArray[i])) {
//                if (state == 0) {
//                    count++;
//                    state = 1;
//                }
//            } else {
//                state = 0;
//            }
//        }
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
}
