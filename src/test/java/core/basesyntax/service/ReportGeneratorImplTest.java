package core.basesyntax.service;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private ReportGenerator reportGenerator;

    @BeforeEach
    void setUp() {
        Storage.clear();
        reportGenerator = new ReportGeneratorImpl();
    }

    @Test
    void getReport_emptyStorage_Ok() {
        String expected = "fruit,quantity"
                + System.lineSeparator();

        Assertions.assertEquals(
                expected,
                reportGenerator.getReport()
        );
    }

    @Test
    void getReport_filledStorage_Ok() {
        Storage.putFruit("banana", 100);
        Storage.putFruit("apple", 50);

        String report = reportGenerator.getReport();

        Assertions.assertTrue(
                report.startsWith("fruit,quantity")
        );

        Assertions.assertTrue(
                report.contains("banana,100")
        );

        Assertions.assertTrue(
                report.contains("apple,50")
        );
    }
}