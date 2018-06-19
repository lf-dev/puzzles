package facebook;


public class SpiralMatrix {


    static int i, j;

    public static void main(String[] args) {

        int n = 3;
        int[][] N = new int[n][n];

        int max = n*n;
        int curr = 1;

        i = 0;
        j = 0;

        //u, d, l ,r
        char dir = 'r';

        while(curr <= max) {

            N[i][j] = curr;
            curr++;
            dir = navigate(dir, N);
        }

        for(int x=0; x<n; x++) {
            System.out.println();
            for(int y=0; y<n; y++) {

                System.out.print("[" + N[x][y] + "] ");

            }
        }

    }

    private static char navigate(char dir, int[][] N) {

        if(dir == 'r') {

            if(isAvaiable(i, j+1, N)) {
                j++;
                return 'r';
            }else {
                i++;
                return 'd';
            }
        }
        else if(dir == 'd') {

            if(isAvaiable(i+1, j, N)) {
                i++;
                return 'd';
            }else {
                j--;
                return 'l';
            }
        }
        else if(dir == 'l') {

            if(isAvaiable(i, j-1, N)) {
                j--;
                return 'l';
            }else {
                i--;
                return 'u';
            }
        }
        else{
            //u
            if(isAvaiable(i-1, j, N)) {
                i--;
                return 'u';
            }else {
                j++;
                return 'r';
            }
        }
    }

    private static boolean isAvaiable(int i, int j, int[][] N) {

        if( i < 0 || j< 0 || i >= N.length || j >= N.length || N[i][j] != 0) {
            return false;
        }

        return true;
    }



}
