
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

public class DrawObjects {
    Shape s;
    Color color;
    Stroke stroke;
    int start, end;
    boolean isFill;
    
    DrawObjects(Shape s, Color color, Stroke stroke, boolean isFill) {
        this.s = s;
        this.color = color;
        this.stroke = stroke;
        this.isFill = isFill;
    }
    
    DrawObjects(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
