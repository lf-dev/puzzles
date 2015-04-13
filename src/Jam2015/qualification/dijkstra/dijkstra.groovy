package Jam2015.qualification.dijkstra

import java.security.InvalidParameterException

//def filename = "sample"
def filename = "C-small-attempt1"
//def filename = "B-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

matrix = [
        '11' : '1',
        '1i' : 'i',
        '1j' : 'j',
        '1k' : 'k',
        'i1' : 'i',
        'ii' : '-1',
        'ij' : 'k',
        'ik' : '-j',
        'j1' : 'j',
        'ji' : '-k',
        'jj' : '-1',
        'jk' : 'i',
        'k1' : 'k',
        'ki' : 'j',
        'kj' : '-i',
        'kk' : '-1'
]

println T
for(int t = 0 ; t < T ; t++ ) {

    int L = sc.nextInt()
    int X = sc.nextInt()

    String line = sc.next()

    String transf = line[0]
    for(int n=1; n < line.size() ; n++){
        transf = transform(transf,line[n])
    }
    line = transf*X

    //println line

    List countI = [0]*line.size()
    List countJ = [0]*line.size()
    List countK = [0]*line.size()
    int accI, accJ, accK

    for(int n=line.size()-1; n >=0 ; n--){
        //acumulado
        if(line[n]=='i'){ accI++ }
        if(line[n]=='j'){ accJ++ }
        if(line[n]=='k'){ accK++ }

        countI[n] = accI
        countJ[n] = accJ
        countK[n] = accK
    }

    List backTransform = [0]*line.size()
    backTransform[line.size()-1] = line[line.size()-1]
    for(int n=line.size()-2; n >=0 ; n--){
        String a = line[n]
        String b = backTransform[n+1]
        backTransform[n] = transform(a,b)
    }

    //println backTransform

    String sol = solve(line, countI, countJ, countK, backTransform)

    println "Case #${t+1}: $sol"
    w.writeLine("Case #${t+1}: $sol")

    long totalTime = System.currentTimeMillis() - init
    println "${(totalTime/1000)/60}"

}
w.close()

String solve(String line, List countI, List countJ, List countK, List backTransform) {

    if(impossible(countI,countJ,countK,0,'i')){
        return "NO"
    }

    int size = line.size()
    String iStr = line[0]

    for (int i = 1; i <= size-2; i++) {
        if(iStr == 'i'){

            if(impossible(countI,countJ,countK,i,'j')){
                return "NO"
            }

            String jStr = line[i]
            for(int j = i+1; j <= size-1; j++) {
                if(jStr == 'j'){

                    if(impossible(countI,countJ,countK,j,'k')){
                        return "NO"
                    }

                    if(backTransform[j]=='k'){
                        return "YES"
                    }
                    /*
                    String kStr = line[j]
                    for(int k = j+1; k <= size; k++) {
                        if(kStr == 'k' && k == size){
                            return 'YES'
                        }
                        if(k < size){
                             kStr = transform(kStr, line[k])
                        }
                    }
                    */
                }
                jStr = transform(jStr, line[j])
            }
        }
        iStr = transform(iStr, line[i])
    }

    return "NO"
}

boolean impossible(List countI, List countJ, List countK, int index, String tipo) {

    boolean somenteI = (countI[index] > 0  && countJ[index] == 0 && countK[index] == 0)
    boolean somenteJ = (countI[index] == 0 && countJ[index] > 0  && countK[index] == 0)
    boolean somenteK = (countI[index] == 0 && countJ[index] == 0 && countK[index] > 0)

    if(tipo == 'i'){
        return somenteJ || somenteK
        //return somenteJ || somenteK || (somenteI && countI[index]%5 != 0 && countI[index] != 1)
    }

    if(tipo == 'j'){
        return somenteI || somenteK
        //return somenteI || somenteK || (somenteJ && countJ[index]%5 != 0 && countJ[index] != 1)
    }

    if(tipo == 'k'){
        return somenteI || somenteJ
        //return somenteI || somenteJ || (somenteK && countK[index]%5 != 0 && countK[index] != 1)
    }

    throw new InvalidParameterException("i j k")
}

String transform(String a, String b){

    int aNeg = 1
    if(a.startsWith('-')){
        aNeg = -1
        a = a[1]
    }

    int bNeg = 1
    if(b.startsWith('-')){
        bNeg = -1
        b = b[1]
    }

    String trans = matrix["$a$b"]

    int transNeg = 1
    if(trans.startsWith('-')){
        transNeg = -1
        trans = trans[1]
    }

    int sig = aNeg * bNeg * transNeg
    if(sig<0){
        return "-$trans"
    }else{
        return trans
    }
}