import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@DisplayName("String 알고리즘 테스트")
public class StringTest {

    @Test
    @DisplayName("문자열 역순으로 뒤집기")
    void testReverseString() {
        String input = "Hello";
        String expected = "olleH";
        String result = String.reverseString(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("문자열 역순 - 빈 문자열")
    void testReverseStringEmpty() {
        String input = "";
        String result = String.reverseString(input);
        
        assertEquals("", result);
    }

    @Test
    @DisplayName("팰린드롬 확인 - 팰린드롬 문자열")
    void testIsPalindrome() {
        assertTrue(String.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(String.isPalindrome("race a car"));
    }

    @Test
    @DisplayName("팰린드롬 확인 - 팰린드롬 아님")
    void testIsNotPalindrome() {
        assertFalse(String.isPalindrome("hello"));
        assertFalse(String.isPalindrome("abc"));
    }

    @Test
    @DisplayName("팰린드롬 확인 - 한 글자")
    void testIsPalindromeSingleChar() {
        assertTrue(String.isPalindrome("a"));
    }

    @Test
    @DisplayName("중복된 문자 제거 - 첫 등장 순서 유지")
    void testRemoveDuplicates() {
        String input = "programming";
        String result = String.removeDuplicates(input);
        
        assertEquals("progamin", result);
    }

    @Test
    @DisplayName("중복된 문자 제거 - 모두 다른 문자")
    void testRemoveDuplicatesAllUnique() {
        String input = "abc";
        String result = String.removeDuplicates(input);
        
        assertEquals("abc", result);
    }

    @Test
    @DisplayName("문자 빈도수 계산")
    void testCharFrequency() {
        String input = "hello";
        Map<Character, Integer> result = String.charFrequency(input);
        
        assertEquals(1, result.get('h'));
        assertEquals(1, result.get('e'));
        assertEquals(2, result.get('l'));
        assertEquals(1, result.get('o'));
    }

    @Test
    @DisplayName("문자 빈도수 - 반복되는 문자")
    void testCharFrequencyRepeated() {
        String input = "aabbcc";
        Map<Character, Integer> result = String.charFrequency(input);
        
        assertEquals(2, result.get('a'));
        assertEquals(2, result.get('b'));
        assertEquals(2, result.get('c'));
    }

    @Test
    @DisplayName("아나그램 확인 - 아나그램")
    void testIsAnagram() {
        assertTrue(String.isAnagram("listen", "silent"));
        assertTrue(String.isAnagram("evil", "vile"));
    }

    @Test
    @DisplayName("아나그램 확인 - 아나그램 아님")
    void testIsNotAnagram() {
        assertFalse(String.isAnagram("hello", "world"));
        assertFalse(String.isAnagram("python", "java"));
    }

    @Test
    @DisplayName("아나그램 - 대소문자 구분")
    void testIsAnagramCaseSensitive() {
        assertFalse(String.isAnagram("Listen", "Silent"));
    }

    @Test
    @DisplayName("단어 뒤집기")
    void testReverseWords() {
        String input = "Hello World Java";
        String expected = "Java World Hello";
        String result = String.reverseWords(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("단어 뒤집기 - 여러 공백")
    void testReverseWordsMultipleSpaces() {
        String input = "  Hello   World  Java  ";
        String result = String.reverseWords(input);
        
        assertEquals("Java World Hello", result);
    }

    @Test
    @DisplayName("단어 뒤집기 - 한 단어")
    void testReverseWordsSingleWord() {
        String input = "Hello";
        String result = String.reverseWords(input);
        
        assertEquals("Hello", result);
    }

    @Test
    @DisplayName("문자열 자릿수 합계")
    void testDigitSum() {
        String input = "123abc456";
        int result = String.digitSum(input);
        
        assertEquals(21, result); // 1+2+3+4+5+6
    }

    @Test
    @DisplayName("문자열 자릿수 합계 - 숫자 없음")
    void testDigitSumNoDigits() {
        String input = "abc";
        int result = String.digitSum(input);
        
        assertEquals(0, result);
    }

    @Test
    @DisplayName("문자열 일부 포함 여부 - 있음")
    void testIsSubstring() {
        assertTrue(String.isSubstring("hello world", "world"));
        assertTrue(String.isSubstring("programming", "gram"));
    }

    @Test
    @DisplayName("문자열 일부 포함 여부 - 없음")
    void testIsSubstringNotFound() {
        assertFalse(String.isSubstring("hello", "xyz"));
    }

    @Test
    @DisplayName("가장 긴 공통 접두사")
    void testLongestCommonPrefix() {
        String[] strs = {"flower", "flow", "flight"};
        String result = String.longestCommonPrefix(strs);
        
        assertEquals("fl", result);
    }

    @Test
    @DisplayName("가장 긴 공통 접두사 - 공통 접두사 없음")
    void testLongestCommonPrefixEmpty() {
        String[] strs = {"dog", "racecar", "car"};
        String result = String.longestCommonPrefix(strs);
        
        assertEquals("", result);
    }

    @Test
    @DisplayName("빈 문자열 처리")
    void testEmptyString() {
        String result = String.reverseString("");
        assertEquals("", result);
    }
}
