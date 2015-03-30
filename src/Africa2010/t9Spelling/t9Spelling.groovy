package Africa2010.t9Spelling

def filename = "C-large-practice"

def input = new File(filename + '.in').readLines()
def numberOfTestCases = input[0] as Integer

def file = new File(filename + '.out')
def w= file.newWriter()

toT9 = [:]
toT9['a'] = '2'
toT9['b'] = '22'
toT9['c'] = '222'
toT9['d'] = '3'
toT9['e'] = '33'
toT9['f'] = '333'
toT9['g'] = '4'
toT9['h'] = '44'
toT9['i'] = '444'
toT9['j'] = '5'
toT9['k'] = '55'
toT9['l'] = '555'
toT9['m'] = '6'
toT9['n'] = '66'
toT9['o'] = '666'
toT9['p'] = '7'
toT9['q'] = '77'
toT9['r'] = '777'
toT9['s'] = '7777'
toT9['t'] = '8'
toT9['u'] = '88'
toT9['v'] = '888'
toT9['w'] = '9'
toT9['x'] = '99'
toT9['y'] = '999'
toT9['z'] = '9999'
toT9[' '] = '0'

for(int n = 0 ; n < numberOfTestCases ; n++ ) {
    def message = input[n+1] as String

    String r = asciiToT9(message)

    w.writeLine("Case #${n+1}: ${r}")
}

w.close()

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
