package paint;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class FreehandTool extends Tool {
    private List<Point2D> points;

    public FreehandTool(PaintModel model, PaintController controller) {
        super(model, controller);
        points = new ArrayList<>();
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        points.clear();
        points.add(new Point2D(e.getX(), e.getY()));
        super.onMousePressed(e); // Guarda xStart, yStart
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        points.add(new Point2D(e.getX(), e.getY()));
        
        // Desenho temporário (feedback visual)
        gc.setStroke(paintController.selectedColor());
        gc.setLineWidth(2);
        gc.lineTo(e.getX(), e.getY());
        gc.stroke();
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        if (points.size() > 1) {
            Freehand drawing = new Freehand(points, paintController.selectedColor());
            model.addFigure(drawing);
        }
        paintController.redraw();
    }
}