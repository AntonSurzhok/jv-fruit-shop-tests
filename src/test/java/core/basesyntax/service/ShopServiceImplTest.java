package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.strategy.ReturnOperation;
import core.basesyntax.strategy.SupplyOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ShopServiceImplTest {
    private static ShopService shopService;

    @BeforeAll
    static void beforeAll() {
        Map<FruitTransaction.Operation, OperationHandler> handlers =
                new HashMap<>();

        handlers.put(
                FruitTransaction.Operation.BALANCE,
                new BalanceOperation()
        );
        handlers.put(
                FruitTransaction.Operation.SUPPLY,
                new SupplyOperation()
        );
        handlers.put(
                FruitTransaction.Operation.PURCHASE,
                new PurchaseOperation()
        );
        handlers.put(
                FruitTransaction.Operation.RETURN,
                new ReturnOperation()
        );

        OperationStrategy strategy =
                new OperationStrategyImpl(handlers);

        shopService = new ShopServiceImpl(strategy);
    }

    @AfterEach
    void tearDown() {
        Storage.clear();
    }

    @Test
    void process_validTransactions_Ok() {
        List<FruitTransaction> transactions = List.of(
                new FruitTransaction(
                        FruitTransaction.Operation.BALANCE,
                        "banana",
                        100
                ),
                new FruitTransaction(
                        FruitTransaction.Operation.PURCHASE,
                        "banana",
                        20
                )
        );

        shopService.process(transactions);

        assertEquals(
                80,
                Storage.getFruitQuantity("banana")
        );
    }

    @Test
    void process_nullTransactions_NotOk() {
        assertThrows(
                RuntimeException.class,
                () -> shopService.process(null)
        );
    }

    @Test
    void process_nullTransaction_NotOk() {
        List<FruitTransaction> transactions = new ArrayList<>();
        transactions.add(null);

        assertThrows(
                RuntimeException.class,
                () -> shopService.process(transactions)
        );
    }
}
