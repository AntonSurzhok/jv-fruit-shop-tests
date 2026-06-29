package core.basesyntax.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private final core.basesyntax.file.FileReader fileReader =
            new FileReaderImpl();

    @Test
    void read_validFile_Ok() throws IOException {
        File file = File.createTempFile("fruit-shop", ".csv");
        file.deleteOnExit();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("type,fruit,quantity\n");
            writer.write("b,banana,100\n");
            writer.write("s,banana,50\n");
        }

        List<String> result = fileReader.read(file.getAbsolutePath());

        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(
                "type,fruit,quantity",
                result.get(0)
        );
        Assertions.assertEquals(
                "b,banana,100",
                result.get(1)
        );
        Assertions.assertEquals(
                "s,banana,50",
                result.get(2)
        );
    }

    @Test
    void read_fileDoesNotExist_NotOk() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> fileReader.read("not_existing_file.csv")
        );
    }

    @Test
    void read_nullPath_NotOk() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> fileReader.read(null)
        );
    }
}