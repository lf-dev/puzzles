package Jam2008.round1a.MinimumScalarProduct

def filename = "A-large-practice"

def input = new File(filename + '.in').readLines()
def numberOfTestCases = input[0] as Integer

def file = new File(filename + '.out')
def w= file.newWriter()

for(int t = 0 ; t < numberOfTestCases ; t++ ) {

    def n = input[1+t*3] as Integer
    def x = input[2+t*3].split(' ').collect { it as Long}
    def y = input[3+t*3].split(' ').collect { it as Long}

    Long s = msp(n, x, y)

    w.writeLine("Case #${t+1}: ${s}")
}
w.close()

Long msp(int n, List x, List y) {

    x = x.sort()
    y = y.sort{ it * -1}

    List p = [0]*n

    n.times { i->
        p[i] = x[i] * y[i]
    }

    return p.sum()

}

String asciiToT9(String message) {

    String t9Message = ""

    message.each {

        String charT9 = toT9[it]

        if(t9Message && t9Message[t9Message.size() - 1] == charT9[0]){
            t9Message += ' '
        }
        t9Message += charT9
    }

    return t9Message
}


String leterToT9(char c) {

    if(c == 'z') {
        return '9999'
    }
    if(c == ' ') {
        return '0'
    }

    char a = 'a' as char

    int pos = ((c - a)/3) + 2

    int mod = (c - a)%3 + 1

    return "${pos}" * mod
}
