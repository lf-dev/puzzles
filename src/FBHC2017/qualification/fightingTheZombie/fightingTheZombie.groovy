package FBHC2017.qualification.fightingTheZombie

test()

//def filename = "sample"
def filename = "fighting_the_zombie"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()
println T
for (int t = 0; t < T; t++) {

    int H = sc.nextInt()
    int S = sc.nextInt()
    List spells = []

    S.times {
        String s = sc.next()
        spells << parseSpell(s)
    }

    double bestProbability = solve(H, spells)
    String bestFmt = String.format("%.6f", bestProbability).replace(',','.')

    w.writeLine("Case #${t + 1}: $bestFmt")
    println("Case #${t + 1}: $bestFmt")
}
w.close()

double solve(H, spells) {

    double bestProb = 0.0
    for(Map spell : spells) {

        List combs = combine3(spell.x, spell.y)

        if(spell.z > 0) {
            combs = [0]*spell.z + combs
        }
        else if(spell.z < 0 ) {
            int a = spell.z * -1
            a.times {
                if(combs.size()>0){
                    combs.remove(0)
                }
            }

//            combs = combs[(spell.z*-1)..(combs.size()-1)]
        }

        double possibilidades = 0.0
        for(int i=H-1; i<combs.size(); i++){
            possibilidades += combs[i]
        }

        double probabilidade
        if(combs.size() == 0){
            probabilidade = 0.0
        }else {
            double espacoAmostral = combs.sum()
            probabilidade = possibilidades/espacoAmostral
        }

        if(probabilidade > bestProb){
            bestProb = probabilidade
        }
    }
    return bestProb
}

Map parseSpell(String str) {

    Map spell = [:]
    spell.x = str.substring(0, str.indexOf('d')) as Integer

    int signIndex = [str.indexOf('-'), str.indexOf('+')].max()
    if(signIndex == -1){
        signIndex = str.length()
    }
    spell.y = str.substring(str.indexOf('d')+1, signIndex) as Integer

    if(signIndex < str.length()){
        spell.z = str.substring(signIndex, str.length()) as Integer
    }else {
        spell.z = 0
    }
    return spell
}

List combine3(int rolls, int dice) {

    int numElements = dice * rolls
    //inicio das combinacoes sao 0 casos até rolls-1
    int offset = rolls-1
    //half representa a metade depois de considerar o offset
    int half = Math.ceil((numElements-offset)/2)

    List total = [0.0]*numElements

    for(int i=0; i<half; i++) {
        total[i+offset] = combine3(rolls, dice, i+offset+1)

        int sibling = numElements - 1 - i
        total[sibling] = total[i+offset]
    }
    return total
}

//http://mathforum.org/library/drmath/view/52207.html
double combine3(int rolls, int dice, int sum) {

    double total = 0
    double kmax = Math.floor((sum-rolls)/dice)

    for(k=0; k<=kmax; k++) {
        double p1 = (-1)**k
        double p2 = combination(rolls, k)
        double p3 = combination(sum -(dice*k) -1, rolls-1)
        double p = p1*p2*p3

        total += p
    }

    return total;
}

double combination(int n, int k) {

    List numeradores = []
    List denominadores = []

    //combinacao: n!/(k!*(n-k)!)
    //elaborando temos:
    //numeratores: k+1 * k+2 * ... * n
    //denominadores: 1 * 2 * ... * n-k

    for(int i=k+1; i<=n; i++) {
        numeradores << (i as Double)
    }

    for(int i=1; i<=(n-k); i++) {
        denominadores << (i as Double)
    }

    //completa o array de numeradores ou denominadores com valores neutros
    if(denominadores.size() < numeradores.size()){
        (numeradores.size()-denominadores.size()).times {
            denominadores << 1.0
        }
    }
    else if(denominadores.size() > numeradores.size()) {
        (denominadores.size()-numeradores.size()).times {
            numeradores << 1.0
        }
    }

    double total = 1

    //realiza multiplicacao das frações da combinacao para evitar numeros muito grandes que estouram o limite do double
    for(int i=0; i<numeradores.size(); i++){
        total = total * (numeradores[i]/denominadores[i])
    }

    return total
}

