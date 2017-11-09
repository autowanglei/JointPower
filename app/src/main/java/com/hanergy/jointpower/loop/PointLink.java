package com.hanergy.jointpower.loop;

import java.util.LinkedList;

/**
 * @author wanglei
 * @date 2017/10/27 10:29
 * @description
 */
public class PointLink extends LinkedList<Point> {

    /**
     * size相等，并且全部包含另一个PointLink，则认为两个PointLink equal。
     * 去除逆向搜索到的回路。
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals( Object o ) {
        return ((this.size() == ((PointLink) o).size()) && this.containsAll((PointLink) o));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
