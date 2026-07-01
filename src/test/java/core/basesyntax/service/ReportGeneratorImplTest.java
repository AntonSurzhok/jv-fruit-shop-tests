package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private static ReportGenerator reportGenerator;

    @BeforeAll
    static void beforeAll() {
        reportGenerator = new ReportGeneratorImpl();
    }

    @AfterEach
    void tearDown() {
        Storage.clear();
    }

    @Test
    void getReport_emptyStorage_Ok() {
        String expected = "fruit,quantity" + System.lineSeparator();

        assertEquals(expected, reportGenerator.getReport());
    }

    @Test
    void getReport_withData_Ok() {
        Storage.putFruit("banana", 100);
        Storage.putFruit("apple", 50);

        String report = reportGenerator.getReport();

        assertTrue(report.startsWith("fruit,quantity"));
        assertTrue(report.contains("banana,100"));
        assertTrue(report.contains("apple,50"));
    }
}
