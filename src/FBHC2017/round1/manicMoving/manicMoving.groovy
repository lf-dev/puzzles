package FBHC2017.round1.manicMoving

//test()

//def filename = "sample"
def filename = "manic_moving"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    int M = sc.nextInt()
    int K = sc.nextInt()

    int[][] Ms = new int[N][N]

    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++){
            if(i == j) {
                Ms[i][j] = 0
            }else {
                Ms[i][j] = -1
            }
        }
    }

    M.times {

        int a = sc.nextInt()-1
        int b = sc.nextInt()-1
        int g = sc.nextInt()

        Ms[a][b] = g
        Ms[b][a] = g
    }

    List S = []
    List D = []

    K.times {
        S << sc.nextInt()-1
        D << sc.nextInt()-1
    }

    long min = minCost(N, M, K, Ms, S, D)

    w.writeLine("Case #${t + 1}: $min")
    println("Case #${t + 1}: $min")
}


w.close()

long minCost(int N, int M, int K, int[][] Ms, List S, List D) {

    List P = []

    P[0] = path(Ms, N, [0, S[0], D[0]])

    if(K>1){

        long path1 = path(Ms, N, [D[0],S[1],D[1]])
        long path2 = path(Ms, N, [0, S[0], S[1], D[0], D[1]])

        if(path1 < 0 || path2 < 0){
            return -1
        }

        P[1] = Math.min(P[0] + path1, path2)
    }

    for(int k=2; k<K; k++){

        long path1 = path(Ms, N, [D[k-1], S[k], D[k]])
        long path2 = path(Ms, N, [D[k-2], S[k-1], S[k], D[k-1], D[k]])

        if(path1 < 0 || path2 < 0) {
            return -1
        }

        P[k] = Math.min(P[k-1] + path1, P[k-2] + path2)
    }

    return P[K-1]
}

int path(int[][] Ms, int N, List p) {

    long cost = 0
    for(int i=0; i<p.size()-1; i++){

        int s = p[i]
        int d = p[i+1]
        long c = dijktra(Ms, N, s, d)
        if(c == Long.MAX_VALUE){
            return -1
        }else{
            cost += c
        }
    }
    return cost

}

long dijktra(int[][] Ms, int N, int s, int d){

    List distances = [Long.MAX_VALUE]*N
    distances[s] = 0

    List naoVisitados = [s]
    Set visitados = [] as Set

    while(!naoVisitados.isEmpty()){

        int n = getMin(distances, naoVisitados)

        naoVisitados.removeElement(n)
        visitados << n

        if(n == d){
            break
        }

        List vizinhos = obterVizinhos(n, Ms, N, visitados)

        for(int i=0; i< vizinhos.size(); i++){

            int v = vizinhos[i]
            if(distances[v] > Ms[n][v] + distances[n]){
                distances[v] = Ms[n][v] + distances[n]

                if(!visitados.contains(v) && !naoVisitados.contains(v)){
                    naoVisitados << v
                }
            }
        }

    }

    return distances[d];
}

List obterVizinhos(int n, int[][] Ms, int N, Set visitados) {

    List v = []
    for(int i=0; i<N; i++){

        if(Ms[n][i] > 0 && !visitados.contains(i)) {
            v << i
        }

    }
    return v
}

int getMin(List distances, List naoVisitados) {

    long minValue = Long.MAX_VALUE
    int minNode = -1
    for(int i=0; i<naoVisitados.size(); i++){
        int node = naoVisitados[i]
        if(distances[node] < minValue){
            minValue = distances[node]
            minNode = node
        }
    }
    return minNode
}

void test(){

    List distances = [Long.MAX_VALUE, 10, 20]
    List naoVisitados = [1, 2]

    assert getMin(distances, naoVisitados) == 1
    naoVisitados.removeElement(1)
    assert getMin(distances, naoVisitados) == 2

    int N = 3
    int[][] Ms = [  [0,1,3],
                    [1,0,1],
                    [3,1,0] ]
    assert obterVizinhos(0, Ms, N, [] as Set) == [1, 2]
    assert obterVizinhos(1, Ms, N, [] as Set) == [0, 2]
    assert obterVizinhos(2, Ms, N, [] as Set) == [0, 1]
    assert obterVizinhos(0, Ms, N, [1] as Set) == [2]
    assert obterVizinhos(1, Ms, N, [0] as Set) == [2]
    assert obterVizinhos(2, Ms, N, [1] as Set) == [0]
    assert dijktra(Ms, N, 0, 2) == 2

    Ms = [  [ 0, 1,-1,-1, 5],
            [ 1, 0, 1,-1,-1],
            [-1, 1, 0, 1,-1],
            [-1,-1, 1, 0, 1],
            [ 5,-1,-1, 1, 0]  ]
    assert dijktra(Ms, 5, 0, 4) == 4
    assert dijktra(Ms, 5, 0, 1) == 1
    assert dijktra(Ms, 5, 2, 4) == 2

    Ms = [  [ 0, 7, 9,-1,-1,14],
            [ 7, 0,10,15,-1,-1],
            [ 9,10, 0, 11,-1,2],
            [-1,15, 11, 0,6,-1],
            [-1,-1,-1, 6, 0, 9],
            [14,-1, 2,-1, 9, 0]]
    assert dijktra(Ms, 6, 0, 4) == 20
    assert dijktra(Ms, 6, 0, 5) == 11

    //disconexo
    Ms = [  [0, -1],
            [-1, 0]]
    assert dijktra(Ms, 2, 0, 1) == Long.MAX_VALUE

    //do comeco para ele mesmo
    assert dijktra(Ms, 2, 0, 0) == 0

    //performance
    N = 100
    Ms = new int[N][N]
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++){
            Ms[i][j] = -1
        }
    }
    for(int i=0; i<N; i++){

        Ms[i][i] = 0
        if(i < N-1){
            Ms[i+1][i] = 1000
            Ms[i][i+1] = 1000
        }
    }
    int M = 99

    int K = 5000
    List S = [0]*K
    List D = [99]*K

    long precoIda = 1000*99
    long numeroViajens = K/2 //cada viajem tranporta duas familias
    long precoTotal = precoIda*2*numeroViajens - precoIda //ida e volta * numero de viajens menos a ultima volta
    assert minCost(N, M, K, Ms, S, D) == precoTotal

    println "test ok!"
}
