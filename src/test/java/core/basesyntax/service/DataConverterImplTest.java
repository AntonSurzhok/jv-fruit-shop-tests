package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private static DataConverter dataConverter;

    @BeforeAll
    static void beforeAll() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_validData_Ok() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana,100",
                "s,banana,20"
        );

        List<FruitTransaction> result =
                dataConverter.convertToTransaction(input);

        assertEquals(2, result.size());
        assertEquals(
                FruitTransaction.Operation.BALANCE,
                result.get(0).getOperation()
        );
        assertEquals("banana", result.get(0).getFruit());
        assertEquals(100, result.get(0).getQuantity());
    }

    @Test
    void convertToTransaction_nullInput_NotOk() {
        assertThrows(
                RuntimeException.class,
                () -> dataConverter.convertToTransaction(null)
        );
    }

    @Test
    void convertToTransaction_invalidLine_NotOk() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "invalid,line"
        );

        assertThrows(
                RuntimeException.class,
                () -> dataConverter.convertToTransaction(input)
        );
    }

    @Test
    void convertToTransaction_negativeQuantity_NotOk() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana,-10"
        );

        assertThrows(
                RuntimeException.class,
                () -> dataConverter.convertToTransaction(input)
        );
    }

    @Test
    void convertToTransaction_unknownOperation_NotOk() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "x,banana,10"
        );

        assertThrows(
                RuntimeException.class,
                () -> dataConverter.convertToTransaction(input)
        );
    }
}
