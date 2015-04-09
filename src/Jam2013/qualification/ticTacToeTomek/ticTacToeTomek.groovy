package Jam2013.qualification.ticTacToeTomek

//def filename = "sample"
//def filename = "A-small-practice"
def filename = "A-large-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    List lines = []
    for(int l=0; l<4; l++){
        lines << sc.next()
    }

    List columns = [""]*4
    for(int c=0; c<4; c++){
        columns[c] = lines[0][c] + lines[1][c] + lines[2][c] + lines[3][c]
    }

    List diag = [""]*2
    diag[0] = lines[0][0] + lines[1][1] + lines[2][2] + lines[3][3]
    diag[1] = lines[0][3] + lines[1][2] + lines[2][1] + lines[3][0]

    List tudo = [lines, columns, diag].flatten()

    boolean wonX = won(tudo, 'X')
    boolean wonO = won(tudo, 'O')

    if(wonX) {
        w.writeLine("Case #${t+1}: X won")
    }else if(wonO) {
        w.writeLine("Case #${t+1}: O won")
    }else if(notCompleted(lines)) {
        w.writeLine("Case #${t+1}: Game has not completed")
    }else {
        w.writeLine("Case #${t+1}: Draw")
    }
}
w.close()

boolean won(List lines, String play) {

    return lines.any {
        (it.count(play) + it.count('T')) == 4
    }
}

boolean notCompleted(List lines){
    return lines.any {
        it.contains(".")
    }
}

