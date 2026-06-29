package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    private PurchaseOperation purchaseOperation;

    @BeforeEach
    void setUp() {
        Storage.clear();
        purchaseOperation = new PurchaseOperation();
    }

    @Test
    void handle_validPurchase_Ok() {
        Storage.putFruit("banana", 100);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "banana",
                40
        );

        purchaseOperation.handle(transaction);

        Assertions.assertEquals(
                60,
                Storage.getFruitQuantity("banana")
        );
    }

    @Test
    void handle_purchaseAllFruit_Ok() {
        Storage.putFruit("apple", 50);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "apple",
                50
        );

        purchaseOperation.handle(transaction);

        Assertions.assertEquals(
                0,
                Storage.getFruitQuantity("apple")
        );
    }

    @Test
    void handle_notEnoughFruit_NotOk() {
        Storage.putFruit("orange", 10);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "orange",
                15
        );

        Assertions.assertThrows(
                RuntimeException.class,
                () -> purchaseOperation.handle(transaction)
        );
    }

    @Test
    void handle_fruitDoesNotExist_NotOk() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "kiwi",
                5
        );

        Assertions.assertThrows(
                RuntimeException.class,
                () -> purchaseOperation.handle(transaction)
        );
    }
}
