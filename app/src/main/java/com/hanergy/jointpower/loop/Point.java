package com.hanergy.jointpower.loop;

import java.util.LinkedList;

/**
 * @author wanglei
 * @date 2017/10/27 10:27
 * @description
 */
public class Point {

    // 相邻点
    private LinkedList<Point> neighbors = new LinkedList<>();
    // point ID
    private int id;
    //极性
    private int pole;

    public Point( int id, int pole ) {
        this.id = id;
        this.pole = pole;
    }

    public LinkedList<Point> getNeighbors() {
        return neighbors;
    }

    public int getId() {
        return id;
    }

    public int getPole() {
        return pole;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        return ((Point) obj).getId() == id;
    }

}
