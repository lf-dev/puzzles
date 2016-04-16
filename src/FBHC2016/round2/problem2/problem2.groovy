package FBHC2016.round2.problem2

go()

def go() {
    def filename = "sample"
//    def filename = ""

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
    return 0
}

def test(){


}


