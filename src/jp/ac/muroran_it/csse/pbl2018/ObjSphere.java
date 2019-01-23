package jp.ac.muroran_it.csse.pbl2018;

import com.sun.opengl.util.GLUT;
import jp.ac.muroran_it.csse.vr_skelton.ModelSpaceObject;

import javax.media.opengl.GL;
import java.nio.DoubleBuffer;

public class ObjSphere extends ModelSpaceObject {

    @Override
    public void drawObject(GL gl) {


        double[] coo = CalcCoordinate.llCoordinate( 54.0, 2.0 )/*.array()/**/;

//        折れ線の反射特性を緑に設定
        float[] ambientDiffuseOfSphere = new float[] { 0.0f, 0.0f, 1.0f, 1.0f };
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, ambientDiffuseOfSphere, 0);


//        現在のモデルビュー行列を保存
        gl.glPushMatrix();

        gl.glTranslated( coo[0], coo[1], coo[2] );
        gl.glScaled( 0.1, 0.1,0.1 );


//        ここから描画フェイズ

        new GLUT().glutSolidSphere( 1.0, 10, 10 );

        gl.glScaled(10.0, 10.0, 10.0 );

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();



    }
}
