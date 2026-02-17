package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Figura {
    private Point2D center;
    private double radius;

    public Circle(Point2D center, double radius, Color color) {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean contains(double x, double y) {
        return center.distance(x, y) <= radius;
    }

    @Override
    public void move(double dx, double dy) {
        center = center.add(dx, dy);
    }

    @Override
    protected double[] getBounds() {
        return new double[] { center.getX() - radius, center.getY() - radius, radius * 2, radius * 2 };
    }
}