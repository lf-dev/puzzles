package facebook;

/**
 * Created by upom on 19/06/2018.
 */
public class EditDistance {

    public static void main(String[] args) {

        test("cat", "dog",false);
        test("cat", "cats",true);
        test("cat", "cut",true);
        test("cat", "cast",true);
        test("cat", "at",true);
        test("cat", "act",false);
        test("aaa", "aaabb",false);
    }

    private static void test(String s1, String s2, boolean expected) {

        if(isOneEditAway(s1, s2) != expected) {
            System.out.println(s1 + " " + s2 + " presented unexpected result" );
        }else {
            System.out.println(s1 + " " + s2 + " ok");
        }
    }

    public static boolean isOneEditAway(String s1, String s2) {

        if(Math.abs(s1.length() - s2.length()) > 1){
            return false;
        }

        if(s1.length() == s2.length()) {
            return oneEditSameSize(s1, s2);
        }

        String big = s1.length() > s2.length() ? s1 : s2;
        String small = s1.length() < s2.length() ? s1 : s2;

        return oneRemoveAway(big, small);

    }

    private static boolean oneRemoveAway(String big, String small) {

        int bigIndex = 0;
        int smallIndex = 0;
        boolean hasOneEdit = false;

        while (bigIndex < big.length()) {

            if(smallIndex == small.length() || big.charAt(bigIndex) != small.charAt(smallIndex)) {

                if(hasOneEdit) {
                    return false;
                }

                hasOneEdit = true;

                bigIndex++;
            }else {
                bigIndex++;
                smallIndex++;
            }

        }

        return hasOneEdit;

    }


    private static boolean oneEditSameSize(String s1, String s2) {

        int edit = 0;
        for(int i=0; i<s1.length(); i++) {

            if(s1.charAt(i) != s2.charAt(i)){
                edit++;
            }
        }

        return edit <= 1;
    }
}
