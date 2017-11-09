package com.hanergy.jointpower.loop;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wanglei
 * @date 2017/10/27 10:31
 * @description
 */
public class LoopHelp {

    private final String TAG = getClass().getSimpleName();
    private List<Point> connectPoints = new ArrayList<>();
    LinkedList<PointLink> mLoopList = new LinkedList<>();

    private LoopHelp() {

    }

    public static LoopHelp getInstance() {
        return LoopHolder.instance;
    }

    private static class LoopHolder {
        private static final LoopHelp instance = new LoopHelp();
    }

    /**
     * 搜索回路
     *
     * @return
     */
    public List<PointLink> searchLoop() {
        Log.i(TAG, "into searchLoop");
        removeUnUsePoints();
        if (connectPoints.size() >= 2) {
            LinkedList<PointLink> loopList = new LinkedList<>();
            PointLink startList = new PointLink();
            do {
                loopList.clear();
                startList.clear();
                startList.addLast(connectPoints.get(0));
                loopList.add(startList);
                do {
                    LinkedList<PointLink> tempList = (LinkedList<PointLink>) loopList.clone();
                    loopList.clear();
                    for (int i = 0; i < tempList.size(); i++) {
                        loopList.addAll(searchLoop(tempList.get(i)));
                    }
                } while (loopList.size() != 0);
                removeLoopsPoint();
            } while (connectPoints.size() != 0);
            printfLoop(mLoopList, "mLoopList");
        }
        return mLoopList;
    }

    /**
     * 去除不能形成回路的点（一个有关联的回路图中，任意点为起点，循环searchLoop一遍后，可得到该关联回路网的所有回路，
     * 故该关联回路网中所有的点均不会再形成新的回路）
     */
    private void removeLoopsPoint() {
        for (int i = 0; i < connectPoints.size(); i++) {
            for (int j = 0; j < mLoopList.size(); j++) {
                if (mLoopList.get(j).contains(connectPoints.get(i))) {
                    connectPoints.remove(i);
                    i--;
                    j = 0;
                    break;
                }
            }
        }
    }

    /**
     * @param parentList 起始点到当前搜索点的链表
     * @return
     */
    private LinkedList<PointLink> searchLoop( PointLink parentList ) {
        LinkedList<PointLink> loopList = new LinkedList<>();
        Point lastPoint = parentList.getLast();
        if (parentList.size() != 1) {
            /*获取倒数第二个point*/
            Point point = parentList.get(parentList.size() - 2);
            for (int i = 0; i < lastPoint.getNeighbors().size(); i++) {
                Point neighbor = lastPoint.getNeighbors().get(i);
                /*邻点不是parentList的最后一个，防止逆向搜索*/
                if (!neighbor.equals(point)) {
                    PointLink list = (PointLink) parentList.clone();
                    if (!list.contains(neighbor)) {
                        list.addLast(neighbor);
                        loopList.add(list);
                    } else {
                        int index = list.indexOf(neighbor);
                        list.addLast(neighbor);
                        PointLink loop = new PointLink();
                        loop.addAll(list.subList(index, list.size()));
                        if (!mLoopList.contains(loop)) {
                            mLoopList.add(loop);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < lastPoint.getNeighbors().size(); i++) {
                Point neighbor = lastPoint.getNeighbors().get(i);
                PointLink list = (PointLink) parentList.clone();
                list.addLast(neighbor);
                loopList.add(list);
            }
        }
        return loopList;
    }

    /**
     * 输出回路
     *
     * @param loops
     * @param loopTag
     */
    private void printfLoop( List<PointLink> loops, String loopTag ) {
        for (int i = 0; i < loops.size(); i++) {
            PointLink loop = loops.get(i);
            String pointIds = "";
            for (int j = 0; j < loop.size(); j++) {
                pointIds += loop.get(j).getId() + " ";
            }
            Log.i(TAG, loopTag + "[" + i + "]:" + pointIds);
        }

    }

    /**
     * 去除不能形成回路的点（只有一个相邻点的piont不能形成回路）
     */
    private void removeUnUsePoints() {
        boolean hasUnUsePoints = false;
        for (int i = 0; i < connectPoints.size(); i++) {
            if (connectPoints.get(i).getNeighbors().size() <= 1) {
                Point point = connectPoints.get(i);
                point.getNeighbors().get(0).getNeighbors().remove(point);
                connectPoints.remove(i);
                i--;
                hasUnUsePoints = true;
            }
        }
        if (hasUnUsePoints) {
            removeUnUsePoints();
        }
    }

    /**
     * 连线
     *
     * @param line
     */
    public void linkLine( Line line ) {
        for (int i = 0; i < line.getPoints().length; i++) {
            Point point = line.getPoints()[i];
            if (!point.getNeighbors().contains(line.getPoints()[1 - i])) {
                line.getPoints()[i].getNeighbors().addLast(line.getPoints()[1 - i]);
            }
            if (!connectPoints.contains(point)) {
                connectPoints.add(point);
            }
        }
    }

    /**
     * 清除已连接的点及搜索到的回路
     */
    public void clear() {
        Log.i(TAG, "into clear");
        for (int i = 0; i < connectPoints.size(); i++) {
            connectPoints.get(i).getNeighbors().clear();
        }
        connectPoints.clear();
        mLoopList.clear();
    }

    /**
     * 断开连接
     *
     * @param line
     */
    public void breakLine( Line line ) {
        Log.i(TAG, "into breakLine");
        for (int i = 0; i < line.getPoints().length; i++) {
            if (connectPoints.contains(line.getPoints()[i])) {
                if (line.getPoints()[i].getNeighbors().contains(line.getPoints()[1 - i])) {
                    line.getPoints()[i].getNeighbors().remove(line.getPoints()[1 - i]);
                }
            }
        }
    }

}
