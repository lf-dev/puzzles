package Jam2017.round1a.problemB

def filename = "sample"
//def filename = "B-small-attempt0"
//def filename = "B-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

println T
for(int t = 0 ; t < T ; t++ ) {

    int N = sc.nextInt()
    int R = sc.nextInt()
    int O = sc.nextInt()
    int Y = sc.nextInt()
    int G = sc.nextInt()
    int B = sc.nextInt()
    int V = sc.nextInt()

    String sol = solve(R, O, Y, G, B, V)

    String out = "Case #${t+1}: " + sol
    w.writeLine(out)
    println out
}
w.close()

String solve(int R, int O, int Y, int G, int B, int V) {

    List map = ["R","O","Y","G","B","V"]
    List unicornios = [R, O, Y, G, B, V]

    boolean inseriuAoMenosUm = true
    LinkedList stalls = new LinkedList()

    while(inseriuAoMenosUm && unicornios.sum() > 0){
        inseriuAoMenosUm = false

        for(int color=0; color<unicornios.size(); color++){

            //existe unicornio daquela cor
            if(unicornios[color] > 0) {

                //primeiro unicornio
                if(stalls.size() == 0){
                    stalls << color
                    inseriuAoMenosUm = true
                    unicornios[color]--
                }else if(stalls.size() > 0){

                    if(insert(stalls, color)){
                        inseriuAoMenosUm = true
                        unicornios[color]--
                    }
                }
            }
        }
    }

    if(unicornios.sum() > 0){
        return "IMPOSSIBLE"
    }else {
        return stalls.collect{ map[it] }.join()
    }

}

boolean insert(List stalls, int color){

    for(int i=0; i<stalls.size(); i++){

        int S1 = stalls[i]

        int S2
        if(i==0){
           S2 = stalls.last()
        }else if(i==stalls.size()-1){
           S2 = stalls.first()
        }else{
           S2 = stalls[i+1]
        }

        if (canBeNeightboor(S1, color) && canBeNeightboor(S2, color)) {

            stalls.add(i, color)
            return true
        }
    }

    return false

}

boolean canBeNeightboor(int S, int color) {

    int leftColor = S - 1
    if(leftColor == -1) {
        leftColor = 5
    }
    int rightColor = S + 1
    if(rightColor == 6) {
        rightColor = 0
    }

    return color != S && color != leftColor && color != rightColor

}
