package ch.ilikechickenwings.TXTRAP.Interface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class SaveListener implements Runnable {

	private WorldFrame worldFrame;
	
	public SaveListener(WorldFrame wF){
		
		worldFrame=wF;
	}
	
	
	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		
		while(s.hasNext()){
			if(s.nextLine().equals("save")){
				
				
					File file0 = new File((new StringBuilder()).append(System.getProperty("user.home")).append("/.TXTRAPServer/".concat("world.w")).toString());
			        if(!file0.exists())
			        {
			            File file1 = new File((new StringBuilder()).append(System.getProperty("user.home")).append("/.TXTRAPServer").toString());
			            file1.mkdir();
			        }
					
				   String s1 = new StringBuilder().append(System.getProperty("user.home")).append("/.TXTRAPServer/").append("world.w").toString();
				    try {
				        
				    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(s1));
				        oos.writeObject(worldFrame);
				        oos.close();
				    } catch(Exception ex) {
				        ex.printStackTrace();
				    }
				    System.out.println("Saved the world");
			}
			
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		s.close();
	}

}
