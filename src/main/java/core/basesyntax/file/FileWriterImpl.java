package core.basesyntax.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterImpl implements FileWriter {

    @Override
    public void write(String data, String fileName) {
        if (data == null) {
            throw new RuntimeException("Data can't be null");
        }

        if (fileName == null || fileName.isBlank()) {
            throw new RuntimeException("File name can't be null or empty");
        }

        try {
            Files.writeString(Path.of(fileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + fileName, e);
        }
    }
}
