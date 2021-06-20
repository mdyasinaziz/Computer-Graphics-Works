package task2;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;

public class task2 implements GLEventListener{
	
	
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	   	  
	      
	      task2 l = new task2();
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
   	  
   	  
   		
   	File f = new File ("E:\\My Eclipse Workspace\\Lab01\\src\\myCoordinates.txt");
	
	BufferedReader br = null;
	try {
		br = new BufferedReader (new FileReader(f));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	String st;
	
	try {
		while((st = br.readLine())!=null) {
			if (!st.isEmpty()) {
				String[] splitSt = st.split(",");
				double x = Double.parseDouble(splitSt [0]);
				double y = Double.parseDouble(splitSt [1]);
				gl.glVertex2d(x,y);
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   	  
      
      
      gl.glEnd();
          
      
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
