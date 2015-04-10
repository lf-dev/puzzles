package Jam2013.qualification.treasure

//def filename = "sample"
def filename = "D-small-practice"
//def filename = "D-large-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    int K = sc.nextInt()
    int N = sc.nextInt()

    List keys = [0]*201
    K.times{
        int key = sc.nextInt()
        keys[key]++
    }

    List Cs = []
    N.times{
        int type = sc.nextInt()
        Chest c = new Chest(num: it+1, type: type)

        int numKeys = sc.nextInt()
        numKeys.times {
            c.keys << sc.nextInt()
        }
        Cs << c
    }

    println "T: ${t+1}"
//    println "Keys: $keys"
    println "Chests:"
    Cs.each {
        println it
    }

    String resp = solve(Cs, keys) ?: "IMPOSSIBLE"
    w.writeLine("Case #${t+1}: ${resp}")
    println "Case #${t+1}: ${resp}"

}
w.close()

String solve(List Cs, List keys){

    if(Cs.every{it==null}){
        return ""
    }

    //possui bau mas nao chave
    if(keys.every{it==0}){
        return null
    }

    if(impossivel(Cs, keys)){
        return null
    }

    for(int i=0; i<Cs.size(); i++){

        Chest c = Cs[i]

        if(c != null && keys[c.type]>0) {
            //open
            keys[c.type]--
            Cs[i] = null
            c.keys.each { k->
                keys[k]++
            }

            String resp = solve(Cs, keys)
            if(resp != null){
                return "${c.num} $resp"
            }else{
                //close
                keys[c.type]++
                Cs[i] = c
                c.keys.each { k->
                    keys[k]--
                }
            }
        }
    }

    //exauriu as combinacoes
    return null
}

boolean impossivel(List Cs, List keys){
    List demanda = [0]*201
    List disponivel = [0]*201

    Cs.each { c->
        if(c!=null){
            demanda[c.type]++
            c.keys.each{ k->
                disponivel[k]++
            }
        }
    }

//    println demanda
//    println disponivel
//    println keys

    for(int i=0; i<201; i++) {
        if(demanda[i] > disponivel[i] + keys[i]){
            return true
        }
    }
    return false
}

class Chest {

    int num
    int type
    List keys = []

    String toString(){
        "$num $type $keys"
    }

}
