package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PaintController implements Initializable {

    private PaintModel model = new PaintModel();

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;
    
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ChoiceBox <String> modeChoice;

    private Tool[] tools;
    private int currentToolIndex = 0; // Lines
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        tools = new Tool[] {new LineTool(model, this), new RectangleTool(model, this)};
        colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        modeChoice.setValue("Linha");
    }

    @FXML
    public void onMousePressed(MouseEvent e) {
        currentToolIndex = modeChoice.getSelectionModel().getSelectedIndex();
        tools[currentToolIndex].onMousePressed(e);
    }

    @FXML
    public void onMouseReleased(MouseEvent e) {
        tools[currentToolIndex].onMouseReleased(e);
    }

    @FXML
    public void onMouseDragged(MouseEvent e) {
        tools[currentToolIndex].onMouseDragged(e);      
    }

    public void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawFigures() {
        for (Figura f : model.getFiguras())
            f.draw(gc);
    }

    public void redraw() {
        clearCanvas();
        drawFigures();
    }

    public Color selectedColor() {
        return colorPicker.getValue();
    }
    
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
