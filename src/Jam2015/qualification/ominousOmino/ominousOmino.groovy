package Jam2015.qualification.ominousOmino

import java.security.InvalidParameterException

//def filename = "sample"
//def filename = "D-small-attempt2"
def filename = "D-large"

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

    //nao tem espaco
    if(X > R*C){
        return richard
    }

    //nao é multiplo
    if((R*C)%X != 0){
        return richard
    }

    //richard pode escolher a peca comprida
    if(X>R && X>C){
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

        List primes = [2,3,5,7,9,11,13,17,19]

        boolean Rg = primes.any{ p -> R%p==0 }
        boolean Cg = primes.any{ p -> C%p==0 }

        return (Rg && Cg) ? gabriel : richard
    }

    if(X >= 4 && X <= 6){

        //2 é primo mas nao funciona para X=4, 5 ,6
        if(R==2 || C==2){
            return richard
        }

        List primes = [2,3,5,7,9,11,13,17,19]

        boolean Rg = primes.any{ p -> R%p==0 }
        boolean Cg = primes.any{ p -> C%p==0 }

        return (Rg && Cg) ? gabriel : richard
    }

    //richard pode escolher a peca com buraco isolado no meio
    if(X >= 7) {
        return richard
    }

}