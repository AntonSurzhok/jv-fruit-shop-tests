package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private ReturnOperation returnOperation;

    @BeforeEach
    void setUp() {
        Storage.clear();
        returnOperation = new ReturnOperation();
    }

    @Test
    void handle_existingFruit_Ok() {
        Storage.putFruit("pear", 20);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN,
                "pear",
                10
        );

        returnOperation.handle(transaction);

        Assertions.assertEquals(
                30,
                Storage.getFruitQuantity("pear")
        );
    }

    @Test
    void handle_newFruit_Ok() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN,
                "kiwi",
                15
        );

        returnOperation.handle(transaction);

        Assertions.assertEquals(
                15,
                Storage.getFruitQuantity("kiwi")
        );
    }
}
