package FBHC2017.round1.beachUmbrellas

test()

//def filename = "sample"
def filename = "beach_umbrellas"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    int M = sc.nextInt()

    List Rs = []
    N.times {
        Rs << sc.nextInt()
    }

    int arr = arrangements(N, M, Rs)

    w.writeLine("Case #${t + 1}: $arr")
    println("Case #${t + 1}: $arr")
}

w.close()

long arrangements(int N, int M, List Rs) {

    if(N == 1){
        return M
    }

    long blockSize = Rs.sum()*2
    long total = 0

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){

            long size = blockSize - Rs[i] - Rs[j] + 1

            if(size <= M && i!=j){
                long empty = Math.min(M - size, N)

                //combinacoes espacos vazios
                long totalEmpty = 0
                for(int k=1; k<=empty; k++){
                    totalEmpty += modCombination(N, k)
                    totalEmpty = mod(totalEmpty)
                }

                //total de combinaçoes dentro do bloco + somatorio das combinacoes dos espacos vazios
                long F = N-2
                total += modPermutacao(F) + totalEmpty
                total = mod(total)
            }
        }
    }

    return total
}

long modCombination(double n, double k) {
    modCombination(n, k, (10**9)+7)
}

long modCombination(double n, double k, double m) {

    double total = modPermutacao(n as Long)
    long p = n-k

    while(k > 0){
        total = (total/k)
        k--
    }

    while(p > 0){
        total = (total/p)
        p--
    }
    return total%m as Long
}

long modPermutacao(long k){

    long total = 1
    while(k > 0){
        total = mod(total*k)
        k--
    }
    return mod(total)
}

long mod(long num){
    long m = (10**9) + 7

    return num%m
}

//433316150
//916295601

void test(){


    println modCombination(5, 3, 10)
    println 10%7
    println modPermutacao(5)

    println "test ok!"
}
