import util.ConnectionManager;
import util.JsonReader;
import util.JsonWriter;
import util.RequestHandler;

public class TestProject {
    public static void main(String[] args){
        RequestHandler.start();
        JsonReader.read();
        JsonWriter.write();

        ConnectionManager.closeConnection();
    }
}

//    javac Main.java
//    java Main arg0 arg1 arg2