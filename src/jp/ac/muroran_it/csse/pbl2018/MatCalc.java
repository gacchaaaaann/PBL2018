package jp.ac.muroran_it.csse.pbl2018;

import java.util.Scanner;

/**
 * 行列の計算のため(逆行列を導くのがメイン)
 * (逆行列，行列式，余因子行列，次数，正方かの判定など)
 * @author 17024124 hanagata
 */
public class MatCalc {

/*
//   動作確認のため
    public static void main(String[] args) {

        test();

    }
*/


    /**
     * 逆行列
     *
     * @param mat 正方行列
     * @return  matの逆行列
     * @throws Exception 逆行列が存在しない(正則でない)場合
     */
    public static double[][] inverseMat(double[][] mat) throws Exception {

        /** 次元 */
        int d = dimension(mat);

        /** 返り値 */
        double[][] res = new double[d][d];

        /** 行列式 */
        double det = det(mat);

//        行列式が0の時はエラーを吐いて止まる．(正則でないので逆行列は存在しない)
            if ( det==0 )   throw new Exception("Not Regular Error.There is no inverse matrix.");

//        逆行列の生成(行列Aの逆行列のij成分はAjiの余因子を行列式で割ったものである)
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                res[i][j] = exFactor( mat, j, i ) / det;
            }
        }

        return res;
    }


    /**
     * 行列式を求める
     *
     * @param mat 正方行列
     * @return 行列式の値(スカラー値)
     */
    public static double det(double[][] mat) {

        switch (dimension(mat)) {

//            2次元の場合，愚直に計算(要素の掛け算で求められる)(実際はそれを行う2次元限定の行列式のメソッドに投げる)
            case 2:
                return secDet(mat);

//            2次元以外の場合，余因子展開をして求める行列式の次元を一つ下げる
            default:

                /** 次元(実際は要素数として使ってる) */
                int d = dimension(mat);
                /** 返り値 */
                double res = 0;

//                一行目の各要素とそれの余因子をかけて累積していく
                for (int i = 0; i < d; i++) {   //exFactorにiを投げるため，拡張for文は使えなかった
                    res += mat[0][i] * exFactor(mat, 0, i );
                }

                return res;
        }

    }


    /**
     * 余因子行列
     *
     * @param mat 正方行列
     * @return  matの余因子行列
     */
    public static double[][] exFactorMat(double[][] mat){

        /** 次元(返す行列の次元を決めるため) */
        int d = dimension(mat);

        /** 返り値 */
        double[][] res = new double[d][d];

//        返す行列の各要素に，各余因子を詰め込んでいく
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                res[i][j] = exFactor( mat, j, i );
            }
        }

        return res;
    }


    /**
     * 与えられた2次行列の行列式を求める
     *
     * @param mat 二次行列
     * @return matの行列式の値(スカラー)
     */
    public static double secDet(double[][] mat) {

//        次元が2でない行列は対象外なのでエラーを吐く
        if (dimension(mat) != 2) try {
            throw new Exception("Not Secondary Error.Cannot find the determinant.");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

//        2次元にのみ許されし行列式を求める計算を行い，返す．
        return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];

    }


    /**
     * 与えられた4値の行列の行列式を求める
     *
     * @param a 左上(0,0)
     * @param b 右上(0.1)
     * @param c 左下(1,0)
     * @param d 右下(1,1)
     * @return 行列式
     */
    public static double secDet(double a, double b, double c, double d) {
//        4値を行列に変換し正規のsecDetに渡す
        return secDet( new double[][]{{a, b}, {c, d}} );
    }


    /**
     * 与えられた行列において(a+1)行目(b+1)列目の余因子を求める
     *
     * @param mat 正方行列
     * @param a   行(0～)
     * @param b   列(0～)
     * @return 余因子
     */
    public static double exFactor(double[][] mat, int a, int b) {

//        余因子とは，ij要素自身を含むi行とj列を除いた1次元下の行列の行列式と，-1の(i+j)乗を掛けた値．
        return Math.pow(-1,a+b)*det(deleteElements(mat, a, b));

    }


    /**
     * 与えられたn次正方行列の(a+1)行と(b+1)列を除いたn-1次正方行列を返す
     *
     * @param mat n次正方行列
     * @param a 0～
     * @param b 0～
     * @return n-1次正方行列
     */
    public static double[][] deleteElements(double[][] mat, int a, int b) {

        /** 次数 */
        int d = dimension(mat);

        /** 返り値 */
        double[][] res = new double[d - 1][d - 1];

        /** 無視する行が発生した場合に生じる, 読み込み行列の行 と 代入先行列の行の番号 の差 */
        int addi = 0;

//        行を片っ端から網羅するが，1行無視するので見る回数は(次元ー１)回
        for (int i = 0; i < d - 1; i++) {

//            行番号が指定されたものと一致したとき，読み込む行列の行数を1つ先送りする(1行飛ばす)
            if (i == a) addi = 1;

            /** 無視する列が発生した場合に生じる， 読み込み行列の列 と 代入先行列の列の番号 の差 */
            int addj = 0;

//            列を片っ端から網羅するが，1列無視するので見る回数は(次元ー１)回
            for (int j = 0; j < d - 1; j++) {

//                列番号が指定されたものと一致したとき，読み込む行列の列数を1つ先送りする(1列飛ばす)
                if (j == b) addj = 1;

//                返り値用行列に値を写す
                res[i][j] = mat[i + addi][j + addj];

            }

        }

        return res;
    }


    /**
     * 与えられた正方行列の次数を返す(正方であるかの確認も兼ねる)
     *
     * @param mat 正方行列
     * @return 正方行列の次数(int)
     * @throws Exception
     */
    public static int dimension(double[][] mat) {

        /** 行数 */
        int line = mat.length;

        /** 列数 */
        int column = mat[0].length;

//        そもそも行列として成り立っていなければだめなので
        if ( !isMat(mat) ) {
            try {
                throw new Exception("Not Matrix Error.One line have a wrong number of elements.");
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

//        正方行列かどうかの確認
        if (line == column) return line;
        else {
            try {
                throw new Exception("Not Square Error.Cannot get dimension.");
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

    }


    /**
     * double型の正方行列を入力させる
     *
     * @return 正方行列
     */
    public static double[][] getMat() {

//        次元を聞く
        System.out.println("Number of dimension?");

        Scanner scan = new Scanner(System.in);
        int line = scan.nextInt();
        scan = new Scanner(System.in);

        /** 返り値(読み込んだ次元の空の正方行列を作る) */
        double[][] mat = new double[line][line];

//        要素の入力
        System.out.println("Input!");

        for (int i = 0; i < line; i++) {
            System.out.print("line" + (i + 1) + ":");
            for (int j = 0; j < line; j++) {
                mat[i][j] = scan.nextDouble();
            }
            System.out.println();
        }

//        出来上がった行列を返す
        return mat;
    }


    /**
     * 行列を表示する
     *
     * @param mat  表示する行列
     */
    public static void printMat(double[][] mat) {

        for ( double[] d : mat){
            for ( double dd : d ) System.out.print( dd + " " );
            System.out.println();
        }
    }


    /**
     * 行列A×行列Bをする
     *
     * @param matA  A
     * @param matB  B
     * @return 正方行列
     */
    public static double[][] multiMat( double[][] matA, double[][] matB ){

//        行列でなかったらException
        if ( !isMat(matA) || !isMat(matB) ){
            try {
                throw new Exception("Not Matrix Error.Cannot multiple.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        乗算の条件を満たしているか(A×BのAの列数とBの行数が一致するか)
        if( matA[0].length != matB.length ){
            try {
                throw new Exception("Not Each at Number of LINE and COLUMN Error.Cannot multiple.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        /** 返り値 */
        double[][] res = new double[matA.length][matB[0].length];

//        乗算
        for (int l = 0; l < matA.length; l++) {
            for (int c = 0; c < matB[0].length; c++) {
                for (int e = 0; e < matA[0].length; e++) {
                    res[l][c] += matA[l][e] * matB[e][c];
                }
            }
        }

        return res;

    }


    /**
     * 行列であるか(各行の要素数が同じか)を調べる
     *
     * @param mat 行列
     * @return 行列の時:true　行列でない時:false
     */
    public static boolean isMat( double[][] mat ){

        int column = mat[0].length;

        for (double[] l : mat) {
            if (l.length != column)     return false;  //一行目と要素数が一致しない行がいたら行列ではないのでfalse
        }

        return true;

    }


    /**
     * 動作確認用
     */
    public static void test() {

/*
//        inverseMatが本当に逆行列を返すのか(返ってきた(/・ω・)/わーい)
        double[][] A = getMat();
        double[][] Ai = inverseMat(A);

        printMat(A);
        System.out.println();
        printMat(Ai);
        System.out.println("\n");
        printMat(multiMat(A,Ai));
*/

/*
//        exFactorMatの行列を表示する
        printMat(exFactorMat(getMat()));
*/


/*
//        detが本当に行列式を返してくれるのか
        System.out.println( "det:" + det(getMat()));
*/


/*
//        deleteElementsから帰ってきた行列を表示する(deleteElementsの挙動は理想通り)
        double[][] mat = new double[][] {{1,2,3},{4,5,6},{7,8,9}};

        for ( double[] d : deleteElements( mat, 0, 1 )){
            for ( double dd : d ) System.out.print( dd + " " );
            System.out.println();
        }
*/


    }

}


