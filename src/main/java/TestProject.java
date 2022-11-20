import util.ConnectionManager;
import util.JsonReader;
import util.JsonWriter;

public class TestProject {
    public static void main(String[] args){
        String line1 = "stat"; String line2 = "input.json"; String line3 = "output.json";
        JsonWriter.setPath(line3);
        ConnectionManager.openConnection();
        JsonReader.read(line1,line2);
        JsonWriter.write(line1);
        ConnectionManager.closeConnection();
    }
}

//    javac Main.java
//    java Main arg0 arg1 arg2