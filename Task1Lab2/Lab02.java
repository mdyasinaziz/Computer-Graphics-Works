package Task1Lab2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import java.lang.*;

public class Lab02 implements GLEventListener{
   
	private GLU glu;
   @Override
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
      DDA(gl, 0,0,0,50);
      DDA(gl, 15,0,50,0);
      DDA(gl, 50,0,50,50); 
      DDA(gl, 15,50,50,50);
      DDA(gl, 15,50,15,25);
      DDA(gl, 15,25,50,25);
      
   }
   @Override
   public void dispose(GLAutoDrawable arg0) {
      //method body
   }
   
   @Override
   public void init(GLAutoDrawable gld) {
       GL2 gl = gld.getGL().getGL2();
       glu = new GLU();

       gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
       gl.glColor4f(1.0f, 1.0f, 0.0f, 0.0f);//yellow
       gl.glViewport(-100, -50, 50, 100);
       gl.glMatrixMode(GL2.GL_PROJECTION);
       gl.glLoadIdentity();
       glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
   }

   

   @Override
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // method body
   }
   
   
   public void DDA(GL2 gl, float X0, float Y0, float X1, float Y1) {
	   
	   gl.glPointSize(5);
	   gl.glBegin (GL2.GL_POINTS);//static field
       
       //gl.glPointSize(3.0f);
       //gl.glColor3d(1, 0, 0);
       
       // calculate dx & dy
       float dx = X1 - X0;
       float dy = Y1 - Y0;
    
       // calculate steps required for generating pixels
       float steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);
    
       // calculate increment in x & y for each steps
       float Xinc = dx / (float) steps;
       float Yinc = dy / (float) steps;
    
       // Put pixel for each step
       float X = X0;
       float Y = Y0;
       for (int i = 0; i <= steps; i++)
       {
           //putpixel (round(X),round(Y),RED);  // put pixel at (X,Y)
    	   gl.glVertex2d(Math.round(X),Math.round(Y));
           X += Xinc;           // increment in x at each step
           Y += Yinc;           // increment in y at each step
           //delay(100);          // for visualization of line-
                                // generation step by step
       }
       
       gl.glEnd();
       
    }

   
   
   
   
   
   public static void main(String[] args) {
      //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      Lab02 l = new Lab02();
      glcanvas.addGLEventListener(l);
      glcanvas.setSize(400, 400);
      //creating frame
      final JFrame frame = new JFrame ("Lab 03");
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
   }//end of main
}//end of classimport javax.media.opengl.GL2;