package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OperationStrategyImplTest {
    private static OperationStrategy operationStrategy;

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

        operationStrategy = new OperationStrategyImpl(handlers);
    }

    @Test
    void get_balanceHandler_Ok() {
        assertInstanceOf(
                BalanceOperation.class,
                operationStrategy.get(FruitTransaction.Operation.BALANCE)
        );
    }

    @Test
    void get_supplyHandler_Ok() {
        assertInstanceOf(
                SupplyOperation.class,
                operationStrategy.get(FruitTransaction.Operation.SUPPLY)
        );
    }

    @Test
    void get_purchaseHandler_Ok() {
        assertInstanceOf(
                PurchaseOperation.class,
                operationStrategy.get(FruitTransaction.Operation.PURCHASE)
        );
    }

    @Test
    void get_returnHandler_Ok() {
        assertInstanceOf(
                ReturnOperation.class,
                operationStrategy.get(FruitTransaction.Operation.RETURN)
        );
    }

    @Test
    void get_nullOperation_NotOk() {
        assertThrows(
                RuntimeException.class,
                () -> operationStrategy.get(null)
        );
    }
}