List combine2(int rolls, int dice) {

    List total = [0]*(dice*rolls)
    for(int i=0; i<total.size(); i++) {
        println "combinations for ${i+1}"
        total[i] = combine2(rolls, dice, i+1, 0)
    }
    return total
}

int combine2(int rolls, int dice, int equalsTo, int acumulado) {

    if(acumulado + rolls > equalsTo){
        return 0
    }

    if(rolls == 0) {
        return acumulado == equalsTo ? 1 : 0
    }

    int total = 0
    for(int i=1; i<=dice; i++) {
        total += combine2(rolls-1, dice, equalsTo, acumulado + i)
    }
    return total
}

List combine(int rolls, int dice) {

    List total = [0]*(dice*rolls)
    List comb = [0]*rolls

    combine(rolls, dice, total, comb)

    return total
}

void combine(int rolls, int dice, List total, List comb) {

    if(rolls == 0) {
        int index = comb.sum()
        total[index-1] += 1
    }else {
        for(int i=1; i<=dice; i++){
            comb[rolls-1] = i
            combine(rolls-1, dice, total, comb)
        }
    }
}

void test() {

    assert parseSpell("2d4") == [x: 2, y:4, z:0]
    assert parseSpell("1d8") == [x: 1, y:8, z:0]
    assert parseSpell("10d6-10") == [x: 10, y:6, z:-10]
    assert parseSpell("10d6+1") == [x: 10, y:6, z:1]
    assert parseSpell("1d4+4") == [x: 1, y:4, z:4]
    assert parseSpell("2d4") == [x: 2, y:4, z:0]
    assert parseSpell("3d4-4") == [x: 3, y:4, z:-4]
    assert parseSpell("10d4") == [x: 10, y:4, z:0]
    assert parseSpell("5d8") == [x: 5, y:8, z:0]
    assert parseSpell("2d20") == [x: 2, y:20, z:0]
    assert parseSpell("1d10") == [x: 1, y:10, z:0]
    assert parseSpell("1d10+1") == [x: 1, y:10, z:1]
    assert parseSpell("1d10+2") == [x: 1, y:10, z:2]
    assert parseSpell("1d10+3") == [x: 1, y:10, z:3]

    Random r = new Random()

    1000.times {
        int rand = r.nextInt(5)
        assert rand < 5
        assert rand >= 0
    }

    Map spell = [x:5, y:6, z:0]
    1000.times {

        int total = roll(spell, r)
        assert total >= 5
        assert total <= 5*6
    }

    assert combination(4, 2) == 6
    assert combination(6, 4) == 15
    assert combination(20, 1) == 20

    assert combine(2, 4) == combine3(2, 4)
    assert combine(3, 4) == combine3(3, 4)
    assert combine(4, 4) == combine3(4, 4)
    assert combine(2, 6) == combine3(2, 6)
    assert combine(3, 6) == combine3(3, 6)
    assert combine(4, 6) == combine3(4, 6)

    println "testes ok"
}

//================================================== Monte Carlo==============================================
double monteCarlo(int H, List spells, Random r) {

    int numSimulations = 10**7
    double bestProbability = 0

    for(Map spell : spells) {

        int numSuccess = 0;
        for(int i=0; i<numSimulations; i++) {

            if(roll(spell, r) >= H){
                numSuccess++
            }
        }

        double probability = numSuccess/numSimulations

        if(probability > bestProbability){
            bestProbability = probability
        }
    }

    return bestProbability
}

int roll(Map spell, Random r) {

    int total = 0
    for(int i=0; i<spell.x; i++){
        total += r.nextInt(spell.y) + 1
    }
    total += spell.z
    return total

}
