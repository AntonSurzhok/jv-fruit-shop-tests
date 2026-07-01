package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    private SupplyOperation supplyOperation;

    @BeforeEach
    void setUp() {
        supplyOperation = new SupplyOperation();
    }

    @AfterEach
    void tearDown() {
        Storage.clear();
    }

    @Test
    void handle_newFruit_Ok() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY,
                "banana",
                50
        );

        supplyOperation.handle(transaction);

        assertEquals(50, Storage.getFruitQuantity("banana"));
    }

    @Test
    void handle_existingFruit_Ok() {
        Storage.putFruit("banana", 100);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY,
                "banana",
                20
        );

        supplyOperation.handle(transaction);

        assertEquals(120, Storage.getFruitQuantity("banana"));
    }
}
