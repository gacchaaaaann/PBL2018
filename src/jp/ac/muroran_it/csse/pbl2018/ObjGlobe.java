package jp.ac.muroran_it.csse.pbl2018;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;
import jp.ac.muroran_it.csse.vr_skelton.ModelSpaceObject;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * @author 17024124 hanagata
 */
public class ObjGlobe extends ModelSpaceObject {

    public LoadObjFile data = new LoadObjFile("globe.obj");


    /**
     * 大体の中心は原点，半径約10
     * @param gl
     */
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
            da[1] = (da[1]-23.5+15.0)/6;
            da[2] = (da[2]-17.0+40.0)/6;

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
        float[] ambientDiffuseOfLine = new float[] {0.0f, 1.0f, 0.0f, 0.5f};
        float[] ambientDiffuseOfBack = new float[]{0.2f, 0.2f, 0.2f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfLine, 0);
        gl.glMaterialfv(GL.GL_BACK, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfBack, 0);

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();

//        地球儀のGLの座標系の原点を指定しないといけないかもしれない(何も指定しないと(0,0,0)ぽいですね)
//        gl.glTranslated();

//        ここから描画フェイズ

        for ( Integer[][] f : face ) {

 //        折れ線を描画
            if ( f[0].length == 3 ) gl.glBegin(GL.GL_TRIANGLES);
            else gl.glBegin(GL.GL_TRIANGLE_FAN);

            for ( int vi : f[0] )   gl.glVertex3dv( ver.get( vi-1 ) );

            gl.glEnd();

        }


        float[] ambientDiffuseOfSphere = new float[] { 0.0f, 0.0f, 1.0f, 0.5f };
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfSphere, 0);

//        new GLUT().glutSolidSphere( 10.0, 10, 10 );

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();



    }



}
