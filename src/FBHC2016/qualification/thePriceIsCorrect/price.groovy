package FBHC2016.qualification.thePriceIsCorrect

//def filename = "sample"
def filename = "the_price_is_correct"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    int P = sc.nextInt()

    List Bs = []

    N.times {
        Bs << sc.nextInt()
    }
    Bs << P+1 //sentinela

    long numSequences = numSequences(Bs, P, N)
    w.writeLine("Case #${t + 1}: $numSequences")
    println("Case #${t + 1}: $numSequences")
}
w.close()

long numSequences(List Bs, int P, int N) {

    int i,f, acum = 0
    long sequences = 0

    acum = Bs[f]

    while(i<N){

        while(acum <= P){
            f++
            acum += Bs[f]
        }

        if(f>i){
            acum -= Bs[f]
            f--
        }

        if(i == f) {

            if(Bs[f]<=P){
                sequences++
            }
            i++
            f++
            acum = Bs[i]

        }else {

            sequences += f - i + 1
            acum -= Bs[i]
            i++
        }
    }

    return sequences
}


