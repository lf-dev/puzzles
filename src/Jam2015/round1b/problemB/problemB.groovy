package Jam2015.round1b.problemB

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

    //w.writeLine("Case #${t+1}: ")
}
w.close()
