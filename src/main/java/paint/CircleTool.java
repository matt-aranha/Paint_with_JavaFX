package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class CircleTool extends Tool {
    public CircleTool(PaintModel model, PaintController controller) {
        super(model, controller);
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        paintController.redraw();
        double radius = new Point2D(xStart, yStart).distance(e.getX(), e.getY());
        
        gc.setStroke(paintController.selectedColor());
        gc.strokeOval(xStart - radius, yStart - radius, radius * 2, radius * 2);
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        double radius = new Point2D(xStart, yStart).distance(e.getX(), e.getY());
        Circle c = new Circle(new Point2D(xStart, yStart), radius, paintController.selectedColor());
        model.addFigure(c);
        paintController.redraw();
    }
}