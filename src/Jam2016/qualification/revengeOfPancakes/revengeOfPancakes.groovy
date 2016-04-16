package Jam2016.qualification.revengeOfPancakes

//def filename = "sample"
//def filename = "B-small-attempt0"
def filename = "B-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    String stack = sc.next()

    int numFlips = flip(stack)
    w.writeLine("Case #${t+1}: $numFlips")
    println("Case #${t+1}: $numFlips")

}
w.close()

int flip(String stack) {

    String p = stack[0]
    int numFlips = 0

    for(int i=0; i<stack.size(); i++){

        if(stack[i] != p){
            numFlips++
            p = stack[i]
        }
    }

    if(p == '-'){
        numFlips ++
    }

    return numFlips
}