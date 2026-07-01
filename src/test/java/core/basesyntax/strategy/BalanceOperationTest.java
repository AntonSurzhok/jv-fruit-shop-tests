package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    private BalanceOperation balanceOperation;

    @BeforeEach
    void setUp() {
        balanceOperation = new BalanceOperation();
    }

    @AfterEach
    void tearDown() {
        Storage.clear();
    }

    @Test
    void handle_newFruit_Ok() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE,
                "banana",
                100
        );

        balanceOperation.handle(transaction);

        assertEquals(100, Storage.getFruitQuantity("banana"));
    }

    @Test
    void handle_replaceBalance_Ok() {
        Storage.putFruit("banana", 50);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE,
                "banana",
                120
        );

        balanceOperation.handle(transaction);

        assertEquals(120, Storage.getFruitQuantity("banana"));
    }
}
