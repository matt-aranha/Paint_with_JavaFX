package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PaintController implements Initializable {

    private PaintModel model = new PaintModel();
    private GraphicsContext gc;

    @FXML private Canvas canvas;
    @FXML public ColorPicker colorPicker; // public para acesso na SelectionTool
    @FXML private ChoiceBox<String> modeChoice;

    private Tool[] tools;
    private int currentToolIndex = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        
        // Inicializa todas as ferramentas na ordem do ChoiceBox
        // Ordem esperada no XML: Linha, Retângulo, Círculo, Mão Livre, Seleção
        tools = new Tool[] {
            new LineTool(model, this),
            new RectangleTool(model, this),
            new CircleTool(model, this),
            new FreehandTool(model, this),
            new SelectionTool(model, this)
        };
        
        colorPicker.setValue(Color.BLACK);
        
        // Listener para mudar cor da figura selecionada se estiver no modo Seleção
        colorPicker.setOnAction(e -> {
            if (tools[currentToolIndex] instanceof SelectionTool) {
                ((SelectionTool) tools[currentToolIndex]).updateSelectedColor(colorPicker.getValue());
            }
        });

        // Configura itens do ChoiceBox via código para garantir sincronia (opcional, mas seguro)
        modeChoice.getItems().clear();
        modeChoice.getItems().addAll("Linha", "Retângulo", "Círculo", "Mão Livre", "Seleção");
        modeChoice.setValue("Linha");
        
        // Importante: O Canvas precisa de foco para receber eventos de teclado
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this::onKeyPressed);
        // Garante o foco ao clicar
        canvas.setOnMouseClicked(e -> canvas.requestFocus());
    }

    @FXML
    public void onMousePressed(MouseEvent e) {
        canvas.requestFocus(); // Garante foco para o teclado
        currentToolIndex = modeChoice.getSelectionModel().getSelectedIndex();
        if (currentToolIndex >= 0 && currentToolIndex < tools.length) {
            tools[currentToolIndex].onMousePressed(e);
        }
    }

    @FXML
    public void onMouseReleased(MouseEvent e) {
        if (currentToolIndex >= 0 && currentToolIndex < tools.length)
            tools[currentToolIndex].onMouseReleased(e);
    }

    @FXML
    public void onMouseDragged(MouseEvent e) {
        if (currentToolIndex >= 0 && currentToolIndex < tools.length)
            tools[currentToolIndex].onMouseDragged(e);
    }
    
    // Novo método para tratar teclado
    public void onKeyPressed(KeyEvent e) {
        if (tools[currentToolIndex] instanceof SelectionTool) {
            ((SelectionTool) tools[currentToolIndex]).onKeyPressed(e);
        }
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