import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringUtils {

    public static boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
    
    public static String full2Half(String s){
        return KeywordProcessor.process(Normalizer.normalize(s,Normalizer.Form.NFKC));
    }
    
    public static String normalSpace(String s){
        return s.replaceAll("[\\p{Zs}]"," ");
    }
    
    public static String cleanPunctuation(String s){
        return s.replaceAll("[\\pP\\pS\\pM\\pC&&[^.-]]"," "); // 除了 .- 以外的符号，用交集排除
    }
    
    public static String cleanNumbre(String s){
        return s.replaceAll("[\\d]"," ");
    }
    
    public static String ajoutSpace(String s){
        return s.replaceAll("[0-9]+", " $0 "); //数字组合的前后加空格比如 s56s 变为 s 56 s
    }
    
    
    

}
