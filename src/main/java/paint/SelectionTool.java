package paint;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.Collections;
import java.util.List;

public class SelectionTool extends Tool {
    private Figura selectedFigura;
    private double lastX, lastY;
    private boolean isDragging = false;

    public SelectionTool(PaintModel model, PaintController controller) {
        super(model, controller);
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        isDragging = false;
        
        // Procura a figura de trás para frente (topo para o fundo)
        List<Figura> figuras = model.getFiguras();
        selectedFigura = null;
        for (int i = figuras.size() - 1; i >= 0; i--) {
            if (figuras.get(i).contains(e.getX(), e.getY())) {
                selectedFigura = figuras.get(i);
                isDragging = true;
                break;
            }
        }
        paintController.redraw();
        if (selectedFigura != null) {
            selectedFigura.drawSelection(gc);
            // Atualiza o color picker para a cor da seleção
            paintController.colorPicker.setValue(selectedFigura.getColor());
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (selectedFigura != null && isDragging) {
            double dx = e.getX() - lastX;
            double dy = e.getY() - lastY;
            selectedFigura.move(dx, dy);
            lastX = e.getX();
            lastY = e.getY();
            
            paintController.redraw();
            selectedFigura.drawSelection(gc);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        isDragging = false;
    }
    
    // Método extra para receber eventos de teclado
    public void onKeyPressed(KeyEvent e) {
        if (selectedFigura == null) return;

        if (e.getCode() == KeyCode.DELETE) {
            model.getFiguras().remove(selectedFigura);
            selectedFigura = null;
        } 
        else if (e.getCode() == KeyCode.UP) {
            // Bring to Front
            List<Figura> lista = model.getFiguras();
            int idx = lista.indexOf(selectedFigura);
            if (idx < lista.size() - 1) {
                Collections.swap(lista, idx, idx + 1);
            }
        } 
        else if (e.getCode() == KeyCode.DOWN) {
            // Send to Back
            List<Figura> lista = model.getFiguras();
            int idx = lista.indexOf(selectedFigura);
            if (idx > 0) {
                Collections.swap(lista, idx, idx - 1);
            }
        }
        
        paintController.redraw();
        if (selectedFigura != null) {
            selectedFigura.drawSelection(gc);
        }
    }
    
    public void updateSelectedColor(javafx.scene.paint.Color c) {
        if (selectedFigura != null) {
            selectedFigura.setColor(c);
            paintController.redraw();
            selectedFigura.drawSelection(gc);
        }
    }
}