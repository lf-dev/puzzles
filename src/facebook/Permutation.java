package facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by upom on 19/06/2018.
 */
public class Permutation {


    public static void main(String[] args) {

        //Find Permutations of s within b

        String s = "abbc";
        String b = "babcabbacaabcbabcacbb";

        Map<Character, Integer> sMap = buildMap(s, 0, s.length());

        int total = 0;
        for(int i=0; i + s.length() < b.length(); i++) {

            Map<Character, Integer> bMap = buildMap(b, i, i + s.length());

            if(sMap.equals(bMap)) {
                System.out.println("i: " + i);
                total++;
            }
        }

        System.out.println(total);

    }

    private static Map<Character, Integer> buildMap(String str, int init, int end) {

        Map<Character, Integer> map = new HashMap<>();
        for(int i=init; i<end; i++) {

            char key = str.charAt(i);
            if(!map.containsKey(key)) {
                map.put(key, 0);
            }
            map.put(key, map.get(key) + 1);
        }

        return map;

    }

}
