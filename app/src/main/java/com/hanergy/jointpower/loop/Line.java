package com.hanergy.jointpower.loop;

/**
 * @author wanglei
 * @date 2017/11/1 11:42
 * @description
 */
public class Line {

    private Point[] points = new Point[2];

    public Line( Point start, Point stop ) {
        points[0] = start;
        points[1] = stop;
    }

    public Point[] getPoints() {
        return points;
    }


}
