package MyLibrary.lib;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISBN {
    public static String toString(String isbn){
        if(checkIsbn(isbn)) return isbn.replace("-","");
        else return "";
    }

    public static boolean checkIsbn(String isbn)
    {
        if(isbn.indexOf("--")!=-1) return false;
        if(countStr(isbn,'-')==3 ){
            String nisbn = isbn.replace("-","");
            if(nisbn.length()==10) {
                if (isNumeric(nisbn.substring(0, 10)) || (nisbn.charAt(9) == 'X' && isNumeric(nisbn.substring(0, 9)))) {
                    int all = 0;
                    if (nisbn.charAt(9) == 'X') {
                        all = 10;
                        for (int i = 0; i < 9; i++) {
                            all += (10 - i) * ((int) nisbn.charAt(i)-(int)'0');
                        }
                    } else {
                        for (int i = 0; i < 10; i++) {
                            all += (10 - i) * (int) nisbn.charAt(i);
                        }
                    }
                    if (all % 11 == 0) return true;
                }
            }
        }
        else if(countStr(isbn, '-')==4){
            String nisbn = isbn.replace("-","");
            if (nisbn.length()==13) {
                if(nisbn.substring(0,3).equals("978") || nisbn.substring(0,3).equals("979")){
                    int all = 0;
                    for(int i=0;i<13;i++){
                        if(i%2==0) all += ((int)nisbn.charAt(i) - (int)'0');
                        else all += 3*((int)nisbn.charAt(i)-(int)'0');
                    }
                    if(all%10==0) return true;
                }
            }
        }
        System.out.println("ISBN不合法！");
        return false;
    }

    public static int countStr(String str, char c){
        int num = 0;
        for(int i = 0; i< str.length();i++)
            if(c == str.charAt(i))
                num++;
        return num;
    }

    public static boolean isNumeric(String str){
        Pattern p = Pattern.compile("[0-9]*");
        Matcher isNum = p.matcher(str);
        if(!((Matcher) isNum).matches()){
            return false;
        }
        return true;
    }
}

