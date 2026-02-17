package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figura {
    Point2D start;
    double width, height;
    Color color;

    public Rectangle(Point2D start, double width, double height, Color color) {
        this.start = start;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(start.getX(), start.getY(), width, height);
    }
}
