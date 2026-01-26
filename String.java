import java.util.*;

/**
 * 문자열 처리 관련 자주 사용되는 메서드와 알고리즘
 */
public class String {

    // 1. 문자열 역순으로 뒤집기
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // 2. 팰린드롬 확인
    public static boolean isPalindrome(String s) {
        String clean = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }

    // 3. 중복된 문자 제거 (첫 등장 순서 유지)
    public static String removeDuplicates(String s) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return String.join("", set.stream().map(String::valueOf).toArray(String[]::new));
    }

    // 4. 문자 빈도수
    public static Map<Character, Integer> charFrequency(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }

    // 5. 아나그램 확인 (문자 재배열로 같은 단어 만들 수 있는지)
    public static boolean isAnagram(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    // 6. 단어 뒤집기
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) result.append(" ");
        }
        return result.toString();
    }

    // 7. 가장 긴 공통 접두사 찾기
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // 8. 부분 문자열 찾기 (KMP 알고리즘보다 간단한 방법)
    public static int findSubstring(String text, String pattern) {
        return text.indexOf(pattern);
    }

    // 9. 압축된 문자열 펼치기 (예: "2a3b" -> "aabbb")
    public static String decompressString(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                char c = s.charAt(i++);
                for (int j = 0; j < num; j++) {
                    result.append(c);
                }
            } else {
                i++;
            }
        }
        return result.toString();
    }

    // 10. 문자열 분할
    public static List<String> splitString(String s, String delimiter) {
        return Arrays.asList(s.split(delimiter));
    }

    // 11. 첫 번째 반복되지 않은 문자 찾기
    public static Character firstUniqueChar(String s) {
        Map<Character, Integer> freq = charFrequency(s);
        for (char c : s.toCharArray()) {
            if (freq.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    // 12. 회전된 문자열인지 확인
    public static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return (s1 + s1).contains(s2);
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 문자열 메서드 테스트 =====\n");

        // 1. 문자열 역순
        System.out.println("1. 문자열 역순");
        System.out.println("원본: hello");
        System.out.println("결과: " + reverseString("hello"));
        System.out.println();

        // 2. 팰린드롬 확인
        System.out.println("2. 팰린드롬 확인");
        System.out.println("'A man, a plan, a canal: Panama' : " + isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("'hello' : " + isPalindrome("hello"));
        System.out.println();

        // 3. 중복 제거
        System.out.println("3. 중복 제거");
        System.out.println("'aabbcc' : " + removeDuplicates("aabbcc"));
        System.out.println();

        // 4. 문자 빈도수
        System.out.println("4. 문자 빈도수");
        System.out.println("'banana' : " + charFrequency("banana"));
        System.out.println();

        // 5. 아나그램
        System.out.println("5. 아나그램 확인");
        System.out.println("'listen' 과 'silent' : " + isAnagram("listen", "silent"));
        System.out.println();

        // 6. 단어 뒤집기
        System.out.println("6. 단어 뒤집기");
        System.out.println("'Hello World Java' : " + reverseWords("Hello World Java"));
        System.out.println();

        // 7. 가장 긴 공통 접두사
        System.out.println("7. 가장 긴 공통 접두사");
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(Arrays.toString(strs) + " : " + longestCommonPrefix(strs));
        System.out.println();

        // 8. 부분 문자열 찾기
        System.out.println("8. 부분 문자열 찾기");
        System.out.println("'world' in 'hello world' : " + findSubstring("hello world", "world"));
        System.out.println();

        // 9. 압축된 문자열 펼치기
        System.out.println("9. 압축된 문자열 펼치기");
        System.out.println("'2a3b1c' : " + decompressString("2a3b1c"));
        System.out.println();

        // 10. 문자열 분할
        System.out.println("10. 문자열 분할");
        System.out.println("'a,b,c' : " + splitString("a,b,c", ","));
        System.out.println();

        // 11. 첫 반복되지 않은 문자
        System.out.println("11. 첫 반복되지 않은 문자");
        System.out.println("'leetcode' : " + firstUniqueChar("leetcode"));
        System.out.println();

        // 12. 회전된 문자열
        System.out.println("12. 회전된 문자열 확인");
        System.out.println("'waterbottle' 과 'erbottlewat' : " + isRotation("waterbottle", "erbottlewat"));
    }
}
