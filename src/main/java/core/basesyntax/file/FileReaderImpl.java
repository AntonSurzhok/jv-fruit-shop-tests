package core.basesyntax.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderImpl implements FileReader {

    @Override
    public List<String> read(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new RuntimeException("File name can't be null or empty");
        }

        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
    }
}
