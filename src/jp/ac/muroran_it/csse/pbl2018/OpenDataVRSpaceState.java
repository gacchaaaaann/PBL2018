/*
 * OpenDataVRSpaceState.java
 * ビューアで表示するオブジェクトを付加したVR空間状態全体を管理するVRSpaceStateの実装
 * Oct. 2016 by Muroran Institute of Technology
 */
package jp.ac.muroran_it.csse.pbl2018;

import java.util.ArrayList;
import jp.ac.muroran_it.csse.vr_skelton.Position;
import jp.ac.muroran_it.csse.vr_skelton.ModelSpaceObject;
import jp.ac.muroran_it.csse.vr_skelton.VRSpaceState;

/**
 * 地球儀を表示(12/13)
 * @author hanagata
 * @version 3.0
 */
public class OpenDataVRSpaceState extends VRSpaceState {
    // VR空間に描画されるオブジェクトのリスト
    // 描画オブジェクトは全てこのリストに格納することで一元管理できる
    public static ArrayList<ModelSpaceObject> objects = new ArrayList<>();
    //////////////////////////////////////////////////////
    // 描画される物体オブジェクトの追加方法：
    // (1) 物体オブジェクトのクラスを定義(VRSpaceObjectを継承して実装)
    // (2) オブジェクトの宣言(クラス変数の宣言)
    // (3) コンストラクタ内でインスタンス生成とリストへの追加
    // (4) 必要に応じてゲッター(例:getMap())などの追加
    // (5) オブジェクトに影響を与えるコントロール側処理の作成
    //////////////////////////////////////////////////////
    

    /** 地球儀 */
    private ObjGlobe glb;

    /** 球 */
    private ObjSphere sphere;

    /** 軸 */
    private ObjAxis axis;



    /**
     * コンストラクタ
     * @param name アプリケーション名
     */
    public OpenDataVRSpaceState(String name) {
        // アプリケーション名を登録
        super(name);
        
        //▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
        // VRオブジェクトの生成と登録
        //------------------------------------------------------
        // ここに空間中で扱う物体オブジェクトの生成・追加を記述する。
        // 表示を中止するときはリストへの追加をコメント化すればよい。


          glb = new ObjGlobe();
          objects.add(glb);

          sphere = new ObjSphere();
//          objects.add(sphere);

          axis = new ObjAxis();
          objects.add(axis);

        //▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
    }

    /**
     * スタイラスのボタン状態の設定。
     * ボタンを押したときにはスタイラスに接触している震源情報を選択する。
     * @param pressed スタイラスのボタンを押す場合true。
     */
    @Override
    public synchronized void setStylusPressed(boolean pressed) {
        super.setStylusPressed(pressed); // 継承元のsetStylusPressedを実行
        if (pressed) {
            // モデル空間でのスタイラスの座標値を取得
            Position stylusPosition = toModelSpace(getStylusPosition());
//            // スタイラスに最も近い震源情報の座標値を選択
//            if(eq!=null){
//                eq.selectNearestEqCenter(stylusPosition);
//            }
        }
    }

    /**
     * VR空間オブジェクトのリストを取得
     * @return VR空間に描画されるオブジェクトリスト
     */
    public ArrayList<ModelSpaceObject> getVRSpaceObjects(){
        return objects;
    }
    
    /**
     * スクリーン上に描画する補助情報を取得。
     * @return 選択中の震源情報。選択中の震源情報がない場合はnull。
     */
    public synchronized String[] getInformation() {
        String[] strings = null;
        return strings;
    }


}
