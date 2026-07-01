package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    private static final String SEPARATOR = ",";

    @Override
    public List<FruitTransaction> convertToTransaction(List<String> data) {
        if (data == null) {
            throw new RuntimeException("Input data can't be null");
        }

        if (data.isEmpty()) {
            throw new RuntimeException("Input data is empty");
        }

        List<FruitTransaction> result = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            String line = data.get(i);

            if (line == null || line.isBlank()) {
                throw new RuntimeException("Incorrect csv line");
            }

            String[] parts = line.split(SEPARATOR);

            if (parts.length != 3) {
                throw new RuntimeException("Incorrect csv line: " + line);
            }

            String fruit = parts[1].trim();

            if (fruit.isEmpty()) {
                throw new RuntimeException("Fruit name can't be empty");
            }

            int quantity;

            try {
                quantity = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException(
                        "Incorrect quantity: " + parts[2], e
                );
            }

            if (quantity < 0) {
                throw new RuntimeException("Quantity can't be negative");
            }

            result.add(new FruitTransaction(
                    FruitTransaction.Operation.fromCode(parts[0].trim()),
                    fruit,
                    quantity
            ));
        }

        return result;
    }
}
