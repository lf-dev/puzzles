package Jam2014.qualification.minesweeper

MINE = -1

def filename = "C-small-practice"
//def filename = "C-large-practice"
//def filename = "sample"

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

    int offsetTop = 0
    while(2 * C < M){
        M = M - C
        R--
        offsetTop++
    }

    println "${R} ${C} ${M}"

    def field = new Cell[R][C]

    w.writeLine("Case #${t+1}:")
    if(brute(field, M, 0 , 0, R, C, M)){
        //field possui resposta

        //print offsetTop
        offsetTop.times {
            w.writeLine("*"*C)
        }

        for(int r=0; r<R; r++){

            for(int c=0; c<C; c++) {

                Cell cell = field[r][c]

                if(cell.click){
                    w.write "c"
                }else if(cell.mine){
                    w.write "*"
                }else{
                    w.write "."
                }
            }
            w.writeLine("")
        }
    }
    else {
        w.writeLine("Impossible")
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

boolean prune(def field, int r, int C, int M) {

    if(M<=1){
        return false
    }

    String line = ":"
    for(int i=0; i<C; i++){
        line += field[r][i].mine ? "*":"."
    }
    line += ":"

    if(line.contains(".*.") || line.contains("*.*") || line.contains(":.*")){
        return true
    }

    return false
}

boolean brute(def field, int m, int r, int c, int R, int C, int M) {

    if (c == C) {
        c = 0
        r++

        if(prune(field, r-1, C, M)){
            return false
        }
    }

    //chegou ao fim da enumeracao
    if(r == R) {
        if(m==0) {

            boolean sol = resolve(R, C, M, field)

            return sol
        }else {
            return false
        }
    }

    if (m > 0) {

        field[r][c] = new Cell(mine: true, r: r, c: c, maxRow: R, maxCol: C, field: field)
        if(brute(field, m - 1, r, c + 1, R, C, M)){
            return true
        }
    }

    field[r][c] = new Cell(mine: false, r: r, c: c, maxRow: R, maxCol: C, field: field)
    if(brute(field, m, r, c + 1, R, C, M)){
        return true
    }
}


def countMines(int R, int C, def field) {
    for (int r = 0; r < R; r++) {
        for(int c=0; c<C; c++) {
            field[r][c].count = field[r][c].vizinhos().count { it.mine }
        }
    }

}

boolean resolve(int R, int C, int M, def field) {

    int totalCells = R * C

    countMines(R, C, field)

    for(int r=0; r<R; r++){
        for(int c=0; c<C; c++) {

            field[r][c].click = true
            open(field[r][c])

            //verifica se todos os nao mina foram visitados
            int abertos = countAbertosNaoMina(R, C, field)

            if ((totalCells - (M + abertos)) == 0) {
                return true
            } else {
                limpa(R, C, field)
            }
        }
    }

    return false
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