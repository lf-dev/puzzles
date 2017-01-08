package FBHC2017.qualification.lazyLoading

//def filename = "sample"
def filename = "lazy_loading"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    List Ws = []

    N.times {
        Ws << sc.nextInt()
    }

    int max = maxTrips(N, Ws)

    w.writeLine("Case #${t + 1}: $max")
    println("Case #${t + 1}: $max")
}
w.close()

int maxTrips(int N, List Ws) {

    Ws = Ws.sort()
    int numTrips = 0

    while(!Ws.isEmpty()) {

        int heaviest = Ws.remove(Ws.size()-1)
        int tripWeight = heaviest

        while(tripWeight < 50 && !Ws.isEmpty()){
            Ws.remove(0)
            tripWeight += heaviest
        }

        if(tripWeight >= 50){
            numTrips++
        }
    }
    return numTrips
}
