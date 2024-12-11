import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {

    public void writeToFile(String outputFileName, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(outputFileName);
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close(); // Close the file to ensure data is saved
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }

}
