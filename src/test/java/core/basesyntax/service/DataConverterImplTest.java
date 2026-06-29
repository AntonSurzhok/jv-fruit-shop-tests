package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private DataConverter dataConverter;

    @BeforeEach
    void setUp() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_validData_Ok() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana,20",
                "s,banana,30",
                "p,banana,10",
                "r,banana,5"
        );

        List<FruitTransaction> transactions =
                dataConverter.convertToTransaction(input);

        Assertions.assertEquals(4, transactions.size());

        Assertions.assertEquals(
                FruitTransaction.Operation.BALANCE,
                transactions.get(0).getOperation());

        Assertions.assertEquals(
                "banana",
                transactions.get(0).getFruit());

        Assertions.assertEquals(
                20,
                transactions.get(0).getQuantity());
    }

    @Test
    void convertToTransaction_nullData_NotOk() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> dataConverter.convertToTransaction(null)
        );
    }

    @Test
    void convertToTransaction_invalidCsv_NotOk() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana"
        );

        Assertions.assertThrows(
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

        Assertions.assertThrows(
                RuntimeException.class,
                () -> dataConverter.convertToTransaction(input)
        );
    }
}
