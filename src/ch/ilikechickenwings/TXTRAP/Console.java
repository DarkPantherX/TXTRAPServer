package ch.ilikechickenwings.TXTRAP;

import java.awt.Color;


import ch.ilikechickenwings.TXTRAP.CStyler;

public class Console {
	
	
	
	public static CStyler standardOutput = new CStyler(Color.BLACK,Color.WHITE,true,false);
	public static CStyler standardListOutput = new CStyler(Color.BLACK,Color.WHITE,true,true);
	public static CStyler standardEvent = new CStyler(Color.WHITE,Color.BLACK,true,false);
	public static CStyler standardCommand = new CStyler(Color.BLACK,Color.WHITE,false,true);
	public static CStyler startOutput = new CStyler(Color.CYAN,Color.BLACK,true, false);
	public static CStyler startOutputComment =new CStyler(Color.RED,Color.BLACK,false, true);
	public static CStyler errorOutput =new CStyler(Color.RED,Color.WHITE,true, false);
	
	
}
