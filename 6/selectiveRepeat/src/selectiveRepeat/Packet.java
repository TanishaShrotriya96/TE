package selectiveRepeat;

public class Packet {

	int packet;
	int failStatus;
	int ack;
	
	Packet() {
		packet=-1;
		failStatus=0;
		ack=-1;
	}
	
	public String toString() {
		
		return "Packet:"+packet+" |failStatus:"+ failStatus +" |ack:"+ack;
	}
}
