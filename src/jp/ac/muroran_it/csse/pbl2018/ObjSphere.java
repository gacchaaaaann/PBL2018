package jp.ac.muroran_it.csse.pbl2018;

import com.sun.opengl.util.GLUT;
import jp.ac.muroran_it.csse.vr_skelton.ModelSpaceObject;

import javax.media.opengl.GL;

public class ObjSphere extends ModelSpaceObject {

    @Override
    public void drawObject(GL gl) {


//        折れ線の反射特性を緑に設定
/*
        float[] ambientDiffuseOfLine = new float[] {0.0f, 1.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfLine, 0);
*/

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();


//        ここから描画フェイズ

        new GLUT().glutSolidSphere( 10.0, 10, 10 );

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();



    }
}
