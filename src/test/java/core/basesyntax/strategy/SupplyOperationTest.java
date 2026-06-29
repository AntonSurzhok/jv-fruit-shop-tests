package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    private SupplyOperation supplyOperation;

    @BeforeEach
    void setUp() {
        Storage.clear();
        supplyOperation = new SupplyOperation();
    }

    @Test
    void handle_existingFruit_Ok() {
        Storage.putFruit("apple", 30);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY,
                "apple",
                20
        );

        supplyOperation.handle(transaction);

        Assertions.assertEquals(
                50,
                Storage.getFruitQuantity("apple")
        );
    }

    @Test
    void handle_newFruit_Ok() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY,
                "orange",
                40
        );

        supplyOperation.handle(transaction);

        Assertions.assertEquals(
                40,
                Storage.getFruitQuantity("orange")
        );
    }
}