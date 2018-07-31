
public class MacroName {
	
	String name;
	int KP,PP,EV,MDTP,KPDTP,SSTP;
	
	MacroName(){
		name=null;
		KP=PP=EV=MDTP=KPDTP=SSTP=0;
	}
	
	public String toString() {
		return name+ " "+PP+ " "+KP+ " "+EV+ " "+MDTP+ " "+KPDTP+ " "+SSTP+ "\n ";
	}
}
