
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Stroke;

abstract class Shape {

    Point p1, p2;
    int left, top, width, height;
    Color color;
    Stroke stroke;
    boolean isFill;

    void reshape(Point p1, Point p2, Color color, Stroke stroke) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
        this.stroke = stroke;
    }

    void reshape(int left, int top, int width, int height, Color color, Stroke stroke, boolean isFill) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.color = color;
        this.stroke = stroke;
        this.isFill = isFill;
    }

    void moveBy(int dx, int dy) {
        left += dx;
        top += dy;
    }

    boolean containsPoint(int x, int y) {
        if (x >= left && x < left + width && y >= top && y < top + height) {
            return true;
        } else {
            return false;
        }
    }

    abstract void draw(Graphics g);
}

class LineShape extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}

class RectShape extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (isFill) {
            g.fillRect(left, top, width, height);
        } else {
            g.drawRect(left, top, width, height);
        }
    }
}

class OvalShape extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (isFill) {
            g.fillOval(left, top, width, height);
        } else {
            g.drawOval(left, top, width, height);
        }
    }

    @Override
    boolean containsPoint(int x, int y) {
        double rx = width / 2.0;   // horizontal radius of ellipse
        double ry = height / 2.0;  // vertical radius of ellipse 
        double cx = left + rx;   // x-coord of center of ellipse
        double cy = top + ry;    // y-coord of center of ellipse
        if ((ry * (x - cx)) * (ry * (x - cx)) + (rx * (y - cy)) * (rx * (y - cy)) <= rx * rx * ry * ry) {
            return true;
        } else {
            return false;
        }
    }
}

class RoundRectShape extends Shape {
    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (isFill) {
            g.fillRoundRect(left, top, width, height, width / 3, height / 3);
        } else {
            g.drawRoundRect(left, top, width, height, width / 3, height / 3);
        }
    }
}
