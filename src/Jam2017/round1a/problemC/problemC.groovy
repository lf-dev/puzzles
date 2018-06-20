package Jam2017.round1a.problemC

def filename = "sample"
//def filename = "C-small-attempt0"
//def filename = "C-large"

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
