package FBHC2016.round1.laudroMatt

go()
//test()

def go() {
//    def filename = "sample"
    def filename = "laundro_matt"

    Scanner sc = new Scanner(new File(filename + '.txt'))
    def file = new File(filename + '.out')
    def w = file.newWriter()

    int T = sc.nextInt()

    println T
    for (int t = 0; t < T; t++) {

        int L = sc.nextInt()
        int N = sc.nextInt()
        int M = sc.nextInt()
        int D = sc.nextInt()
        List Ws = []

        N.times {
            Ws << sc.nextInt()
        }

        long resp = minutes(L,N,M,D,Ws)
        w.writeLine("Case #${t + 1}: $resp")
        println("Case #${t + 1}: $resp")
    }
    w.close()
}

class WhashingMachine implements Comparable<WhashingMachine>{
    //time to wash
    long w
    //when this machine will stop if selected
    long t

    @Override
    int compareTo(WhashingMachine wm) {
        return this.t <=> wm.t
    }

    String toString() {
        return "(t: $t, w: $w)"
    }
}

long minutes(int L, int N, int M, int D, List Ws) {

    PriorityQueue heapWM = new PriorityQueue(N)
    Ws.each {
        heapWM.add(new WhashingMachine(t: it, w: it))
    }

    int numDryers = Math.min(L,M)
    LinkedList dryers = new LinkedList([0L]*numDryers)


    for(int l=0; l<L; l++){
        WhashingMachine n = heapWM.poll()

        //n.t roupa sai da wm
        //tdryer é quando a proxima secadora estara livre
        long tdryer = dryers.removeFirst()

        dryers.addLast(Math.max(n.t + D, tdryer + D))

        n.t += n.w
        heapWM.add(n)
    }

    return dryers.max()
}

def test(){

    50.times{
        int N = 10**5
        int L = 10**6
        int M = 10**9
        int D = 10**9

        List Ws = [10**9]*N

        println minutes(L,N,M,D,Ws)
    }

    println "ok"

}


