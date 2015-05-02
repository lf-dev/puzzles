package Jam2015.round1a.problemA

//def filename = "sample"
//def filename = "A-small-attempt0"
def filename = "A-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

println T
for(int t = 0 ; t < T ; t++ ) {

    int N = sc.nextInt()
    sc.nextLine()

    List ms = sc.nextLine().split(' ').collect{ it as Integer}

    println ms

    long y = method1(ms)
    long z = method2(ms)

    w.writeLine("Case #${t+1}: $y $z")
}
w.close()


long method1(List ms){

    long tot = 0
    for(int i=0; i< ms.size()-1; i++) {

        int antes = ms[i]
        int depois = ms[i+1]

        if(antes > depois){
            tot += antes - depois
        }
    }
    return tot
}

long method2(List ms){

    int rate = 0
    for(int i=0; i< ms.size()-1; i++) {

        int diff = ms[i] - ms[i+1]

        if(diff > rate){
            rate = diff
        }
    }

    long tot = 0
    for(int i=0; i< ms.size()-1; i++) {

        int antes = ms[i]

        tot += Math.min(antes, rate)
    }

    return tot
}
