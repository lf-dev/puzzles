package Jam2014.qualification.minesweeper

MINE = -1

def filename = "sample"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()
println T

for(int t = 0 ; t < T ; t++ ) {

    int R = sc.nextInt()
    int C = sc.nextInt()
    int M = sc.nextInt()

    def field = fill(R, C, M)

    println "${R} ${C} ${M}"
    println field
    boolean solve = canSolve(R,C,field)

    w.writeLine("Case #${t+1}:")

    if(!solve) {
        w.writeLine("Impossible")
    }else {

        boolean click = false
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){

                int cell = field[r][c]

                if(cell == -1){
                    w.write("*")
                }else{
                    if(cell == 0 && !click) {
                        w.write("c")
                        click = true
                    }else {
                        w.write(".")
                    }
                }
            }
            w.writeLine("")
        }
    }

}
w.close()

def fill(int R, int C, int M) {

    def field = new String[R][C]

    int mineNum = 0

    if(C < R) {
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){

                if(mineNum < M) {
                    field[r][c] = "*"
                    mineNum++
                }else {
                    field[r][c] = "."
                }
            }
        }
    } else {
        for(int c=0; c<C; c++){
            for(int r=0; r<R; r++){

                if(mineNum < M) {
                    field[r][c] = "*"
                    mineNum++
                }else {
                    field[r][c] = "."
                }
            }
        }
    }

    return field
}

def solve(int R, int C, def field) {

    for(int r=0; r<R; r++){
        for(int c=0; c<C; c++){

            int cell = field[r][c]
            if(cell == -1) {

                if(c+1<C && field[r][c+1] != -1){
                    //DIREITA
                    field[r][c+1] += 1
                }
                if(c+1<C && r+1<R && field[r+1][c+1] != -1){
                    //DIAGONAL
                    field[r+1][c+1] += 1
                }
                if(r+1<R && field[r+1][c] != -1){
                    //BAIXO
                    field[r+1][c] += 1
                }
            }
        }
    }

    return false
}