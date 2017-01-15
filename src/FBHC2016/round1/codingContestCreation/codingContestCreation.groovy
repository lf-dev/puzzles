package FBHC2016.round1.codingContestCreation

//go()
test()

def go() {
//    def filename = "sample"
    def filename = "coding_contest_creation"

    Scanner sc = new Scanner(new File(filename + '.txt'))
    def file = new File(filename + '.out')
    def w = file.newWriter()

    int T = sc.nextInt()

    println T
    for (int t = 0; t < T; t++) {

        int N = sc.nextInt()
        List Ds = []

        N.times {
            Ds << sc.nextInt()
        }

        long resp = numProblems(Ds)
        w.writeLine("Case #${t + 1}: $resp")
        println("Case #${t + 1}: $resp")
    }
    w.close()
}


long numProblems(List Ds) {

    int count, i, f = 0

    while(f < Ds.size()){

        if(Ds[f] < Ds[f+1]){
            f++
        }else {

            int numElementosSeq = 1
            while(i < f){

                int dif = Ds[i+1] - Ds[i]
                if(dif > 10){
//                    int numInserts = numInserts(dif)
//                    int roundUp = roundUp(numElementosSeq)

                    int numInserts = Math.min(qtyInserts(dif), roundUp(numElementosSeq))

                    count += numInserts
                    numElementosSeq += numInserts
                }

                i++
                numElementosSeq++
            }
            count += roundUp(numElementosSeq)
            f++
            i++
        }
    }

    return count
}

int qtyInserts(int dif){
    return Math.ceil(dif/10) - 1
}

int roundUp(int numElementos) {
    int mod = numElementos%4
    if(mod == 0){
        return 0
    }else {
        return 4 - mod
    }
}

def test(){

    //1, 11
    assert qtyInserts(11-1) == 0

    //1,12
    assert qtyInserts(12-1) == 1

    //1,21
    assert qtyInserts(21-1) == 1

    //1,22
    assert qtyInserts(22-1) == 2

    assert roundUp(0) == 0
    assert roundUp(1) == 3
    assert roundUp(2) == 2
    assert roundUp(3) == 1
    assert roundUp(4) == 0
    assert roundUp(5) == 3
    assert roundUp(6) == 2
    assert roundUp(7) == 1
    assert roundUp(8) == 0

    assert numProblems([10,15,25,30]) == 0
    assert numProblems([15,20,25,40]) == 4
    assert numProblems([3,3,3]) == 9
    assert numProblems([60,90,61,62,63,91,92,93]) == 4
    assert numProblems([5,14,30,32,39,46,47,47,30,58,47]) == 9

    assert numProblems([1]) == 3
    assert numProblems([100]) == 3

    assert numProblems([98,99,100,100]) == 4
    assert numProblems([97,98,99,100,100]) == 3
    assert numProblems([97,98,99,100]) == 0

    assert numProblems([1,100]) == 6

    List Ds = [1,2,3,4,5,6,7,8]*10**4
    assert numProblems(Ds) == 0

    Ds = [1,2,3,4,5,6,7,8,9]*10**5
    assert numProblems(Ds) == 3*10**5

    println "ok"
}


