package Jam2015.qualification.ominousOmino

import java.security.InvalidParameterException

//def filename = "sample"
def filename = "D-small-attempt2"
//def filename = "B-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

println T
for(int t = 0 ; t < T ; t++ ) {

    int X = sc.nextInt()
    int R = sc.nextInt()
    int C = sc.nextInt()

    String sol = solve(X,R,C)

    println "Case #${t+1}: $sol"
    //w.writeLine("Case #${t+1}: $X $R $C")
    w.writeLine("Case #${t+1}: $sol")

    long totalTime = System.currentTimeMillis() - init
    println "${(totalTime/1000)/60}"

}
w.close()

String solve(int X, int R, int C) {

    String gabriel = "GABRIEL"
    String richard = "RICHARD"

    //REGRA GERAL
    if(X > R*C){
        return richard
    }

    if((R*C)%X != 0){
        return richard
    }

    if(X==1){
        return gabriel
    }

    if(X==2){
        boolean numCellImpar = (R*C)%2 != 0
        return numCellImpar ? richard : gabriel
    }

    if(X==3){

        boolean c2r3 = C%2==0 && R%3==0
        boolean c3r2 = C%3==0 && R%2==0
        boolean c3r3 = C%3==0 && R%3==0

        return (c2r3 || c3r2 || c3r3) ? gabriel : richard
    }

    if(X == 4){

        /*

        boolean c3r4 = C%3==0 && R%4==0
        boolean c4r3 = C%4==0 && R%3==0
        boolean c4r4 = C%4==0 && R%4==0

        boolean c5r4 = C%5==0 && R%4==0
        boolean c4r5 = C%4==0 && R%5==0
        boolean c5r5 = C%4==0 && R%4==0

        boolean c7r4 = C%7==0 && R%4==0
        boolean c4r7 = C%4==0 && R%7==0

        boolean c9r4 = C%9==0 && R%4==0
        boolean c4r9 = C%4==0 && R%9==0

        boolean c9r5 = C%9==0 && R%5==0
        boolean c5r9 = C%4==0 && R%9==0

        boolean c11r4 = C%11==0 && R%4==0
        boolean c4r11 = C%4==0 && R%11==0

        boolean c13r4 = C%13==0 && R%4==0
        boolean c4r13 = C%4==0 && R%13==0

        List g = [c3r4, c4r3, c4r4, c5r4, c4r5, c5r5, c7r4, c4r7, c9r4, c4r9, c9r5, c5r9, c11r4, c4r11, c13r4, c4r13]
        */

        List primes = [3,5,7,9,11,13,17,19]

        boolean Rg = primes.contains(R) || R==4
        boolean Cg = primes.contains(C) || C==4

        return (Rg && Cg) ? gabriel : richard
    }

    if(X >= 7) {
        return richard
    }

}