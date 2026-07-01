package core.basesyntax.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private static FileWriter fileWriter;

    @BeforeAll
    static void beforeAll() {
        fileWriter = new FileWriterImpl();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of("target/test-output.csv"));
        Files.deleteIfExists(Path.of("target/empty-output.csv"));
    }

    @Test
    void write_validFile_Ok() throws IOException {
        Path path = Path.of("target/test-output.csv");

        fileWriter.write(
                "fruit,quantity\nbanana,100",
                path.toString()
        );

        String actual = Files.readString(path);

        assertEquals(
                "fruit,quantity\nbanana,100",
                actual
        );
    }

    @Test
    void write_emptyContent_Ok() throws IOException {
        Path path = Path.of("target/empty-output.csv");

        fileWriter.write("", path.toString());

        assertEquals("", Files.readString(path));
    }

    @Test
    void write_invalidPath_NotOk() {
        assertThrows(
                RuntimeException.class,
                () -> fileWriter.write("abc", "")
        );
    }
}
