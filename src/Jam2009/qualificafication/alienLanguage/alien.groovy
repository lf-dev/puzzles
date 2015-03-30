package Jam2009.qualificafication.alienLanguage

def filename = "A-large-practice"

def input = new File(filename + '.in').readLines()
def file = new File(filename + '.out')
def w= file.newWriter()

def (int L, int D, int N)  = input[0].split(' ').collect { it as Long}

Set dictionary = []
for(int d = 1 ; d <= D ; d++ ) {
    dictionary << input[d]
}

for(int n = 1 ; n <= N ; n++ ) {

    String word = input[D + n]

    List chars = []

    while (!word.isEmpty()) {
        //println word
        if(word.charAt(0) != '(') {
            chars << word.charAt(0)
            word = word.size() > 1 ? word[1..-1] : ''
        } else {
            def f = word.indexOf(')')
            chars << word[1..f-1]
            if(f == word.size() -1) {
                word = ''
            } else {
                word = word[f+1..-1]
            }
        }
    }

    total = 0

    println chars
    String g = chars.remove(0)
    calc(g, chars,'', L, dictionary)

    w.writeLine("Case #${n}: ${total}")
}
w.close()

def calc(def group, List groups, String word, int L, Set dictionary) {

    if(word.size() == L){
        if(dictionary.contains(word)){
            total++
        }
    }else {

        String g = groups[0]
        List subGroups = (groups.size()==1 || groups.size()==0) ? [] : groups[1..-1]
        group.each { c->
            String newWord = word + c
            if(dictionary.any { it.startsWith(newWord)}){
                calc(g, subGroups, newWord, L, dictionary)
            }
        }
    }
}

