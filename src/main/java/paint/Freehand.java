package paint;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Freehand extends Figura {
    private List<Point2D> points;

    public Freehand(List<Point2D> points, Color color) {
        super(color);
        this.points = new ArrayList<>(points);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(2);
        gc.beginPath();
        if (!points.isEmpty()) {
            gc.moveTo(points.get(0).getX(), points.get(0).getY());
            for (int i = 1; i < points.size(); i++) {
                gc.lineTo(points.get(i).getX(), points.get(i).getY());
            }
        }
        gc.stroke();
    }

    @Override
    public boolean contains(double x, double y) {
        for (Point2D p : points) {
            if (p.distance(x, y) < 5.0) return true;
        }
        return false;
    }

    @Override
    public void move(double dx, double dy) {
        for (int i = 0; i < points.size(); i++) {
            points.set(i, points.get(i).add(dx, dy));
        }
    }

    @Override
    protected double[] getBounds() {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (Point2D p : points) {
            if (p.getX() < minX) minX = p.getX();
            if (p.getY() < minY) minY = p.getY();
            if (p.getX() > maxX) maxX = p.getX();
            if (p.getY() > maxY) maxY = p.getY();
        }
        return new double[] { minX, minY, maxX - minX, maxY - minY };
    }
}