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
    
    public static String lang(String keyword){
        if(Pattern.compile("[\\p{InARABIC}" +
                "\\p{InARABIC_EXTENDED_A}" +
                "\\p{InARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS}" +
                "\\p{InARABIC_PRESENTATION_FORMS_A}" +
                "\\p{InARABIC_PRESENTATION_FORMS_B}" +
                "\\p{InARABIC_SUPPLEMENT}" +
                "]+").matcher(keyword).find()){
            return "ar";
        }
        else if(Pattern.compile("[\\p{InHANGUL_COMPATIBILITY_JAMO}" +
                "\\p{InHANGUL_JAMO_EXTENDED_A}" +
                "\\p{InHANGUL_JAMO_EXTENDED_B}" +
                "\\p{InHANGUL_JAMO}" +
                "\\p{InHANGUL_SYLLABLES}]+").matcher(keyword).find()){
            return "kr";
        }
        else if(Pattern.compile("\\p{InTHAI}").matcher(keyword).find()){
            return "th";
        }
        else if(Pattern.compile("[\\p{InCYRILLIC}" +
                "\\p{InCYRILLIC_SUPPLEMENTARY}" +
                "\\p{InCYRILLIC_EXTENDED_A}" +
                "\\p{InCYRILLIC_EXTENDED_B}]+").matcher(keyword).find()){
            return "ru";
        }
        else if(Pattern.compile("[\\p{InHIRAGANA}" +
                "\\p{InKATAKANA}" +
                "\\p{InKATAKANA_PHONETIC_EXTENSIONS}" +
                "\\p{InKanbun}" +
                "\\p{InCJK_COMPATIBILITY}" +
                "\\p{InCJK_COMPATIBILITY_FORMS}" +
                "\\p{InCJK_COMPATIBILITY_IDEOGRAPHS}" +
                "\\p{InCJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT}" +
                "\\p{InCJK_RADICALS_SUPPLEMENT}" +
                "\\p{InCJK_STROKES}" +
                "\\p{InCJK_SYMBOLS_AND_PUNCTUATION}" +
                "\\p{InCJK_UNIFIED_IDEOGRAPHS}" +
                "\\p{InCJK_UNIFIED_IDEOGRAPHS_EXTENSION_A}" +
                "\\p{InCJK_UNIFIED_IDEOGRAPHS_EXTENSION_B}" +
                "\\p{InCJK_UNIFIED_IDEOGRAPHS_EXTENSION_C}" +
                "\\p{InCJK_UNIFIED_IDEOGRAPHS_EXTENSION_D}" +
                "\\p{InENCLOSED_CJK_LETTERS_AND_MONTHS}]+"
        ).matcher(keyword).find()){
            return "cn&jp";
        }
        else if(Pattern.compile("[\\p{InDEVANAGARI}" +
                "\\p{InDEVANAGARI_EXTENDED}" +
                "]+").matcher(keyword).find()){
            return "hn";
        }
        return "";
    }
    
    
    

}
