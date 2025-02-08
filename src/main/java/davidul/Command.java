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
    FileProcessor fileProcessor = new FileProcessor();

    @Spec CommandSpec spec;

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
       fileProcessor.readFile(files[0]);

        if(parseResult.hasMatchedOption("-c")){
            fileProcessor.countBytes();
        }
        if(parseResult.hasMatchedOption("-l")){
            System.out.println("Lines");
            fileProcessor.countLines();
        }
        if(parseResult.hasMatchedOption("-w")){
            System.out.println("Words");
            fileProcessor.countWords();
        }

        spec.commandLine().usage(System.out);
        System.out.println("Hello World");
    }
}
