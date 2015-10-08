package ch.ilikechickenwings.TXTRAP;

import java.io.Serializable;

public class NetInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txt;
	private CStyler style;
	private boolean clearTxt;
	private boolean singleLine;
	
	
	public NetInput(String str){
		setTxt(str);
		setStyle(Console.standardOutput);
		setClearTxt(false);
	}
	
	public NetInput(String str, CStyler sty){
		setTxt(str);
		setStyle(sty);
		setClearTxt(false);
	}
	
	
	public NetInput(String str, boolean clear){
		setTxt(str);
		setStyle(Console.standardOutput);
		setClearTxt(clear);
	}
	
	
	public NetInput(String str, CStyler sty, boolean clear){
		setTxt(str);
		setStyle(sty);
		setClearTxt(clear);
	}
	
	
	
	public NetInput(String str, CStyler sty, boolean clear, boolean sL) {
		setTxt(str);
		setStyle(sty);
		setClearTxt(clear);
		setSingleLine(sL);
	}

	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public CStyler getStyle() {
		return style;
	}
	public void setStyle(CStyler style) {
		this.style = style;
	}

	public boolean isClearTxt() {
		return clearTxt;
	}



	public void setClearTxt(boolean clearTxt) {
		this.clearTxt = clearTxt;
	}

	public boolean isSingleLine() {
		return singleLine;
	}

	public void setSingleLine(boolean singleLine) {
		this.singleLine = singleLine;
	}
	
	
	
	
}
