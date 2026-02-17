package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figura {
    private Point2D start, end;
    private Color color;

    public Line(Point2D start, Point2D end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    
    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }
    public Color getColor() {
        return color;
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
    
}
