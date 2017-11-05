
public class packet {

	int packet;
	int failStatus;
	
    // this class keeps track of the packet received and the number of the packet.
	// So if the packet fails after very nth value, then if failStatus = n then that 
	// packet must fail. 
	
	packet() {
		packet=-1;
		failStatus=-1;
	}
	
	public String toString() {
		
		return "Packet : "+packet+" failStatus : "+ failStatus;
	}
}
