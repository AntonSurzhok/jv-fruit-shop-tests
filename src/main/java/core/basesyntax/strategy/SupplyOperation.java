package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class SupplyOperation implements OperationHandler {

    @Override
    public void handle(FruitTransaction transaction) {
        int current = Storage.getFruitQuantity(transaction.getFruit());

        Storage.putFruit(
                transaction.getFruit(),
                current + transaction.getQuantity()
        );
    }
}
