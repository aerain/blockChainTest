import java.security.MessageDigest;

public class SHAHashingExample
{
     public static void hashing(String value) throws Exception {

        for (int i = 0; i < 100; i++) { 
            String password = value + Integer.toString(i); 
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int j = 0; j < byteData.length; j++) {
                sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Hex format ["+i+"] : " + sb.toString());
        }
    }


    public static void main(String[] args)throws Exception
    {
    	if (args.length == 0) {
            System.err.println("ex) java SHAHashingExample good");
            System.exit(1);
        }

        String password = args[0];

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Hex format : " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();

    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);

    	}
    	System.out.println("Hex format : " + hexString.toString());

        //hashing(password);
    }
}




// import java.nio.charset.StandardCharsets;
// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;

// public class SHA256 {

// 	public static String generateHash(String value) {
// 		String hash = null;
// 		try {
// 			MessageDigest md = MessageDigest.getInstance("SHA-256");
// 			byte[] bytes = md.digest(value.getBytes(StandardCharsets.UTF_8));
// 			StringBuilder sb = new StringBuilder();
// 			for (int i = 0; i < bytes.length; i++) {
// 				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
// 			}
// 			hash = sb.toString();
// 		} catch (NoSuchAlgorithmException e) {
// 			e.printStackTrace();
// 		}
// 		return hash;
// 	}
// }