package Jam2010.round1c.ropeIntranet

def filename = "A-large-practice"

def input = new File(filename + '.in').readLines()
def file = new File(filename + '.out')
def w= file.newWriter()

int T = input[0] as Integer

int line = 1
for(int t = 0 ; t < T ; t++ ) {

    int N = input[line++] as Integer

    List wires = []
    N.times {
        wires << input[line++].split(' ').collect { it as Integer}
    }

    long total = rope(wires)

    w.writeLine("Case #${t+1}: ${total}")
}
w.close()

Long rope(List wires) {

    Long total = 0
    wires.each { w->

        total += wires.count { w2 ->
            w[0] < w2[0] && w[1] > w2[1]
        }
    }

    return total

}

