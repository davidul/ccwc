# Word Count
Word count [challenge](https://codingchallenges.fyi/challenges/challenge-wc)

The goal is to count the number of words in a text file. A word is defined as a sequence of alphabetic characters, so the word "it's" is considered one word. The text file can contain any characters, including numbers, letters, special characters, spaces, new lines, tabs, etc.

## Building and Running
To build the project, run the following command:
```bash
make build
```
or 
```bash
mvn clean package
```

Result is in the `target` directory.

## Running
To run the project, run the following command:
```bash
java -jar target/ccwc-jar-with-dependencies.jar <path-to-text-file>
```

Command line options:
- `<path-to-text-file>`: Path to the text file to count the number of words.
- `-h`, `--help`: Show help message.
- `-v`, `--version`: Show version.
- `-c`, number of bytes in file
- `-w`, number of words in file
- `-l`, number of lines in file

## Example
```bash
java -jar target/ccwc-jar-with-dependencies.jar src/test/resources/new_line_at_end.txt
```