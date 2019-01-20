package jp.ac.muroran_it.csse.pbl2018;

import javax.media.opengl.GL;
import jp.ac.muroran_it.csse.vr_skelton.ModelSpaceObject;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * @author 17024124 hanagata
 */
public class ObjGlobe extends ModelSpaceObject {

    public LoadObjFile data = new LoadObjFile("globe.obj");

    @Override
    public void drawObject(GL gl) {

        try {
            drawface( gl, data.ver, data.face );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ObjGlobe(){

        int size = data.ver.size();
        double[] da = new double[]{};

        for (int i = 0; i < size; i++) {
            da = data.ver.get(i).array();

            da[0] = (da[0]-13.5)/6;
            da[1] = (da[1]-8.0)/6;
            da[2] = (da[2]-17.0)/6;

            data.ver.remove(i);
            data.ver.add(i, DoubleBuffer.wrap(da));

        }

    }


    /**
     * 各面の辺を描画する
     * @param gl    VR空間
     * @param ver   頂点座標
     * @param face  凸多角面データ
     * @throws Exception faceが空
     */
    private void drawLine(GL gl, ArrayList<DoubleBuffer> ver, ArrayList<Integer[][]> face ) throws Exception {

        if ( face.isEmpty() )   throw new Exception("face_data is empty;");

//        折れ線の反射特性を緑に設定
        float[] ambientDiffuseOfLine = new float[] {0.0f, 1.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfLine, 0);

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();


//        ここから描画フェイズ

        for ( Integer[][] f : face ) {

 //        折れ線を描画
            gl.glBegin(GL.GL_LINE_STRIP);

            for ( int vi : f[0] )   gl.glVertex3dv( ver.get( vi-1 ) );

            gl.glEnd();

        }

//        描画終わり

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();

    }


    /**
     * 各面を平面で描画する
     * @param gl    VR空間
     * @param ver   頂点座標
     * @param face  凸多角面データ
     * @throws Exception    faceが空
     */
    private void drawface ( GL gl, ArrayList<DoubleBuffer> ver, ArrayList<Integer[][]> face ) throws Exception{

        if ( face.isEmpty() )   throw new Exception("face_data is empty;");

//        折れ線の反射特性を緑に設定
        float[] ambientDiffuseOfLine = new float[] {0.0f, 1.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfLine, 0);

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();


//        ここから描画フェイズ

        for ( Integer[][] f : face ) {

 //        折れ線を描画
            if ( f[0].length == 3 ) gl.glBegin(GL.GL_TRIANGLES);
            else gl.glBegin(GL.GL_TRIANGLE_FAN);

            for ( int vi : f[0] )   gl.glVertex3dv( ver.get( vi-1 ) );

            gl.glEnd();

        }

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();



    }



}
