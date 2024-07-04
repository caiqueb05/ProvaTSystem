package Ex08;

public class VerificaString {
    public static boolean verificarFinalString(String str1,  String str2) {
        if (str1.length() < 2 || str2.length() < 2) {
            return false;
        }
        String inicioStr2 = str2.substring(0, 2);
        return str1.endsWith(inicioStr2);
    }
}
