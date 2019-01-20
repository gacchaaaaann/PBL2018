package jp.ac.muroran_it.csse.pbl2018;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

public class LoaderTest {

    public static void main(String[] args) {
//        globe.objをobjに読み込む
        LoadObjFile obj = new LoadObjFile("globe.obj");


//        6点から仮の中心的なものを見つけようと思う

        



/*
//        一番手前の点と奥の点を表示する

//        解析
        double min = obj.ver.get(0).array()[2];
        double max = obj.ver.get(0).array()[2];
        double x;

        ArrayList<DoubleBuffer> min_list = new ArrayList<DoubleBuffer>();
        ArrayList<DoubleBuffer> max_list = new ArrayList<DoubleBuffer>();


        for ( DoubleBuffer bf : obj.ver ) {

            x = bf.array()[2];

            if( x < min ){
                min_list.clear();
                min_list.add(bf);
                min = x;
                System.out.println( min + "," + max );

            }else if ( x == min ) {
                min_list.add(bf);
                System.out.println("min+");

            }else if ( x > max ) {
                max_list.clear();
                max_list.add(bf);
                max = x;
                System.out.println( min + "," + max );

            }else if ( x == max ) {
                max_list.add(bf);
                System.out.println("max+");

            }
        }


//        表示
        System.out.println("MAX");

        for (DoubleBuffer bf : max_list) {
            for ( double d : bf.array() )   System.out.print( d + ",");
            System.out.println("");
        }

        System.out.println("");

        System.out.println("min");

        for (DoubleBuffer bf : min_list) {
            for ( double d : bf.array() )   System.out.print( d + ",");
            System.out.println("");
        }
*/




/*
//        一番左の点と右の点を表示する

//        解析
        double min = obj.ver.get(0).array()[0];
        double max = obj.ver.get(0).array()[0];
        double x;

        ArrayList<DoubleBuffer> min_list = new ArrayList<DoubleBuffer>();
        ArrayList<DoubleBuffer> max_list = new ArrayList<DoubleBuffer>();


        for ( DoubleBuffer bf : obj.ver ) {

            x = bf.array()[0];

            if( x < min ){
                min_list.clear();
                min_list.add(bf);
                min = x;

            }else if ( x == min ) {
                min_list.add(bf);

            }else if ( x > max ) {
                max_list.clear();
                max_list.add(bf);
                max = x;

            }else if ( x == max ) {
                max_list.add(bf);

            }
        }


//        表示
        System.out.println("MAX");

        for (DoubleBuffer bf : max_list) {
            for ( double d : bf.array() )   System.out.print( d + ",");
            System.out.println("");
        }

        System.out.println("");

        System.out.println("min");

        for (DoubleBuffer bf : min_list) {
            for ( double d : bf.array() )   System.out.print( d + ",");
            System.out.println("");
        }
*/



/*
//        最初の１０個の０からの距離を求めて表示する
        double[] v;
        double sum = 0;
        ArrayList<Double> sumlist = new ArrayList<>();

        for( int i=0; i<10; i++ ){

            v = obj.ver.get(i).array();

            for( double d : v ) sum += Math.pow(d,2);

            sumlist.add(sum);
            sum = 0;

        }

        for( double d : sumlist ) System.out.println(d);
*/



/*
    //        一番上の(最大のy座標を持つ)頂点をあぶりだす
        ArrayList<DoubleBuffer> max = new ArrayList<DoubleBuffer>();
        max.add(obj.ver.get(0));

        double maxDouble = obj.ver.get(0).array()[1];
        System.out.println(maxDouble);

        for ( DoubleBuffer v : obj.ver ){

            if ( v.array()[1] == maxDouble )  max.add(v);
            else if ( v.array()[1] > maxDouble ){
                max.clear();
                max.add(v);
                maxDouble = v.array()[1];
                System.out.println( maxDouble );
            }
       }

        for ( DoubleBuffer v : max ){
            for ( double n : v.array() ) System.out.print( n + " " );
            System.out.println();
        }
*/



/*
//        vertexの要素数を超えるインデックス番号を持つfaceをあぶりだす
        for ( Integer[][] f : obj.face )    for ( int i : f[0] )    if ( i >= 38110 ) System.out.println( i );
*/



/*
//        objのコンストラクタの各要素数を表示
        System.out.println( "vertex:" + obj.ver.size() + ", texture:" + obj.texture.size() + ", normal:" + obj.normal.size() + ", face:" + obj.face.size() );
*/



/*
//        objのfaceのline番目の前後2つを含め表示
        int line = 38110;
        Integer[][] bf;
        for ( int i = -2; i<3; i++ ){
            bf = obj.face.get(line+i);
            for ( Integer[] x : bf ){
                for ( int y : x ) System.out.print( y + " " );
                System.out.println();
            }
            System.out.println();
        }
*/



/*
//        objが読み込んだvertexのデータを並べて表示する
        for (Iterator<DoubleBuffer> it = obj.ver.iterator(); it.hasNext(); ) {
            System.out.println( it.next() );
        }
*/



/*
//        objが読み込んだfaceのデータを並べて表示する
        for (Iterator<Integer[][]> it = obj.face.iterator(); it.hasNext(); ){
            for ( Integer[] n : it.next() ){
                for ( Integer m : n ) System.out.print( m + "/" );
                System.out.println("");
            }
            System.out.println("");
        }
*/



/*
//        objが読み込んだfaceのデータから，頂点が3つのものと4つのものとそれ以外のものを数え上げる
        int three = 0, four = 0, other = 0;
        Integer[] n;

        for ( Integer[][] it : obj.face ){

            n = it[0];

            switch (n.length) {

                case 3:
                    three++;
                    break;

                case 4:
                    four++;
                    break;

                default:
                    other++;
                    System.out.println(n.length);
                    break;
            }

        }

        System.out.println( "3:" + three + ", 4:" + four + ", other:" + other );
*/




    }
}
