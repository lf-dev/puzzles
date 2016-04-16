package Jam2016.qualification.countingSheeps

//def filename = "sample"
//def filename = "A-small-attempt0"
def filename = "A-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    int N = sc.nextInt()

    String c = count(N)
    w.writeLine("Case #${t+1}: $c")
    println("Case #${t+1}: $c")

}
w.close()

String count(int N) {

    if(N==0){
        return "INSOMNIA"
    }

    int n = 1
    boolean[] digits = new boolean[10]

    while(digits.any{!it}){

        String num = (n*N).toString()
        num.each {
            int index = Integer.parseInt(it)
            digits[index] = true
        }
        n++
    }

    return (n-1)*N

}