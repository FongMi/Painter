
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

public class DrawObjects {
    Shape s;
    Color color;
    Stroke stroke;
    int start, end;
    
    DrawObjects(Shape s, Color color, Stroke stroke) {
        this.s = s;
        this.color = color;
        this.stroke = stroke;
    }
    
    DrawObjects(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
