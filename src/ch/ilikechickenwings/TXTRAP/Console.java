package ch.ilikechickenwings.TXTRAP;

import java.awt.Color;
import ch.ilikechickenwings.TXTRAP.CStyler;

public class Console {
	

	
	public static CStyler standartOutput = new CStyler(Color.BLACK,Color.WHITE,true,false);
	public static CStyler standartListOutput = new CStyler(Color.BLACK,Color.WHITE,true,true);
	public static CStyler standartEvent = new CStyler(Color.WHITE,Color.BLACK,true,false);
	public static CStyler standartCommand = new CStyler(Color.BLACK,Color.WHITE,false,true);
	public static CStyler startOutput = new CStyler(Color.CYAN,Color.BLACK,true, false);
	public static CStyler startOutputComment =new CStyler(Color.RED,Color.BLACK,false, true);
	public static CStyler errorOutput =new CStyler(Color.RED,Color.WHITE,true, false);
	
	
}
