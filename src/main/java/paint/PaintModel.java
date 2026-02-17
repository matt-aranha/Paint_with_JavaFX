package paint;

import java.util.ArrayList;
import java.util.List;

public class PaintModel {

    private List<Figura> figuras = new ArrayList<>(); 

    public void addFigure(Figura f) {
        figuras.add(f);
    }

    public List<Figura> getFiguras() {
        return figuras;
    }
}
