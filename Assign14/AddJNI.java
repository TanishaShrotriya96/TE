public class AddJNI
{
static {
 System.load("/home/ccoew/3476/mynativelib.so");
//System.load("/home/ccoew/myjava/cal.so");
		// Load native library at runtime
		// cal.dll (Windows) or libcal.so (Unix)
}

		// Declare a native method add() that receives nothing and returns void
private native int add(int n1,int n2);

		// Test Driver

public static void main(String[] args)
{		// invoke the native method
System.out.println("Addition is=" +new AddJNI().add(100,20)); }}

