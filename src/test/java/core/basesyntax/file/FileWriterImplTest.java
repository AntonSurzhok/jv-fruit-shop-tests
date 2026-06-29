package core.basesyntax.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private final core.basesyntax.file.FileWriter fileWriter =
            new FileWriterImpl();

    @Test
    void write_validData_Ok() throws IOException {
        File file = File.createTempFile("fruit-shop", ".csv");
        file.deleteOnExit();

        String data = "fruit,quantity\nbanana,100";

        fileWriter.write(data, file.getAbsolutePath());

        String result = Files.readString(file.toPath());

        Assertions.assertEquals(data, result);
    }

    @Test
    void write_nullData_NotOk() throws IOException {
        File file = File.createTempFile("fruit-shop", ".csv");
        file.deleteOnExit();

        Assertions.assertThrows(
                RuntimeException.class,
                () -> fileWriter.write(
                        null,
                        file.getAbsolutePath()
                )
        );
    }

    @Test
    void write_nullPath_NotOk() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> fileWriter.write(
                        "test",
                        null
                )
        );
    }
}