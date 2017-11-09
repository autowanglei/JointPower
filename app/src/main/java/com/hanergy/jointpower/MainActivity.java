package com.hanergy.jointpower;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanergy.jointpower.loop.Line;
import com.hanergy.jointpower.loop.LoopHelp;
import com.hanergy.jointpower.loop.Point;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private LoopHelp loopHelp;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loopHelp = LoopHelp.getInstance();
//        linkLine(null);


    }

    Point[] points = new Point[]{new Point(0, 2), new Point(1, 2), new Point(2, 2), new Point(3, 2),
            new Point(4, 2), new Point(5, 2), new Point(6, 2), new Point(7, 2), new Point(8, 2), new Point(9, 2),
            new Point(10, 2), new Point(11, 2), new Point(12, 2), new Point(13, 2)/*,
                new Point(14, 2), new Point(15, 2), new Point(16, 2), new Point(17, 2)*/};

    public void linkLine( View view ) {
        loopHelp.clear();
        loopHelp.linkLine(new Line(points[1], points[2]));
        loopHelp.linkLine(new Line(points[1], points[3]));
        loopHelp.linkLine(new Line(points[2], points[3]));
        loopHelp.linkLine(new Line(points[2], points[4]));
        loopHelp.linkLine(new Line(points[3], points[5]));
        loopHelp.linkLine(new Line(points[4], points[5]));
        loopHelp.linkLine(new Line(points[4], points[9]));
        loopHelp.linkLine(new Line(points[5], points[6]));
        loopHelp.linkLine(new Line(points[7], points[6]));
        loopHelp.linkLine(new Line(points[7], points[8]));
        loopHelp.linkLine(new Line(points[9], points[8]));

        loopHelp.linkLine(new Line(points[10], points[11]));
        loopHelp.linkLine(new Line(points[10], points[12]));
        loopHelp.linkLine(new Line(points[10], points[13]));
        loopHelp.linkLine(new Line(points[11], points[13]));
        loopHelp.linkLine(new Line(points[11], points[12]));
        loopHelp.linkLine(new Line(points[13], points[12]));
//        loopHelp.breakLine(new Line(points[13], points[12]));
//
//        loopHelp.linkLine(new Line(points[14], points[15]));
//        loopHelp.linkLine(new Line(points[14], points[16]));
//        loopHelp.linkLine(new Line(points[14], points[17]));
//        loopHelp.linkLine(new Line(points[15], points[17]));
//        loopHelp.linkLine(new Line(points[15], points[16]));
//        loopHelp.linkLine(new Line(points[17], points[16]));
    }

    public void search( View view ) {
        loopHelp.searchLoop();
    }

    public void breakLine( View view ) {
        loopHelp.breakLine(new Line(points[4], points[5]));
    }

}
