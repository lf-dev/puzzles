package Jam2014.qualification.cookieClickerAlpha

def filename = "B-large-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()
sc.nextLine()

println T
for(int t = 0 ; t < T ; t++ ) {

    String line = sc.nextLine()
    def(double C,double F,double X) = line.split(' ').collect{ it as Double}
    double K = 2.0

    println "${C} ${F} ${X}"

    double min = minimize(K, F, X, C)
    println min

    w.writeLine("Case #${t+1}: ${min}")
}
w.close()

double minimize(double K, double F, double X, double C) {

    int numFarm = 0
    double currentCost = Double.MAX_VALUE
    double costNumFarms = 0
    while(true) {

        costNumFarms = costNFarms(costNumFarms, C, numFarm, F, K)
        double custoX = costX(X, K, numFarm, F)

//        double newCost = cost(K, F, X, C, numFarm)
        double newCost = costNumFarms + custoX

        if(newCost > currentCost) {
            return currentCost
        }
        else {
            currentCost = newCost
            numFarm++
        }
    }
}

double costNFarms(double costNAnterior, double C, double N, double F, double K) {
    if(N == 0) {
        return 0
    }
    return costNAnterior + C/( K + (N*F) - F)
}

double costX(double X, double K, double N, double F) {
    return  X / (K + N*F)
}

double cost(double K, double F, double X, double C, double N) {

    double a = 0
    for(int n=1; n<=N; n++) {
        double div = ( K + (n*F) - F)
        a += 1 / div
    }

    return C*a + ( X / (K + N*F))

}

