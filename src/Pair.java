package tq.s.uml;

import java.awt.*;

public class Pair{
    Point child;
    Point parent;

    public Pair(Point start, Point end) {
        child = start;
        parent = end;
    }

    public Point getChild() {
        return child;
    }

    public void setChild(Point child) {
        this.child = child;
    }

    public Point getParent() {
        return parent;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

}
