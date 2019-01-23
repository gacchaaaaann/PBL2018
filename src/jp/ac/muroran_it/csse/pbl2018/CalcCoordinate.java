package jp.ac.muroran_it.csse.pbl2018;

import java.nio.DoubleBuffer;

public class CalcCoordinate {

    /** 半径 */
    private static double R = 10.0;


    /**
     * 緯度経度から座標を割り出す
     * @param latitude 緯度
     * @param longitude 経度
     * @return coo[a] ( a = { 0:x, 1:y, 2:z } )
     */
    public static double[] llCoordinate(double latitude, double longitude){

        longitude -= 120;
        longitude *= -1;

        double[] coo = new double[] { 0.0, 0.0, 0.0 };

        coo[1] = R * Math.sin( Math.PI * latitude / 180 );

        double r = R * Math.cos( Math.PI * latitude / 180 );  /* 主に判読性のための代入 */

        coo[0] = r * Math.cos( Math.PI * longitude / 180 );
        coo[2] = r * Math.sin( Math.PI * longitude / 180 );

        return coo;
    }

}
