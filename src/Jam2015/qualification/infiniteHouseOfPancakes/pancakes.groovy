package Jam2015.qualification.infiniteHouseOfPancakes

//def filename = "sample"
//def filename = "B-small-attempt1"
def filename = "B-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

println T
for(int t = 0 ; t < T ; t++ ) {

    int D = sc.nextInt()
    List P = []
    D.times {
        P << sc.nextInt()
    }

    int time = time2(P)

    println "Case #${t+1}: $time"

    w.writeLine("Case #${t+1}: $time")

    long totalTime = System.currentTimeMillis() - init
    println "${(totalTime/1000)/60}"

}
w.close()

int time2(List P){

    List times = []
    int max = P.max()
    for(int numPancakes=1; numPancakes<=max; numPancakes++){

        int numSpecials = 0
        for(int i=0; i<P.size(); i++){

            if(P[i] > numPancakes){
                numSpecials += (Math.ceil(P[i]/numPancakes) as int) - 1
            }

            times[numPancakes] = numSpecials + numPancakes
        }
    }

    return times.min()

}

int time(List P){

    int t = 0
    int timeAnterior = P.max()
    while(true){

        parada(P)
        t++

        int timeCorrente = P.max() + t

        if(timeAnterior < timeCorrente) {
            return timeAnterior
        }else {
            timeAnterior = timeCorrente
        }
    }
}

void parada(List P) {

    int max = P.max()
    P.remove(max as Object)

    if(max%2==0){
        int half = max/2 as int
        P << half
        P << half
    }else {
        int half = (max-1)/2
        P << half
        P << half+1
    }
}