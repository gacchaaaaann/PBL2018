package jp.ac.muroran_it.csse.pbl2018;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * globe.objの分析をする
 * (最大値と最小値の中間，平均)
 * @author 17024124 hanagata
 */

/*実行結果*/
//        Max=( 72.4735, 66.8255, 37.4602 )
//        Min=( -45.7266, -50.7594, -71.9074 )
//
//        middle=( 13.373450000000002, 8.033050000000003, -17.223599999999998 )
//
//        difference=( 118.20009999999999, 117.5849, 109.3676 )
//
//
//        average=( 14.068603804775632, 23.47755812122744, -15.488706924691751 )
//
//        プロセスは終了コード 0 で完了しました

public class CalcVertex {

    /**
     * main
     */
    public static void main(String[] args) {

//        読み込み  ////////////
        LoadObjFile data = new LoadObjFile("globe.obj");


//        最大値最小値  //////////////////////////

        double[][] calc = maxAndMin(data.ver);

        System.out.println("Max=" + CalcCenter.strDoubleArray(calc[0]) + "\nMin=" + CalcCenter.strDoubleArray(calc[1]) );


//        中間  ////////////////////////

        double[] middle = new double[3];

        for (int i = 0; i < 3; i++) {
            middle[i] = (calc[0][i] + calc[1][i]) / 2;
        }

        System.out.println("\nmiddle=" + CalcCenter.strDoubleArray(middle));


//        差  /////////////////////////

        double[] differ = new double[3];

        for (int i = 0; i < 3; i++) {
            differ[i] = (calc[0][i] - calc[1][i]);
        }

        System.out.println("\ndifference=" + CalcCenter.strDoubleArray(differ));


//        平均  ///////////////////////

        System.out.println("\n\naverage=" + CalcCenter.strDoubleArray(average(data.ver)));

    }


    /**
     * x,y,z値の最大値と最小値を出す
     * @param list
     * @return res[a][b] ( a = { 0:最大値, 1:最小値 } , b = { 0:x, 1:y, 2:z } )
     */
    public static double[][] maxAndMin(ArrayList<DoubleBuffer> list){

        double[][] res = new double[2][3];
        double[] list_top_copy = list.get(0).array();  //最初のアイテムをコピーしてしまう(何度もアクセスするの重そうだから)

//       一つずつ値を代入していく(行列単位でまとめてってすると窓口を代入することになって別に扱えないから)
        for (int i = 0; i < 3; i++) {
            res[0][i] = list_top_copy[i];
            res[1][i] = list_top_copy[i];
        }


        double[] x;  //for文の中で行列化してコピーしておく用

        for (DoubleBuffer d : list) {
            x = d.array();  //行列化するついでに単体にしてしまう

            for (int i = 0; i < 3; i++) {
//                System.out.println( res[0][i] + "," + x[i] + " -> " + Math.max(res[0][i], x[i]) );
//                System.out.println( res[1][i] + "," + x[i] + " -> " + Math.min(res[1][i], x[i]) );

                res[0][i] = Math.max(res[0][i],x[i]);  //過去最大と比較して大きい方
                res[1][i] = Math.min(res[1][i],x[i]);  //過去最少と比較して小さい方

//                System.out.println( res[0][i] + "," + res[1][i] + "\n" );

            }

        }

        return res;
    }


    /**
     * x,y,z値の平均を出す
     * @param list
     * @return sum[a] ( a = { 0:x, 1:y, 2:z } )
     */
    public static double[] average(ArrayList<DoubleBuffer> list) {
        
        int n = 0;  //要素数カウント用
        double[] sum = {0, 0, 0};

        for (DoubleBuffer x : list) {
            double[] vector = x.array();  //行列化するついでに単体にしてしまう

            for (int i = 0; i < 3; i++) {
                sum[i] += vector[i];  //それぞれsumに積み重ねる
            }

            n++;  //積み重ねが終わったのでカウント
        }

        for (int i = 0; i < 3; i++) {
            sum[i] = sum[i] / n;  //要素数で割って平均にする
        }
        
        return sum;
    }


}
