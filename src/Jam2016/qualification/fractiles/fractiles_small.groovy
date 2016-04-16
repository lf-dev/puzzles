package Jam2016.qualification.fractiles

//def filename = "sample"
def filename = "D-small-attempt0"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    int K = sc.nextInt()
    int C = sc.nextInt()
    int S = sc.nextInt()

    String seq = clean(K,C,S)
    w.writeLine("Case #${t+1}: $seq")
    println("Case #${t+1}: $seq")

}
w.close()

String clean(int K, int C, int S) {

    List seq = []

    long index = 1
    long step = K**(C-1)

    for(int s=0; s<S; s++) {
        seq << index
        index += step
    }

    return seq.join(" ")
}