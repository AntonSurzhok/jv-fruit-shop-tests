package core.basesyntax.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private static FileReader fileReader;

    @BeforeAll
    static void beforeAll() {
        fileReader = new FileReaderImpl();
    }

    @Test
    void read_validFile_Ok() {
        List<String> result = fileReader.read(
                "src/test/resources/input.csv");

        assertEquals(3, result.size());
        assertEquals("type,fruit,quantity", result.get(0));
        assertEquals("b,banana,100", result.get(1));
        assertEquals("s,banana,20", result.get(2));
    }

    @Test
    void read_emptyFile_Ok() {
        List<String> result = fileReader.read(
                "src/test/resources/empty.csv");

        assertEquals(0, result.size());
    }

    @Test
    void read_invalidPath_NotOk() {
        assertThrows(RuntimeException.class,
                () -> fileReader.read("src/test/resources/notExists.csv"));
    }
}
