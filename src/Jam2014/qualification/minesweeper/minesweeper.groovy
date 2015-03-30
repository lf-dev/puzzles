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

    println "${R} ${C} ${M}"
    def field = fill(R, C, M)


//    println field

    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            print field[r][c]
        }
        println ""
    }
//    boolean solve = canSolve(R,C,field)
//
//    w.writeLine("Case #${t+1}:")
//
//    if(!solve) {
//        w.writeLine("Impossible")
//    }else {
//
//        boolean click = false
//        for(int r=0; r<R; r++){
//            for(int c=0; c<C; c++){
//
//                int cell = field[r][c]
//
//                if(cell == -1){
//                    w.write("*")
//                }else{
//                    if(cell == 0 && !click) {
//                        w.write("c")
//                        click = true
//                    }else {
//                        w.write(".")
//                    }
//                }
//            }
//            w.writeLine("")
//        }
//    }

}
w.close()

class Cell {
    boolean mine
    boolean visited = false
    int count = 0
    int r, c
}

def fill(int R, int C, int M) {

    def field = new Cell[R][C]

    int mineNum = 0

    if(R < C) {
        for(int c=0; c<C; c++){
            for(int r=0; r<R; r++){

                if(mineNum < M) {
                    field[r][c] = new Cell(mine: true, r: r, c: c)
                    mineNum++
                }else {
                    field[r][c] = new Cell(mine: false, r: r, c: c)
                }
            }
        }
    } else {
        for(int c=0; c<C; c++){
            for(int r=0; r<R; r++){

                if(mineNum < M) {
                    field[r][c] = new Cell(mine: true, r: r, c: c)
                    mineNum++
                }else {
                    field[r][c] = new Cell(mine: false, r: r, c: c)
                }
            }
        }
    }

    return field
}

def solve(int R, int C, def field) {

    for(int r=0; r<R; r++){
        for(int c=0; c<C; c++){

            calcMines(field, R, C, field[r][c])

            //verifica se todos os nao mina foram visitados

            //caso sim
            //return solucao

            //caso nao
            //limpa visited, count para proxima chamada de calcMines
            //

        }
    }

    return false
}

def calcMines(def field, int R, int C, Cell cell) {

    if(cell.mine) {
        return
    }

    if(!cell.visited) {
        cell.visited = true

        if (cell.c + 1 < C) {
            //DIREITA
            calcMines(field, R, C, field[cell.r][cell.c + 1])
        }
        if (cell.c + 1 < C && cell.r + 1 < R) {
            //DIAGONAL DIREITA BAIXO
            calcMines(field, R, C, field[cell.r + 1][cell.c + 1])
        }
        if (cell.r + 1 < R) {
            //BAIXO
            calcMines(field, R, C, field[cell.r + 1][cell.c])
        }
        if (cell.c - 1 >= 0 && cell.r + 1 < R) {
            //DIAGONAL ESQUERDA BAIXO
            calcMines(field, R, C, field[cell.r + 1][cell.c - 1])
        }
        if (cell.c - 1 >= 0) {
            //ESQUERDA
            calcMines(field, R, C, field[cell.r][cell.c - 1])
        }
        if (cell.c - 1 >= 0 && cell.r - 1 >= 0) {
            //DIAGONAL ESQUERDA TOPO
            calcMines(field, R, C, field[cell.r -1 ][cell.c - 1])
        }
        if (cell.r - 1 >= 0) {
            //TOPO
            calcMines(field, R, C, field[cell.r - 1][cell.c])
        }
        if (cell.c + 1 < C && cell.r - 1 >= 0) {
            //DIAGONAL DIREITA TOPO
            calcMines(field, R, C, field[cell.r -1 ][cell.c + 1])
        }
    }
}