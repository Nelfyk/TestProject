import util.ConnectionManager;
import util.JsonReader;
import util.JsonWriter;

public class TestProject {
    public static void main(String[] args) {
        /*
        args[0] - operation (search/stat)
        args[1] - path to input (input.json)
        args[1] - path to output (output.json)
         */
        JsonWriter.setPath(args[2]);
        ConnectionManager.openConnection();
        JsonReader.read(args[0], args[1]);
        JsonWriter.write(args[0]);
        ConnectionManager.closeConnection();
    }
}
