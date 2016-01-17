package FBHC2016.round1.boomerangTournament

go()
//test()

def go() {
    def filename = "sample"
//    def filename = "boomenrang_tounament"

    Scanner sc = new Scanner(new File(filename + '.txt'))
    def file = new File(filename + '.out')
    def w = file.newWriter()

    int T = sc.nextInt()

    println T
    for (int t = 0; t < T; t++) {

        int N = sc.nextInt()
        int[][] ws = new int[N][N]

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int n = sc.nextInt()
                ws[i][j] = n
            }
        }

        int K = N**1/2

        List resp = solve(N, K, ws, 0)
        w.writeLine("Case #${t + 1}: $resp")
        println("Case #${t + 1}: $resp")
    }
    w.close()
}

List solve(int N, int K, int[][] ws, int n) {

    List line = []
    N.times {
        line << it
    }

    long seed = System.nanoTime()
    Random r = new Random(seed)

    int best = N
    int worst = 0
    10.times {
        Collections.shuffle(line,r)
        List score = eval(line, n, ws)
        List rank = rank(score)
        int ra = rank[n]

        if(ra<best) {
            best = rank
        }

        if(ra>worst){
            worst = rank
        }
    }

    return [best,worst]
}

int best(int N, int K, int[][] ws, int n) {

    List winsOfN = winsOfN(N, ws, n)
    List losesOfN = losesOfN(N, ws, n)

    int bestDepth = Math.min(K, winsOfN.size())

}

def buildBest(int[] line, Set used, int size, int i, int f, int n){

    List winsOfN = winsOfN(N, ws, n)
    List losesOfN = losesOfN(N, ws, n)

    buildBest(line, used, i, f/2, n)

}

List winsOfN(int N, int[][] ws, int n) {
    List winsOfN = []
    for(int i=0; i<N; i++){
        if(ws[n][i] == 1){
            winsOfN << i
        }
    }
    return winsOfN
}

List losesOfN(int N, int[][] ws, int n) {
    List losesOfN = []
    for(int i=0; i<N; i++){
        if(i!=n && ws[n][i] == 0){
            losesOfN << i
        }
    }
    return losesOfN
}

List eval(List line, int N, int[][] ws){

    List scores = [0]*N
    while(line.size() > 1){
        int n1 = line.remove(0)
        int n2 = line.remove(0)

        if(ws[n1-1][n2-1] == 1){
            scores[n1-1]++
            line << n1
        }else{
            scores[n2-1]++
            line << n2
        }
    }
    return scores
}

List rank(List scores){

    List sortedScores = []
    sortedScores.addAll(scores)
    sortedScores.sort{it*-1}.unique(true)

    List positions = [0]*scores.size()
    int position = 1
    for(int i=0; i<sortedScores.size(); i++){
        int score = sortedScores[i]

        for(int j=0; j<positions.size(); j++){
            if(scores[j] == score){
                positions[j] = position
            }
        }
        position++
    }
    return positions
}

def test(){

    int N = 4
    int[][] ws = [[0,1,1,1],[0,0,1,1],[0,0,0,1],[0,0,0,0]]

    assert winsOfN(N, ws, 0) == [1,2,3]
    assert winsOfN(N, ws, 1) == [2,3]
    assert winsOfN(N, ws, 2) == [3]
    assert winsOfN(N, ws, 3) == []

    assert losesOfN(N, ws, 0) == []
    assert losesOfN(N, ws, 1) == [0]
    assert losesOfN(N, ws, 2) == [0,1]
    assert losesOfN(N, ws, 3) == [0,1,2]

    assert eval([1,2,3,4], N, ws) == [2,0,1,0]
    assert eval([4,3,2,1], N, ws) == [2,0,1,0]
    assert eval([1,4,2,3], N, ws) == [2,1,0,0]

    assert rank([2,0,1,0]) == [1,3,2,3]
    assert rank([2,1,0,0]) == [1,2,3,3]

    println "test - ok"

}


