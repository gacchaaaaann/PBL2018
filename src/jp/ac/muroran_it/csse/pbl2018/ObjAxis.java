package jp.ac.muroran_it.csse.pbl2018;

import com.sun.opengl.util.GLUT;
import jp.ac.muroran_it.csse.vr_skelton.ModelSpaceObject;

import javax.media.opengl.GL;

public class ObjAxis extends ModelSpaceObject {

    @Override
    public void drawObject(GL gl) {

//        x軸

//        折れ線の反射特性を緑に設定
        float[] X = new float[] {1.0f, 0.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, X, 0);

        gl.glTranslated( 0.5, 0.0, 0.0 );
        gl.glScaled( 2.0, 0.1, 0.1 );

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();

//        ここから描画フェイズ

        new GLUT().glutSolidCube( 1.0f );

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();



//        y軸

//        折れ線の反射特性を緑に設定
        float[] Y = new float[] { 0.0f, 1.0f, 0.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, Y, 0);

        gl.glScaled( 0.5, 10.0, 10.0 );
        gl.glTranslated( -0.5, 0.5, 0.0 );
        gl.glScaled( 0.1, 2.0, 0.1 );

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();

//        ここから描画フェイズ

        new GLUT().glutSolidCube( 1.0f );

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();



//        z軸

//        折れ線の反射特性を緑に設定
        float[] Z = new float[] { 0.0f, 0.0f, 1.0f, 1.0f};
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, Z, 0);

        gl.glScaled( 10.0, 0.5, 10.0 );
        gl.glTranslated( 0.0, -0.5, 0.5 );
        gl.glScaled( 0.1, 0.1, 2.0 );

//        現在のモデルビュー行列を保存
        gl.glPushMatrix();

//        ここから描画フェイズ

        new GLUT().glutSolidCube( 1.0f );

//        保存したモデルビュー行列を復帰
        gl.glPopMatrix();




    }
}
