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
    def field = fillHorizontal(R, C, M)
    def sol = solve(R, C, M, field)

    if(sol == null){
        field = fillVertical(R, C, M)
        sol = solve(R, C, M, field)
    }

    if(sol == null){
        println "Impossible"

    }else {
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++) {

                Cell cell = field[r][c]

                if(cell.click){
                    print "c"
                }else if(cell.mine){
                    print "*"
                }else{
                    print "."
                }
            }
            println ""
        }
    }

}
w.close()

class Cell {
    boolean mine
    boolean click = false
    boolean visited = false
    int count = 0
    int r, maxRow, c, maxCol
    def field

    String toString(){
        if(mine) {
            return "*"
        }else{
            return "${count}"
        }
    }

    List vizinhos() {

        List v = []
        if (c + 1 < maxCol) {
            //DIREITA
            v << field[r][c + 1]
        }
        if (c + 1 < maxCol && r + 1 < maxRow) {
            //DIAGONAL DIREITA BAIXO
            v << field[r + 1][c + 1]
        }
        if (r + 1 < maxRow) {
            //BAIXO
            v << field[r + 1][c]
        }
        if (c - 1 >= 0 && r + 1 < maxRow) {
            //DIAGONAL ESQUERDA BAIXO
            v << field[r + 1][c - 1]
        }
        if (c - 1 >= 0) {
            //ESQUERDA
            v << field[r][c - 1]
        }
        if (c - 1 >= 0 && r - 1 >= 0) {
            //DIAGONAL ESQUERDA TOPO
            v << field[r -1 ][c - 1]
        }
        if (r - 1 >= 0) {
            //TOPO
            v << field[r - 1][c]
        }
        if (c + 1 < maxCol && r - 1 >= 0) {
            //DIAGONAL DIREITA TOPO
            v << field[r -1 ][c + 1]
        }

        return v
    }
}

def fillHorizontal(int R, int C, int M) {

    def field = new Cell[R][C]

    int mineNum = 0

    for(int r=0; r<R; r++){
        for(int c=0; c<C; c++){

            if(mineNum < M) {
                field[r][c] = new Cell(mine: true, r: r, c: c, maxRow: R, maxCol: C, field: field)
                mineNum++
            }else {
                field[r][c] = new Cell(mine: false, r: r, c: c, maxRow: R, maxCol: C, field: field)
            }
        }
    }

    countMines(R, C, field)

    return field
}

def fillVertical(int R, int C, int M) {

    def field = new Cell[R][C]

    int mineNum = 0

    for(int c=0; c<C; c++){
        for(int r=0; r<R; r++){

            if(mineNum < M) {
                field[r][c] = new Cell(mine: true, r: r, c: c, maxRow: R, maxCol: C, field: field)
                mineNum++
            }else {
                field[r][c] = new Cell(mine: false, r: r, c: c, maxRow: R, maxCol: C, field: field)
            }
        }
    }

    countMines(R, C, field)

    return field
}

def countMines(int R, int C, def field) {
    for (int r = 0; r < R; r++) {
        for(int c=0; c<C; c++) {
            field[r][c].count = field[r][c].vizinhos().count { it.mine }
        }
    }

}

def solve(int R, int C, int M, def field) {

    int totalCells = R * C

    for(int r=0; r<R; r++){
        for(int c=0; c<C; c++) {

            field[r][c].click = true
            open(field[r][c])

            //verifica se todos os nao mina foram visitados
            int abertos = countAbertosNaoMina(R, C, field)

            if ((totalCells - (M + abertos)) == 0) {
                return field
            } else {
                limpa(R, C, field)
            }
        }
    }

    return null
}

def open(Cell cell) {

    if(cell.visited){
        return
    }

    cell.visited = true
    if(cell.mine || cell.count > 0) {
        return
    }

    //cell mine == 0 e nao visitado
    cell.vizinhos().each { v ->
        open(v)
    }
}

int countAbertosNaoMina(int R, int C, def field) {

    int total = 0
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {

            if(field[r][c].visited && !field[r][c].mine){
                total++
            }
        }
    }
    return total
}

void limpa(int R, int C, def field) {
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            field[r][c].visited = false
            field[r][c].click = false
        }
    }
}

