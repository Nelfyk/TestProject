package util;

import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<Object> criteriaList;

    private JsonReader() {
    }

    public static void read(String operation, String path) {
        criteriaList = new ArrayList<>();
        JsonSimpleParser.parse(operation, path);
    }

}
