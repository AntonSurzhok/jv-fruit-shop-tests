package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private ReturnOperation returnOperation;

    @BeforeEach
    void setUp() {
        returnOperation = new ReturnOperation();
    }

    @AfterEach
    void tearDown() {
        Storage.clear();
    }

    @Test
    void handle_returnExistingFruit_Ok() {
        Storage.putFruit("banana", 100);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN,
                "banana",
                20
        );

        returnOperation.handle(transaction);

        assertEquals(120, Storage.getFruitQuantity("banana"));
    }

    @Test
    void handle_returnNewFruit_Ok() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN,
                "apple",
                15
        );

        returnOperation.handle(transaction);

        assertEquals(15, Storage.getFruitQuantity("apple"));
    }
}
