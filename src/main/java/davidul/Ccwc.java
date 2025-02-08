package davidul;

import picocli.CommandLine;

// -c number of bytes
// -w number of words
// -l number of lines
// -m number of characters
public class Ccwc {
    public static void main(String[] args) {
        new CommandLine(new Command()).execute(args);
    }
}
