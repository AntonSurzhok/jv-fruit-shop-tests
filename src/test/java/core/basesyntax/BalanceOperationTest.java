package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    private BalanceOperation balanceOperation;

    @BeforeEach
    void setUp() {
        Storage.clear();
        balanceOperation = new BalanceOperation();
    }

    @Test
    void handle_newFruit_Ok() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.BALANCE,
                "banana",
                100
        );

        balanceOperation.handle(transaction);

        Assertions.assertEquals(
                100,
                Storage.getFruitQuantity("banana")
        );
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

        Assertions.assertEquals(
                120,
                Storage.getFruitQuantity("banana")
        );
    }
}