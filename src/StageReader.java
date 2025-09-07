import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class StageFormatException extends Exception {
    public StageFormatException(String message) {
        super(message);
    }
public class StageReader {
  public static Stage readStage(String path) throws IOException {
    Stage stage = new Stage();
    List<String> lines = Files.readAllLines(Paths.get(path));
    for(String data : lines){
      if(data.isEmpty()){
        throw new StageFormatException("no data seen");
      }
      
    }
    return new Stage();
  }
}