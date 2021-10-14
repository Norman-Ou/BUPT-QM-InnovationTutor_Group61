package smarthome01;
import java.security.MessageDigest;
 
/**
 * MD5�������ܽ���
 */
public class MD5Tools {
    /***
     * MD5���� ����32λmd5��
     */
    public static String stringToMD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
 
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
 
    /**
     * ���ܽ����㷨 ִ��һ�μ��ܣ����ν���
     */
    public static String convertMD5(String inStr){
 
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
 
    }
 
    // ����������
    public static void main(String[] args) {
        String s = new String("123123");
        System.out.println("ԭʼ��" + s);
        System.out.println("MD5��" + stringToMD5(s));
        System.out.println("���ܵģ�" + convertMD5(s));
        System.out.println("���ܵģ�" + convertMD5(convertMD5(s)));
 
    }
}