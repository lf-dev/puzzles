package Jam2010.round1a.rotate

def filename = "A-large-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

for(int t = 0 ; t < T ; t++ ) {

    int N = sc.nextInt()
    int K = sc.nextInt()

    def board = new char[N][N]
    N.times { n->

        String line = sc.next()
        line = line.replace('.','')

        int charsRemovidos = N - line.size()
        line = '.'*charsRemovidos + line

        board[n] = line.toCharArray()
    }

    println t
    println K
    println board
    rotate(board, N)
    println board

    boolean rWin =  avaliaLinhas(board, N, K, (char)'R') ||
                    avaliaColunas(board, N, K, (char)'R') ||
                    avaliaDiagonalTopLeftBottomRight(board, N, K, (char)'R') ||
                    avaliaDiagonalBottomLeftToRight(board, N, K, (char)'R')

    boolean bWin =  avaliaLinhas(board, N, K, (char)'B') ||
                    avaliaColunas(board, N, K, (char)'B') ||
                    avaliaDiagonalTopLeftBottomRight(board, N, K, (char)'B') ||
                    avaliaDiagonalBottomLeftToRight(board, N, K, (char)'B')

    if(rWin && bWin) {
        w.writeLine("Case #${t+1}: Both")
    } else if(rWin) {
        w.writeLine("Case #${t+1}: Red")
    } else if(bWin) {
        w.writeLine("Case #${t+1}: Blue")
    } else {
        w.writeLine("Case #${t+1}: Neither")
    }
}
w.close()

boolean avaliaLinhas(def board, int N, int K, char v) {

    for(int i=0; i<N; i++){
        int k = 0
        for(int j=0; j<N; j++){
            if(board[i][j] == v){
                k++
                if(k == K){
                    return true
                }
            }else{
                k = 0
            }
        }
    }
    return false
}

boolean avaliaColunas(def board, int N, int K, char v) {

    for(int j=0; j<N; j++){
        int k = 0
        for(int i=0; i<N; i++){
            if(board[i][j] == v){
                k++
                if(k == K){
                    return true
                }
            }else{
                k = 0
            }
        }
    }
    return false
}

boolean avaliaDiagonalTopLeftBottomRight(def board, int N, int K, char v) {

    for(int I=0; I<N; I++){
        int k = 0

        int i=I
        int j=0

        while(i<N && j<N) {
            if(board[i][j] == v){
                k++
                if(k == K){
                    return true
                }
            }else{
                k = 0
            }

            i++
            j++
        }
    }

    for(int J=0; J<N; J++){
        int k = 0

        int i=0
        int j=J

        while(i<N && j<N) {
            if(board[i][j] == v){
                k++
                if(k == K){
                    return true
                }
            }else{
                k = 0
            }

            i++
            j++
        }
    }

    return false

}

boolean avaliaDiagonalBottomLeftToRight(def board, int N, int K, char v) {

    for(int I=N-1; I>=0; I--){
        int k = 0

        int i=I
        int j=0

        while(i>=0 && j<N) {
            if(board[i][j] == v){
                k++
                if(k == K){
                    return true
                }
            }else{
                k = 0
            }

            i--
            j++
        }
    }

    for(int J=0; J<N; J++){
        int k = 0

        int i=N-1
        int j=J

        while(i>=0 && j<N) {
            if(board[i][j] == v){
                k++
                if(k == K){
                    return true
                }
            }else{
                k = 0
            }

            i--
            j++
        }
    }

    return false

}


def rotate(def board, int N) {

    for(int i=0; i<N; i++) {
        for(int j=0; j<i; j++) {
            String temp = board[i][j]
            board[i][j] = board[j][i]
            board[j][i] = temp
        }
    }

    for(int i=0; i<N; i++) {

        def newLine = new char[N]
        for(int j=0; j<N; j++) {
            newLine[N-1-j] = board[i][j]
        }
        board[i] = newLine
    }

}