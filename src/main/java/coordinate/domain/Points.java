package coordinate.domain;

import coordinate.view.Output;

import java.util.*;

public class Points {

    private List<Point> points;

    public Points(String[] coordinates) {
        this.points = initPoints(coordinates);
    }

    private List<Point> initPoints(String[] splitInput) {
        List<Point> points = new ArrayList<>();
        for (String set : splitInput) {
            int[] xySet = Utils.convertToIntegerArray(set);
            addPoints(points, xySet);
        }
        checkSquare(points);
        return points;
    }

    static List<Point> addPoints(List<Point> points, int[] xySet) throws IllegalArgumentException {
        Point newPoint = new Point(xySet);
        if (points.contains(newPoint)) {
            Output.printMessage("중복된 좌표가 있습니다."); //중복 체크
            throw new IllegalArgumentException();
        }
        points.add(newPoint);
        return points;
    }

    List<Integer> getXOnRowY(int y) {
        List<Integer> pointsOnRowY = new ArrayList<>();
        for (Point point : points) {
            if (point.contains(y)) {
                pointsOnRowY.add(point.getX());
            }
        }
        return pointsOnRowY;
    }

    static List<Point> checkSquare(List<Point> points) throws IllegalArgumentException {
        if (returnXSet(points).length != returnYSet(points).length) {
            throw new IllegalArgumentException();
        }
        return points;
    }

    static Integer[] returnXSet(List<Point> points) {
        Set<Integer> xSet = new HashSet<>();
        for (Point point : points) {
            xSet.add(point.getX());
        }
        return xSet.toArray(new Integer[0]);
    }

    static Integer[] returnYSet(List<Point> points) {
        Set<Integer> ySet = new HashSet<>();
        for (Point point : points) {
            ySet.add(point.getY());
        }
        return ySet.toArray(new Integer[0]);
    }

    public double calculateDistance() {
        return Utils.calculateDistance(points);
    }

    public int calculateArea() {
        return Utils.calculateArea(points);
    }
}