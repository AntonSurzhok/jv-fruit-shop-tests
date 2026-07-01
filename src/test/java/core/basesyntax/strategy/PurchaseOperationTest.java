package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    private PurchaseOperation purchaseOperation;

    @BeforeEach
    void setUp() {
        purchaseOperation = new PurchaseOperation();
    }

    @AfterEach
    void tearDown() {
        Storage.clear();
    }

    @Test
    void handle_purchase_Ok() {
        Storage.putFruit("banana", 100);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "banana",
                20
        );

        purchaseOperation.handle(transaction);

        assertEquals(80, Storage.getFruitQuantity("banana"));
    }

    @Test
    void handle_purchaseAll_Ok() {
        Storage.putFruit("banana", 100);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "banana",
                100
        );

        purchaseOperation.handle(transaction);

        assertEquals(0, Storage.getFruitQuantity("banana"));
    }

    @Test
    void handle_notEnoughFruit_NotOk() {
        Storage.putFruit("banana", 50);

        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "banana",
                70
        );

        assertThrows(
                RuntimeException.class,
                () -> purchaseOperation.handle(transaction)
        );
    }

    @Test
    void handle_fruitAbsent_NotOk() {
        FruitTransaction transaction = new FruitTransaction(
                FruitTransaction.Operation.PURCHASE,
                "banana",
                10
        );

        assertThrows(
                RuntimeException.class,
                () -> purchaseOperation.handle(transaction)
        );
    }
}
