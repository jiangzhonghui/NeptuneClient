package neptune.client.util;

public class Utils {
	
	public static byte[] getMagic(){
		return new byte[] {
				(byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00,
				(byte)0xfe, (byte)0xfe, (byte)0xfe, (byte)0xfe,
				(byte)0xfd, (byte)0xfd, (byte)0xfd, (byte)0xfd,
				(byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78
		};
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

}
