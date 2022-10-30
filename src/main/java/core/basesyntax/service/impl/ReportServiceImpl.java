package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportService;
import java.util.Map;

public class ReportServiceImpl implements ReportService {
    private static final String EMPTY_STORAGE_MESSAGE = "Storage is empty!";
    private static final String HEADING = "fruit,quantity" + System.lineSeparator();
    private static final String SEPARATOR = ",";

    @Override
    public String generate() {
        if (Storage.storageContents.isEmpty()) {
            throw new RuntimeException(EMPTY_STORAGE_MESSAGE);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEADING);
        for (Map.Entry<String, Integer> entry : Storage.storageContents.entrySet()) {
            stringBuilder.append(entry.getKey() + SEPARATOR
                    + entry.getValue() + System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}