package FBHC2016.round2.problem1

go()
//test()

def go() {
//    def filename = "sample"
    def filename = "boomerang_decoration"

    Scanner sc = new Scanner(new File(filename + '.txt'))
    def file = new File(filename + '.out')
    def w = file.newWriter()

    int T = sc.nextInt()

    println T
    for (int t = 0; t < T; t++) {

        int N = sc.nextInt()

        String aba = sc.next()
        String gabarito = sc.next()

        long resp = minTime(aba, gabarito, N)
        w.writeLine("Case #${t + 1}: $resp")
        println("Case #${t + 1}: $resp")
    }
    w.close()
}


long minTime(String aba, String gabarito, int N) {

    List sequencias = sequencias(gabarito, N)

    int middle = sequencias.size()/2

    int i = sequencias[middle]

    while(i>=0 && aba[i] == gabarito[i]){
        i--
    }
    int numSequenciasParaEsquerda = 0

    if(i>=0){
        char last = gabarito.charAt(i)
        numSequenciasParaEsquerda++
        while(i>=0){
            if(gabarito.charAt([i]) != last){
                numSequenciasParaEsquerda++
                last = gabarito.charAt([i])
            }
            i--
        }
    }

    i = sequencias[middle] + 1

    while(i<N && aba[i] == gabarito[i]){
        i++
    }
    int numSequenciasParaDireita = 0

    if(i<N){
        last = gabarito.charAt(i)
        numSequenciasParaDireita++
        while(i<N){
            if(gabarito.charAt([i]) != last){
                numSequenciasParaDireita++
                last = gabarito.charAt([i])
            }
            i++
        }
    }

    return Math.max(numSequenciasParaEsquerda, numSequenciasParaDireita)
}

List sequencias(String str, int N){

    str.size()

    List sequencias = [0]
    char last = str.charAt(0)
    for(int i=1; i<N; i++){

        if(str.charAt(i) != last){
            sequencias << i
            last = str.charAt(i)
        }
    }
    return sequencias
}

def test(){

    assert sequencias("ABC",3) == [0,1,2]
    assert sequencias("NOREAALLY",9) == [0,1,2,3,4,6,8]
    assert sequencias("OOBOOOOVVBOOEAAAGGS",19) == [0,2,3,7,9,10,12,13,16,18]

    String gabarito = "A"*10**6
    String aba = "B"*10**6

    println gabarito
    println aba

    assert minTime(aba,gabarito,10**6) == 1

    println "test ok"


}


