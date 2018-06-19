package facebook;

/**
 * Created by upom on 19/06/2018.
 */
public class LookAndSay {

    public static void main(String[] args) {

        //never grows
        //String sequence = "22";
        String sequence = "1";
        int N = 50;

        for(int n=0; n<N; n++) {

            System.out.println(sequence.length());
            sequence = lookAndSay(sequence);
        }
    }

    private static String lookAndSay(String sequence) {

        String newSequence = "";
        char curr = sequence.charAt(0);
        int count = 1;

        for(int i=1; i<sequence.length(); i++) {

            if(curr == sequence.charAt(i)){
                count++;
            }else {
                newSequence += count + "" + curr;

                curr = sequence.charAt(i);
                count = 1;
            }
        }

        newSequence += count + "" + curr;

        return newSequence;

    }




}
