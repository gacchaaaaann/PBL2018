package jp.ac.muroran_it.csse.pbl2018;

import java.nio.DoubleBuffer;

/**
 * 空間図形の計算を用いて地球儀を球とみなしたときの大体の中心座標を求める
 * @author 17024124 hanagata
 */
public class CalcCenter {

    public static void main(String[] args) {

        LoadObjFile obj = new LoadObjFile("globe.obj");

        /** 読み込んだデータのカウント用．(読み込み4つでリセット) */
        int i = 0;
        /** 読み込んだ頂点座標の格納用 */
        double[][] ver = new double[4][];

        /** 導出した中心座標の分散の計算用　analysis[0]:各値の2乗を足し重ねる analysis[1]:各値を足し重ねる */
        double[][] analysis = new double[2][3];
        /** 導出した中心座標のx,y,zそれぞれの最大値 */
        double[] max = new double[3];
        /** 導出した中心座標のx,y,zそれぞれの最小値 */
        double[] min = new double[3];
        /** 導出した中心座標の個数 */
        int number = 0;

//        頂点座標を片っ端から使っていく　→使う座標が接近していて中心座標のバラつきに差が出やすくとてもよくない
        for ( DoubleBuffer get : obj.ver ){

            ver[i] = get.array();

//            データを4つ読み込んだら処理する
            if ( i==3 ){

//                ax+by+cz = m の形の平面の式を3つ連立してxyzについて解く( (a b c)→係数行列A , m→定数行列M )

                /** 連立する平面の式の係数行列 */
                double[][] A = new double[3][3];
                /** 連立する平面の式の定数行列 */
                double[][] M = new double[3][1];

//               jは式単位のカウント
                for (int j = 0; j < 3; j++) {
//                   kは係数単位のカウント( k={ 0:x, 1:y, 2:z} )
                    for (int k = 0; k < 3; k++) {

                        A[j][k] = ver[j][k] - ver[j+1][k];  //2点を結ぶ直線のベクトル座標
                        M[j][0] += Math.pow( ver[j][k], 2 ) - Math.pow( ver[j+1][k], 2 );  //x,y,z座標の2乗の差を累計する

                    }
                    M[j][0] /= 2;
                }


                /** 導出した中心座標の格納用 */
                double[][] answer;

                /** MにAの逆行列を掛ける(万が一逆行列が存在しない場合その組み合わせは破棄し次に行く)  */
                try {
                    answer = MatCalc.multiMat( MatCalc.inverseMat(A), M );
                } catch (Exception e) {
                    e.printStackTrace();
                    i = 0;
                    continue;
                }

//                導出された中心座標の表示
                MatCalc.printMat( answer );

                System.out.println();


//                以下分析用処理

//                初期値設定
                if ( number == 0 ){
                    for (int j = 0; j < 3; j++) {
                        max[j] = answer[j][0];
                        min[j] = answer[j][0];
                    }
                }

                for (int j = 0; j < 3; j++) {
                    max[j] = Math.max( max[j], answer[j][0] );
                    min[j] = Math.min( min[j], answer[j][0] );
                }


//                analysis[0]:座標の2乗の累計, analysis[1]:座標の累計  j = { 0:x, 1:y, 2:z }
                for (int j = 0; j < 3; j++) {
                    analysis[0][j] += Math.pow( answer[j][0], 2 );
                    analysis[1][j] += answer[j][0];
                }
//                導出した中心座標の個数をカウントする
                number++;

//                4データ読み込みなおす(i++の工程を見越したうえで-1)
                i = -1;
            }

            i++;
        }


//        総合の分析

        System.out.println("\nnumber = " + number );

//        2乗の平均と普通の平均
        for (int j = 0; j < 3; j++) {
            analysis[0][j] /= number;
            analysis[1][j] /= number;
        }

        System.out.println("\naverage = " + strDoubleArray(analysis[1]) );

//        平均の2乗を2乗の平均から引く
        for (int j = 0; j < 3; j++) {
            analysis[0][j] -= Math.pow( analysis[1][j] , 2 );
        }

        System.out.println("\ndispersion = " + strDoubleArray(analysis[0]) );

        System.out.println("\nMax = " + strDoubleArray(max) + "\nmin = " + strDoubleArray(min) );
    }


    /**
     * 行列を()で括ったString型にする(表示の簡易表記のため)
     *
     * @param array 行列
     * @return 行列の各値を,と空白で区切り()で括った状態のString型オブジェクト
     */
    public static String strDoubleArray( double[] array ){

        String res = "( ";


        for (int i = 0; i < array.length; i++) {
            res += array[i];
            if( i != array.length-1 )   res += ", ";
        }

        return res + " )";
    }

}
