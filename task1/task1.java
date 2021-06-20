package task1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import javax.swing.JFrame;

public class task1 implements GLEventListener{
	
	
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      task1 l = new task1();
	      //creating frame
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(500, 500);
	      
	      final JFrame frame = new JFrame ("straight Line");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	      
	   }
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
      gl.glPointSize(5);
   	  gl.glBegin (GL2.GL_POINTS);//static field
   	  
   	  for (int i = 0; i < 100; i ++) {
   		double tempx = randNumb();
   		double tempy = randNumb();
   		
   		BigDecimal bdx = new BigDecimal(tempx).setScale(2, RoundingMode.HALF_UP);
        double x = bdx.doubleValue();
        
        BigDecimal bdy = new BigDecimal(tempy).setScale(2, RoundingMode.HALF_UP);
        double y = bdy.doubleValue();
   		
   		gl.glVertex2d(x,y);
   	  }
      
      
      gl.glEnd();
          
      
   }
   
   private double randNumb() {
	   double max = 1.0;
	   double min = -1.0;
	   double n = min + Math.random() * (max - min);
	   return n;
}
public void dispose(GLAutoDrawable arg0) {
      //method body
   }

   
   public void init(GLAutoDrawable drawable) {
      // method body
	   //4. drive the display() in a loop
	    }
   
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // method body
   }
   //end of main
}//end of classimport javax.media.opengl.GL2;