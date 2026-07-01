package core.basesyntax.service;

import core.basesyntax.db.Storage;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {

    private static final String HEADER = "fruit,quantity";

    @Override
    public String getReport() {
        StringBuilder builder = new StringBuilder();

        builder.append(HEADER)
                .append(System.lineSeparator());

        for (Map.Entry<String, Integer> entry : Storage.getFruits().entrySet()) {
            builder.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }

        return builder.toString();
    }
}
