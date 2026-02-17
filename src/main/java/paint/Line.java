package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figura {
    private Point2D start, end;

    public Line(Point2D start, Point2D end, Color color) {
        super(color);
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(2);
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public boolean contains(double x, double y) {
        // Distância de ponto a segmento de reta para facilitar a seleção
        double distance = linePointDist(start, end, new Point2D(x, y), true);
        return distance < 5.0; // Tolerância de 5 pixels
    }

    @Override
    public void move(double dx, double dy) {
        start = start.add(dx, dy);
        end = end.add(dx, dy);
    }

    @Override
    protected double[] getBounds() {
        double minX = Math.min(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double w = Math.abs(start.getX() - end.getX());
        double h = Math.abs(start.getY() - end.getY());
        return new double[] { minX, minY, w, h };
    }

    // Função auxiliar matemática para distância
    private double linePointDist(Point2D v, Point2D w, Point2D p, boolean isSegment) {
        double l2 = v.distance(w) * v.distance(w);
        if (l2 == 0) return p.distance(v);
        double t = ((p.getX() - v.getX()) * (w.getX() - v.getX()) + (p.getY() - v.getY()) * (w.getY() - v.getY())) / l2;
        if (isSegment) {
            t = Math.max(0, Math.min(1, t));
        }
        Point2D projection = new Point2D(v.getX() + t * (w.getX() - v.getX()), v.getY() + t * (w.getY() - v.getY()));
        return p.distance(projection);
    }
}