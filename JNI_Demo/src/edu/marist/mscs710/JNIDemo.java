package edu.marist.mscs710;

public class JNIDemo {

	/**
	 * Native method signature
	 *  
	 * @return 0 on success
	 */
	private static native int jnidemo();
	
	public static void main(String[] args)
	{
		System.out.println("Loading native library");
		System.loadLibrary("jnidemo");
		
		System.out.println("Calling native method jnidemo()");
		
		int rc = jnidemo();
		
		System.out.println("Native method jnidemo() returned; rc = " + rc);
		
		return;
	}
	
}
