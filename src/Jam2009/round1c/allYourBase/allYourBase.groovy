package Jam2009.round1c.allYourBase

def filename = "sample"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

for(int t = 0 ; t < T ; t++ ) {

    Map convs = [:]
    int val = 0

    List code = sc.next().toList()
    code.each { c->

        if(!convs.containsKey(c)){

            if(val == 0){
                convs[c] = 1
            }else if(val == 1){
                convs[c] = 0
            }else{
                convs[c] = val
            }
            val++
        }
    }

    long min = convert(code, convs)
    println min

    w.writeLine("Case #${t+1}: ${min}")
}
w.close()

long convert(List code, Map convs) {

    long total = 0
    long base = convs.size()

    if(base == 1) {
        base = 2
    }

    code.reverse().eachWithIndex { c, i->
        total += convs[c]*base**i
    }

    return total
}
