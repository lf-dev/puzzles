package Jam2014.qualification.war

def filename = "D-large-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    int N = sc.nextInt()
    sc.nextLine()
    List naomi = sc.nextLine().split(' ').collect{ it as Double}.sort()
    List ken = sc.nextLine().split(' ').collect{ it as Double}.sort()

    int pWar = war(naomi.collect(), ken.collect(), N)
    int dWar = dWar(naomi.collect(), ken.collect(), N)
    println "${dWar} ${pWar}"

    w.writeLine("Case #${t+1}: ${dWar} ${pWar}")

}
w.close()

int war(List naomi, List ken, int N) {

    int pwar = 0
    for(int n=0; n<N; n++) {

        Double Cn = naomi.remove(naomi.size()-1)

        Double Ck = ken.find{it > Cn}
        if(Ck == null){
            pwar++
            Ck = ken.remove(0)
        }else {
            ken.remove(Ck)
        }
    }

    return pwar
}

int dWar(List naomi, List ken, int N) {

    int pwar = 0
    for(int n=0; n<N; n++) {

        Double Cn = naomi.remove(0)

        Double Tn
        if(Cn > ken.min()){
            Tn = ken.max() + 0.0001
        }else {
            Tn = ken.max() - 0.0001
        }

        Double Ck = ken.find{it > Tn}
        if(Ck == null){
            pwar++
            Ck = ken.remove(0)
        }else {
            ken.remove(Ck)
        }
    }

    return pwar

}
