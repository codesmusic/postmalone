package tqUML;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.*;
import java.awt.geom.Point2D.*;

/**
 * The class represents base line model and rendering according to multiple params.
 *
 * @author
 */
public class ConnectLine {
    /**
     * Three type of line
     */
    public static final int LINE_TYPE_SIMPLE = 0;
    public static final int LINE_TYPE_RECT_1BREAK = 1;
    public static final int LINE_TYPE_RECT_2BREAK = 2;
    /**
     * Two way to start a line
     */
    public static final int LINE_START_HORIZONTAL = 0;
    public static final int LINE_START_VERTICAL = 1;

    /**
     * Define line has arrow or not
     */
    public static final int LINE_ARROW_NONE = 0;
    public static final int LINE_ARROW_DEST = 1;

    /**
     * Width for each arrow
     */
    public static int LINE_ARROW_WIDTH = 10;

    /**
     * Source line point
     */
    Point p1;
    /**
     * Destination line point
     */
    Point p2;

    /**
     * Line type can be one of LINE_TYPE_SIMPLE, LINE_TYPE_RECT_1BREAK, LINE_TYPE_RECT_2BREAK
     */
    int lineType = LINE_TYPE_SIMPLE;
    /**
     * for the LINE_TYPE_RECT_2BREAK type the param defines how line should be rendered
     */
    int lineStart = LINE_START_HORIZONTAL;
    /**
     * arrow can be one of following
     * LINE_ARROW_NONE - no arrow
     * LINE_ARROW_DEST - arrow beside dest point
     */
    int lineArrow = LINE_ARROW_NONE;

    /**
     * Constructs default line
     *
     * @param p1 Point start
     * @param p2 Point end
     */
    public ConnectLine(Point p1, Point p2) {
        this(p1, p2, LINE_TYPE_SIMPLE, LINE_START_HORIZONTAL, LINE_ARROW_NONE);
    }

    /**
     * Constructs line with specified params
     *
     * @param p1        Point start
     * @param p2        Point end
     * @param lineType  int type of line (LINE_TYPE_SIMPLE, LINE_TYPE_RECT_1BREAK, LINE_TYPE_RECT_2BREAK)
     * @param lineStart int for the LINE_TYPE_RECT_2BREAK type the param defines how line should be rendered
     * @param lineArrow int defines line arrow type
     */
    public ConnectLine(Point p1, Point p2, int lineType, int lineStart, int lineArrow) {
        this.p1 = p1;
        this.p2 = p2;
        this.lineType = lineType;
        this.lineStart = lineStart;
        this.lineArrow = lineArrow;
    }

    /**
     * Paints the line with specified params
     *
     * @param g2d Graphics2D
     */
    public void paint(Graphics2D g2d) {
        switch (lineType) {
            case LINE_TYPE_SIMPLE:
                paintSimple(g2d);
                break;
            case LINE_TYPE_RECT_1BREAK:
                paint1Break(g2d);
                break;
            case LINE_TYPE_RECT_2BREAK:
                paint2Breaks(g2d);
                break;
        }
    }

    /**
     * Paint the simple line
     * @param g2d
     */
    protected void paintSimple(Graphics2D g2d) {

        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        paintArrow(g2d, p1, p2);
    }

    protected void paintArrow(Graphics2D g2d, Point p1, Point p2) {
        paintArrow(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
    }

    protected void paintArrow(Graphics2D g2d, Point p1, Point p2, int width) {
        Point2D.Float pp1 = new Point2D.Float(p1.x, p1.y);
        Point2D.Float pp2 = new Point2D.Float(p2.x, p2.y);

        // Get left and right points of arrow
        Point2D.Float left = getLeftArrowPoint(pp1, pp2, width);
        Point2D.Float right = getRightArrowPoint(pp1, pp2, width);

        // Draw arrow line
        g2d.drawLine(p2.x, p2.y, Math.round(left.x), Math.round(left.y));
        g2d.drawLine(p2.x, p2.y, Math.round(right.x), Math.round(right.y));
    }

    /**
     * Paint the line with 1 break
     * @param g2d
     */
    protected void paint1Break(Graphics2D g2d) {
        if (lineStart == LINE_START_HORIZONTAL) {
            //Paint horizontal line
            g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
            // Paint vertical line
            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
            // Paint arrow
            paintArrow(g2d, new Point(p2.x, p1.y), p2);
        } else if (lineStart == LINE_START_VERTICAL) {
            // Paint vertical line
            g2d.drawLine(p1.x, p1.y, p1.x, p2.y);
            // Paint horizontal line
            g2d.drawLine(p1.x, p2.y, p2.x, p2.y);
            // Paint arrow
            paintArrow(g2d, new Point(p1.x, p2.y), p2);
        }
    }

    /**
     * Paint the line with 2 breaks
     * @param g2d
     */
    protected void paint2Breaks(Graphics2D g2d) {
        if (lineStart == LINE_START_HORIZONTAL) {
            // Paint first horizontal line
            g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
            // Paint vertical line
            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
            // Paint second horizontal line
            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
            // Paint arrow
            paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
        } else if (lineStart == LINE_START_VERTICAL) {
            // Paint first vertical line
            g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
            // Paint horizontal line
            g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
            // Paint second vẻtical line
            g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y);
            // Paint arrow
            paintArrow(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
        }
    }

    /**
     * Set type of line
     * @param type
     */
    public void setLineType(int type) {
        lineType = type;
    }

    /**
     * Get source point
     * @return
     */
    public Point getP1() {
        return p1;
    }

    /**
     * Set source point
     * @param p
     */
    public void setP1(Point p) {
        p1 = p;
    }

    /**
     * Get destination point
     * @return
     */
    public Point getP2() {
        return p2;
    }

    /**
     * Set destination point
     * @param p
     */
    public void setP2(Point p) {
        p2 = p;
    }

    /**
     * Get the left arrow point
     * @param p1
     * @param p2
     * @return Point left arrow point
     */

    protected static Point2D.Float getLeftArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
        Point2D.Float res = new Point2D.Float();
        double alpha = Math.PI / 2;
        if (p2.x != p1.x) {
            alpha = Math.atan((p2.y - p1.y) / (p2.x - p1.x));
        }
        alpha += Math.PI / 10;
        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
        if (p1.x <= p2.x) {
            res.x = p2.x - xShift;
        } else {
            res.x = p2.x + xShift;
        }
        if (p1.y < p2.y) {
            res.y = p2.y - yShift;
        } else {
            res.y = p2.y + yShift;
        }
        return res;
    }

    /**
     * Get the right arrow point
     * @param p1
     * @param p2
     * @return Point right arrow point
     */

    protected static Point2D.Float getRightArrowPoint(Point2D.Float p1, Point2D.Float p2, float w) {
        Point2D.Float res = new Point2D.Float();
        double alpha = Math.PI / 2;
        if (p2.x != p1.x) {
            alpha = Math.atan((p2.y - p1.y) / (p2.x - p1.x));
        }
        alpha -= Math.PI / 10;
        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
        if (p1.x < p2.x) {
            res.x = p2.x - xShift;
        } else {
            res.x = p2.x + xShift;
        }
        if (p1.y <= p2.y) {
            res.y = p2.y - yShift;
        } else {
            res.y = p2.y + yShift;
        }
        return res;
    }

    /**
     * Get the prefer width of an arrow
     * @param p1
     * @param p2
     * @return width of arrow
     */
    protected int getRestrictedArrowWidth(Point p1, Point p2) {
        return Math.min(LINE_ARROW_WIDTH, (int) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));
    }
}

