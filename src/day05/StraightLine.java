package day05;

import puzzleutils.Point;

import java.util.ArrayList;
import java.util.List;

public record StraightLine(Point start, Point end) {

    public List<Point> getAllPoints() {
        List<Point> points = new ArrayList<>();
        if (isVertical()) {
            int y1 = Math.min(start.y(), end.y());
            int y2 = Math.max(start.y(), end.y());
            while (y1 < y2 + 1) {
                points.add(new Point(start.x(), y1));
                y1++;
            }
        } else if (isHorizontal()) {
            int x1 = Math.min(start.x(), end.x());
            int x2 = Math.max(start.x(), end.x());
            while (x1 < x2 + 1) {
                points.add(new Point(x1, start.y()));
                x1++;
            }
        } else {
            float signumX = (int) Math.signum(end().x() - start().x());
            float signumY = (int) Math.signum(end().y() - start().y());
            int x = start.x();
            int y = start.y();
            while (x != end.x() && y != end.y()) {
                points.add(new Point(x, y));
                x += signumX;
                y += signumY;
            }
            points.add(end);
        }
        return points;
    }

    public boolean isHorizontal() {
        return start.y() == end.y();
    }

    public boolean isVertical() {
        return start.x() == end.x();
    }
}
