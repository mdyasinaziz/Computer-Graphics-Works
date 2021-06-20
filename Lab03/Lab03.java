package Lab03;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import java.lang.*;

public class Lab03 implements GLEventListener{
   
	private GLU glu;
   @Override
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
      MidpointLine(gl, 0,0,0,50);
      MidpointLine(gl, 15,0,50,0);
      MidpointLine(gl, 50,0,50,50); 
      MidpointLine(gl, 15,50,50,50);
      MidpointLine(gl, 15,50,15,25);
      MidpointLine(gl, 15,25,50,25); 
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
   private int FindZone(int x0, int y0, int x1, int y1) {
       int dx = x1 - x0, dy = y1 - y0;
       if (Math.abs(dx) >Math.abs(dy)) {
           if (dy >= 0 && dx >= 0)
               return 0;
           else if (dx < 0 && dy >= 0)
               return 3;
           else if (dx < 0 && dy < 0)
               return 4;
           else return 7;
       } else {
           if (dy >= 0 && dx >= 0)
               return 1;
           else if (dx < 0 && dy >= 0)
               return 2;
           else if (dx < 0 && dy < 0)
               return 5;
           else return 6;
       }
   }
   
   private int[] ConvertToZone0(int x1, int y1, int x2, int y2, int zone) {
	   
	   int newX1=0;
	   int newY1=0;
	   int newX2=0;
	   int newY2=0;
	   if (zone==0) {
		   newX1=x1;
		   newY1=y1;
		   newX2=x2;
		   newY2=y2;
	   }
	   else if(zone==1){
		   newX1=y1 ;
		   newY1=x1 ;
		   newX2=y2 ;
		   newY2=x2;		  
	   }
	   
	   else if(zone==2){
		   newX1=y1 ;
		   newY1=-x1 ;
		   newX2=y2 ;
		   newY2=-x2;		  
		   }
	   
	   else if(zone==3){
		   newX1=-x1 ;
		   newY1=y1 ;
		   newX2=-x2 ;
		   newY2=y2;		  
		   }
	   
	   else if(zone==4){
		   newX1=-x1 ;
		   newY1=-y1;
		   newX2=-x2 ;
		   newY2=-y2;		  
		   }
	   else if(zone==5){
		   newX1=-y1 ;
		   newY1=-x1 ;
		   newX2=-y2 ;
		   newY2=-x2;		  
		   }
	   else if(zone==6){
		   newX1=-y1 ;
		   newY1=x1 ;
		   newX2=-y2 ;
		   newY2=x2;		  
		   }
	   
	   else if(zone==7){
		   newX1=x1 ;
		   newY1=-y1 ;
		   newX2=x2 ;
		   newY2=-y2;		  
		   }
	   
	   int [] points = {newX1,newY1,newX2,newY2};
	   return points;
   }
   
   private void OriginalZone(GL2 gl,float x, float y, int zone) {
		  float originalX=0;
		  float originalY=0;
		   
		   if (zone==0) {
			   originalX=x;
			   originalY=y;
		   }
		   else if (zone==1) {
			   originalX=y;
			   originalY=x;
		   }
		   else if (zone==2) {
			   originalX=-y;
			   originalY=x;
		   }
		   else if (zone==3) {
			   originalX=-x;
			   originalY=y;
		   }
		   else if (zone==4) {
			   originalX=-x;
			   originalY=-y;
		   } 
		   else if (zone==5) {
			   originalX=-y;
			   originalY=-x;
		   } 
		   else if (zone==6) {
			   originalX=y;
			   originalY=-x;
		   }
		   else if (zone==7) {
			   originalX=x;
			   originalY=-y;
		   }
		   
		gl.glVertex3f(originalX,originalY,0);
	   }
   
   public void MidpointLine(GL2 gl, int x1, int y1, int x2, int y2) {
	   
	   int z=FindZone(x1,y1,x2,y2);
	   int points[]=ConvertToZone0(x1,y1,x2,y2,z);
       
	   gl.glPointSize(3.0f);
	   gl.glColor4f(1.0f, 1.0f, 0.0f, 0.0f);//yellow
       gl.glBegin (GL2.GL_POINTS);
       
       float dx= points[2]-points[0];
       float dy= points[3]-points[1];
       float d = 2*dy - dx;
       float incrEast = 2*dy;
       float incrNorthEast = 2*(dy-dx);
       
       float x= points[0];
       float y= points[1];
       
       while (x<points[2]) {
    	   if (d<=0) {
    		   d=d+incrEast;
    		   x=x+1;
    		   y=y+0;
    	   }
    	   else {
    		   d=d+incrNorthEast;
    		   x=x+1;
    		   y=y+1;
    	   }
    	   OriginalZone(gl,x,y,z);
       }
       gl.glEnd();
    }
   
//   public void MidpointLine(GL2 gl, int x1, int y1, int x2, int y2) {
//	    int dx, dy, incrWest, incrNorthWest, d, x, y;
//	    
//	    gl.glPointSize(3.0f);
//	    gl.glColor4f(1.0f, 1.0f, 0.0f, 0.0f);//yellow
//        gl.glBegin (GL2.GL_POINTS);
//	    
//	    dx=x1-x2;
//	    dy=y1-y2;
//	    d=2*dy+dx;
//	    incrWest=2*dy;
//	    incrNorthWest=2*(dy+dx);
//	    x=x1;
//	    y=y1;
//	     while(x>=x2){
//	         if(d<=0) {
//	             //choose NW
//	             d=d+incrNorthWest;
//	             x=x-1;
//	        }
//	        else{
//	              //choose W
//	             d=d+incrWest;
//	             x=x-1;
//	             y=y+1;
//	        }
//	         OriginalZone(gl,x,y,3);
//	    }
//	     gl.glEnd();
//	} 
   
   

   public static void main(String[] args) {
      //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      Lab03 l = new Lab03();
      glcanvas.addGLEventListener(l);
      glcanvas.setSize(400, 400);
      //creating frame
      final JFrame frame = new JFrame ("shawwar assignment");
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
   }//end of main
}//end of classimport javax.media.opengl.GL2;