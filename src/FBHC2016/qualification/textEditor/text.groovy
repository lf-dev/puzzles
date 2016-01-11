package FBHC2016.qualification.textEditor

go()

def go() {

//    def filename = "sample"
    def filename = "text_editor"

    Scanner sc = new Scanner(new File(filename + '.txt'))
    def file = new File(filename + '.out')
    def w = file.newWriter()

    int T = sc.nextInt()

    println T
    for (int t = 0; t < T; t++) {

        int N = sc.nextInt()
        int K = sc.nextInt()

        List words = []

        N.times {
            words[it] = sc.next()
        }

//        words = words.sort{it.size()}

        int[][] table = distanceTable(words, N)
        int numOps = numOpsCombinatorial(N, K, table, [])
//        int numOps = greed(table,N,K)

        w.writeLine("Case #${t + 1}: $numOps")
        println("Case #${t + 1}: $numOps")
    }
    w.close()
}

int numOpsCombinatorial(int N, int K, int[][] table, List seq) {

    if(seq.size() == K){
        return evalSequence(seq, table)
    }

    List results =[]
    for(int i=0; i<N; i++){
        if(!seq.contains(i)){
            seq << i
            results << numOpsCombinatorial(N, K, table, seq)
            seq.remove(seq.size()-1)
        }
    }
    results.min()
}

int greed(int[][] table, int N, int K){
    int min = Integer.MAX_VALUE
    int init = 0
    for(int i=0; i<N; i++){

        for(int j=i+1; j<N; j++){
            if(table[i][j]<min){
                init = j
                min = table[i][j]
            }
        }
    }

    List seq = [init]

    while(seq.size() < K){

        int cur = seq.last()

        min = Integer.MAX_VALUE
        int index = Integer.MAX_VALUE
        for(int i=0; i<N; i++){

            if(table[cur][i]<min && !seq.contains(i)){
                index = i
                min = table[cur][i]
            }
        }
        seq << index
    }

//    println seq
    return evalSequence(seq, table)
}

int evalSequence(List sequence, int[][] table) {
    int acum = table[sequence.first()][sequence.first()]
    for(int i=0; i<sequence.size()-1; i++){
        acum += table[sequence[i]][sequence[i+1]]
    }
    acum += table[sequence.last()][sequence.last()] - 1
    return acum
}

int distance(String w1, String w2){

    int max = Math.min(w1.size(), w2.size())

    int i = 0
    while(i < max && w1[i] ==  w2[i]){
        i++
    }

    return (w1.size() - i) + (w2.size() - i) + 1
}

int[][] distanceTable(List words, int N) {

    int[][] table = new int[N][N]

    for(int i=0; i<N; i++){

        String w1 = words[i]

        table[i][i] = w1.size() + 1
        for(int j=i+1; j<N; j++){
            table[i][j] = distance(w1, words[j])
            table[j][i] = table[i][j]
        }
    }

    return table
}

def test() {

    assert distance("f","ff") == 2
    assert distance("hair","hail") == 3
    assert distance("aaa","bbb") == 7
    assert distance("fox","xfox") == 8


    assert distanceTable(["hair","hail"], 2) == [[5,3],[3,5]]
    assert distanceTable(["aaa","bbb"], 2) == [[4,7],[7,4]]

    assert distanceTable(["f","ff","fff"], 3) == [[2,2,3],[2,3,2],[3,2,4]]

    int[][] table = [[2,2,3],[2,3,2],[3,2,4]]
    assert evalSequence([0,1,2], table) == 2 + 2 + 2 + 4 - 1
    assert evalSequence([2,1,0], table) == 4 + 2 + 2 + 2 - 1

    table = distanceTable(["fff","fffff","ff","ffff"], 4)
    assert evalSequence([2,0,3],table) == evalSequence([3,0,2],table)
    assert evalSequence([0,1,2,3],table) == evalSequence([3,2,1,0],table)

    table = distanceTable(["a", "hair", "box", "awesome", "hail"], 5)
    table.each {
        println it
    }

    println "ok"

}