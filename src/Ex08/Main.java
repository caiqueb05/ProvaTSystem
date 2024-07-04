package Ex08;

public class Main {
    public static void main(String[] args) {
        String str1 = "programação";
        String str2 = "ão";
        System.out.println(VerificaString.verificarFinalString(str1, str2));

        System.out.println(VerificaString.verificarFinalString("olá", "mundo"));
        System.out.println(VerificaString.verificarFinalString("", "abc"));
    }
}
