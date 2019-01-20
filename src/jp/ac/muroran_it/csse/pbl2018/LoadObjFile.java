package jp.ac.muroran_it.csse.pbl2018;

import java.io.*;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * .objファイルを読み込み，直接触れる形にする
 * @author 17024124 hanagata
 */

public class LoadObjFile {

    /** 読み込むファイルの名前 */
    static String file_name = new String(); //ファイルの名前

    /** 頂点の座標 */
    static ArrayList<DoubleBuffer> ver = new ArrayList<>();
    /** 法線ベクトル */
    static ArrayList<DoubleBuffer> normal = new ArrayList<DoubleBuffer>();
    /** テクスチャ座標 */
    static ArrayList<DoubleBuffer> texture = new ArrayList<DoubleBuffer>();
    /** 凸多角面データ\n face[n]={頂点座標値番号,テクスチャ座標値番号,頂点法線ベクトル番号}\n 各番号は1から始まるインデックス番号 */
    static ArrayList<Integer[][]> face = new ArrayList<>();



    /**
     * 呼び出しの段階でファイル名を指定し読み込みまで行う.\n
     * これによりフィールドのデータはいつでも取り出せる状態になる
     * @param file_name 読み込むファイルの名前
     */
    LoadObjFile ( String file_name ){
        this.file_name = file_name;
        load();
    }


    /**
     * ファイルを読み込み各フィールドにインプットしていく.\n
     * ファイル名はフィールドに準ずる
     */
    public static void load() {

        File file = new File(file_name);

        double[] para;  //パラメータの一時保存用
        String[] spl1;  //文字列分断作業用
        String[] spl2;  //文字列分断作業用
        Integer[][] paras;  //二次元パラメータの一時保存用

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String str = br.readLine();

            while ( str != null ){

               if( str.contains(" ") && !str.startsWith("#"))   {   //splitメソッドのエラー回避 + コメント除外

                  spl1 = str.split(" ");  //パラメータごとに区切る

                  switch ( spl1[0] ){

                     case "v":  //頂点は3値(x座標，y座標，z座標)
                        para = new double[3];
                        for (int i=0; i<3; i++ ) para[i] = Double.parseDouble(spl1[i+2]);  //可読性を求めたのかスペースが2つ重ねてあり1つ目がnullになってるから飛ばす
                        ver.add(DoubleBuffer.wrap(para));
                        break;

                    case "vn":  //法線は3値(x値，y値，z値)
                        para = new double[3];
                        for (int i=0; i<3; i++ ) para[i] = Double.parseDouble(spl1[i+1]);
                        normal.add(DoubleBuffer.wrap(para));
                        break;

                    case "vt":  //テクスチャ座標は2値(x座標，y座標)
                        para = new double[2];
                        for (int i=0; i<2; i++ ) para[i] = Double.parseDouble(spl1[i+1]);
                        texture.add(DoubleBuffer.wrap(para));
                        break;

                     case "f":
                        paras = new Integer[3][ spl1.length - 1 ];   //para[0]:v，para[1]:t，para[2]:n，の行列．それぞれspl1のパラメータ数分ある
                        for ( int i=1; i<spl1.length; i++ ){
                            spl2 = spl1[i].split("/");  //1パラメータごとに3つの値が/で区切られて書かれている，/で各値を分断する
                            for ( int l=0; l<3; l++ )  paras[l][i-1] = Integer.parseInt(spl2[l]);
                        }
                        face.add(paras);
                        break;

                  }

                }

                str = br.readLine();

            }

            br.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
