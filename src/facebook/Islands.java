package facebook;

public class Islands {

    static int[][] map = new int[][]{
            {0,1,0,0,0,0},
            {0,0,0,0,1,0},
            {0,0,0,0,1,0},
            {0,1,1,0,1,0},
            {0,0,0,0,1,0}
    };

    static int[][] paintedMap;

    public static void main(String[] args) {

        paintedMap = new int[map.length][map[0].length];

        int color = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {


                if(!isVisited(i,j) && isLand(i, j)){
                    color++;
                    paint(i, j, color);
                }
            }
        }

        System.out.println(color);

    }

    public static void paint(int i, int j, int color) {
        if(!isValid(i, j) || isVisited(i,j)){
            return;
        }

        if(isLand(i,j)) {
            paintedMap[i][j] = color;

            paint(i - 1, j - 1, color);
            paint(i - 1, j, color);
            paint(i - 1, j + 1, color);
            paint(i, j - 1, color);
            paint(i, j + 1, color);
            paint(i + 1, j - 1, color);
            paint(i + 1, j, color);
            paint(i + 1, j + 1, color);
        }
    }

    private static boolean isValid(int i, int j) {
        return i > 0 && i < map.length && j > 0 && j < map[0].length;
    }

    private static boolean isVisited(int i, int j) {
        return paintedMap[i][j] != 0;
    }

    private static boolean isLand(int i, int j) {
        return map[i][j] == 1;
    }






}
