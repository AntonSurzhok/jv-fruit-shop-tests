package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationStrategyImplTest {
    private OperationStrategy operationStrategy;

    @BeforeEach
    void setUp() {
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
    void get_balanceOperation_Ok() {
        OperationHandler handler =
                operationStrategy.get(FruitTransaction.Operation.BALANCE);

        Assertions.assertInstanceOf(
                BalanceOperation.class,
                handler
        );
    }

    @Test
    void get_supplyOperation_Ok() {
        OperationHandler handler =
                operationStrategy.get(FruitTransaction.Operation.SUPPLY);

        Assertions.assertInstanceOf(
                SupplyOperation.class,
                handler
        );
    }

    @Test
    void get_purchaseOperation_Ok() {
        OperationHandler handler =
                operationStrategy.get(FruitTransaction.Operation.PURCHASE);

        Assertions.assertInstanceOf(
                PurchaseOperation.class,
                handler
        );
    }

    @Test
    void get_returnOperation_Ok() {
        OperationHandler handler =
                operationStrategy.get(FruitTransaction.Operation.RETURN);

        Assertions.assertInstanceOf(
                ReturnOperation.class,
                handler
        );
    }

    @Test
    void get_unknownOperation_NotOk() {
        Map<FruitTransaction.Operation, OperationHandler> handlers =
                new HashMap<>();

        OperationStrategy strategy =
                new OperationStrategyImpl(handlers);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> strategy.get(FruitTransaction.Operation.BALANCE)
        );
    }
}
