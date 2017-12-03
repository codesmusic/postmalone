package tq.s.uml;

import java.awt.*;

/**
 * Create a pair of points that describe relationship
 * @author Le trung Thong
 */
public class Pair{
    Point child; // Child point extends parent
    Point parent; // Parent point

    /**
     * Constructor creates point
     * @param start Start point from child diagram
     * @param end End point from parent diagram
     */
    public Pair(Point start, Point end) {
        child = start;
        parent = end;
    }

    /**
     * Child point getter
     * @return Child point
     */
    public Point getChild() {
        return child;
    }

    /**
     * Child setter
     * @param Child point
     */
    public void setChild(Point child) {
        this.child = child;
    }

    /**
     * Parent point getter
     * @return Parent point
     */
    public Point getParent() {
        return parent;
    }

    /**
     * Parent setter
     * @param Parent child
     */
    public void setParent(Point parent) {
        this.parent = parent;
    }

}
