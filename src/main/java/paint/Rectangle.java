package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figura {
    Point2D start;
    double width, height;

    public Rectangle(Point2D start, double width, double height, Color color) {
        super(color);
        this.start = start;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(start.getX(), start.getY(), width, height);
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= start.getX() && x <= start.getX() + width &&
               y >= start.getY() && y <= start.getY() + height;
    }

    @Override
    public void move(double dx, double dy) {
        start = new Point2D(start.getX() + dx, start.getY() + dy);
    }

    @Override
    protected double[] getBounds() {
        return new double[] { start.getX(), start.getY(), width, height };
    }
}