package davidul;

import org.slf4j.Logger;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

import static picocli.CommandLine.*;
import static picocli.CommandLine.Model.*;

// -c number of bytes
// -w number of words
// -l number of lines
// -m number of characters
@CommandLine.Command(name = "ccwc", mixinStandardHelpOptions = true)
public class Command implements Runnable {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Command.class);

    @Spec
    CommandSpec spec;

    @Parameters(arity = "1..*", paramLabel = "FILE", description = "File to process.")
    File[] files;

    @Option(names = "-c", description = "number of bytes")
    private boolean bytes;

    @Option(names = "-w", description = "number of words")
    private boolean words;

    @Option(names = "-l", description = "number of lines")
    private boolean lines;

    @Option(names = "-m", description = "number of characters")
    private boolean characters;


    @Override
    public void run() {
        ParseResult parseResult = spec.commandLine().getParseResult();
        List<PositionalParamSpec> positionalParamSpecs = parseResult.matchedPositionals();
        File[] files = positionalParamSpecs.getFirst().getValue();
        StringBuffer output = new StringBuffer();
        List<Counter> counters = FileProcessor.getInstance().readFiles(files);
        for (Counter counter : counters) {

            output.append("File: ")
                    .append(files[0].getAbsolutePath())
                    .append("\n");


            if (!bytes && !lines && !words) {
                bytes = true;
                lines = true;
                words = true;
            }

            if (bytes) {
                int countBytes = counter.countBytes();
                output.append("Bytes: ")
                        .append(countBytes)
                        .append("\n");
            }

            if (lines) {
                int countLines = counter.countLines();
                output.append("Lines: ")
                        .append(countLines)
                        .append("\n");
            }

            if (words) {
                int countWords = counter.countWords();
                output.append("Words: ")
                        .append(countWords)
                        .append("\n");
            }

            System.out.println(output);
        }
    }
}
