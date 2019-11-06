import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLController {

    @FXML
    private TextField line;

    @FXML
    private TextArea text;

    @FXML
    private ListView<?> list;

    @FXML
    private Button send;

    public void addLine(String text){
        this.text.appendText(text);
    }
    @FXML
    public void onBtClick(javafx.event.ActionEvent actionEvent) {
        String text = line.getText();
        line.setText("");
        this.text.appendText(text+"\n");
    }
}
