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
        Map<Character, Integer> bMap = buildMap(b, 0, s.length());

        int total = 0;
        for(int i=0; i + s.length() < b.length(); i++) {

            if(sMap.equals(bMap)) {
                System.out.println("i: " + i);
                total++;
            }

            decrementOrRemove(bMap, b.charAt(i));

            if(i + s.length() < b.length()) {
                addOrIncrement(bMap, b.charAt(i+s.length()));
            }
        }

        System.out.println(total);

    }

    private static void decrementOrRemove(Map<Character, Integer> map, char key) {

        int value = map.get(key);
        if(value == 1) {
            map.remove(key);
        }else {
            map.put(key, value - 1);
        }
    }

    private static void addOrIncrement(Map<Character, Integer> map, char key) {

        if(!map.containsKey(key)) {
            map.put(key, 0);
        }
        map.put(key, map.get(key) + 1);
    }

    private static Map<Character, Integer> buildMap(String str, int init, int end) {

        Map<Character, Integer> map = new HashMap<>();
        for(int i=init; i<end; i++) {

            char key = str.charAt(i);
            addOrIncrement(map, key);
        }

        return map;

    }

}
