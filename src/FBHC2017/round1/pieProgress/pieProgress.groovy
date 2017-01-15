package FBHC2017.round1.pieProgress

//test()

//def filename = "sample"
def filename = "pie_progress"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    int M = sc.nextInt()

    List Ns = []
    N.times {
        List Ms = []
        M.times {
            Ms << sc.nextInt()
        }
        Ns << Ms.sort()
    }

    int min = minCost(Ns, N, M)

    w.writeLine("Case #${t + 1}: $min")
    println("Case #${t + 1}: $min")
}


w.close()

int minCost(List Ns, int N, int M) {

    List piesPerDay = [0]*N
    List bestPricePerDay = [0]*N

    for(int n=0; n<N; n++){

        List mins = []

        int bestPrice = n==0 ? 0 : bestPricePerDay[n-1]
        for(int day=0; day<=n; day++){

            List Ms = Ns[day]
            int piesThisDay = piesPerDay[day]

            if(piesThisDay >= M) {
                mins << Integer.MAX_VALUE
            }else {
                int piePrice = Ms[piesThisDay]
                int priceWithThisPie = bestPrice + piePrice - (piesThisDay**2) + ((piesThisDay+1)**2)
                mins << priceWithThisPie
            }
        }

        int minValue = mins.min()
        int minIndex = mins.indexOf(minValue)

        piesPerDay[minIndex] += 1
        bestPricePerDay[n] = minValue
    }

    return bestPricePerDay[N-1]
}

void test(){

    List Ns1 = [ [1, 1],
                 [100, 100],
                 [10000, 10000]]
    assert minCost(Ns1, 3, 2) == 107

    List Ns2 = [[1],
                [2],
                [3],
                [4],
                [5]]
    assert minCost(Ns2, 5, 1) == 20

    List Ns3 = [[1, 2, 3, 4, 5].sort(),
                [2, 3, 4, 5, 1].sort(),
                [3, 4, 5, 1, 2].sort(),
                [4, 5, 1, 2, 3].sort(),
                [5, 1, 2, 3, 4].sort()]
    assert minCost(Ns3, 5, 5) == 10

    List Ns4 = [[1, 1, 1, 1, 1].sort(),
                [2, 2, 2, 2, 2].sort(),
                [3, 3, 3, 3, 3].sort(),
                [4, 4, 4, 4, 4].sort(),
                [5, 5, 5, 5, 5].sort()]
    assert minCost(Ns4, 5, 5) == 18

    List Ns5 = [[7, 15, 12, 6].sort(),
                [15, 3, 19, 18].sort(),
                [10, 9, 10, 14].sort(),
                [12, 14, 8, 8].sort(),
                [5, 3, 5, 11].sort(),
                [9, 14, 19, 11].sort(),
                [12, 6, 20, 9].sort(),
                [18, 13, 12, 15].sort(),
                [14, 14, 10, 20].sort(),
                [11, 19, 12, 11].sort()]
    assert minCost(Ns5, 10, 4) == 79

    List Ns6 = [[1]*3,
                [1000]*3,
                [1000]*3]
    assert minCost(Ns6, 3, 3) == 1 + 1 + 1 + 3**2

    List Ns7 = []
    300.times {
        Ns7 << [10**6]*300
    }
    assert minCost(Ns7, 300, 300) == 300*(10**6) + 300

    100.times {
        assert minCost(Ns7, 300, 300) == 300*(10**6) + 300
    }

    println "test ok!"
}
