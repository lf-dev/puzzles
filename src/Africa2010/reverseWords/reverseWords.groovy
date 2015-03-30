package Africa2010.reverseWords

def filename = "B-large-practice"

def input = new File(filename + '.in').readLines()
def numberOfTestCases = input[0] as Integer

def file = new File(filename + '.out')
def w= file.newWriter()

for(int n = 0 ; n < numberOfTestCases ; n++ ) {
    def line = input[n+1] as String

    String r = reverse(line)

    w.writeLine("Case #${n+1}: ${r}")
}

w.close()

String reverse(String line) {

    List words = line.tokenize(' ')
    return words.reverse().join(' ')
}
