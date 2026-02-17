package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figura {
    protected Color color;

    public Figura(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(GraphicsContext gc);
    
    // Verifica se o ponto (x, y) está dentro ou sobre a figura
    public abstract boolean contains(double x, double y);
    
    // Move a figura pelo delta (dx, dy)
    public abstract void move(double dx, double dy);

    // Desenha os 4 quadrados de seleção
    public void drawSelection(GraphicsContext gc) {
        double[] bounds = getBounds();                                                   // deve retornar minX, minY, width, height
        double x = bounds[0];
        double y = bounds[1];
        double w = bounds[2];
        double h = bounds[3];
        
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        gc.strokeRect(x, y, w, h);

        double size = 6;
        gc.setFill(Color.BLACK);
        // Top-Left
        gc.fillRect(x - size/2, y - size/2, size, size);
        // Top-Right
        gc.fillRect(x + w - size/2, y - size/2, size, size);
        // Bottom-Left
        gc.fillRect(x - size/2, y + h - size/2, size, size);
        // Bottom-Right
        gc.fillRect(x + w - size/2, y + h - size/2, size, size);
    }

    // Método auxiliar para calcular a bounding box: [minX, minY, width, height]
    protected abstract double[] getBounds();
}