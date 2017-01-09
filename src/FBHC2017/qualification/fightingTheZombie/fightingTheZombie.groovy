package FBHC2017.qualification.fightingTheZombie

test()

def filename = "sample"
//def filename = "fighting_the_zombie"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()
Random r = new Random()

//println T
//for (int t = 0; t < T; t++) {
//
//    int H = sc.nextInt()
//    int S = sc.nextInt()
//    List spells = []
//
//    S.times {
//        String s = sc.next()
//        spells << parseSpell(s)
//    }
//
//    double bestProbability = monteCarlo(H, spells, r)
//    String bestFmt = String.format("%.6f", bestProbability)
//
//    w.writeLine("Case #${t + 1}: $bestFmt")
//    println("Case #${t + 1}: $bestFmt")
//}
//w.close()

printCombinations(2, 6)
println GF(2, 6)
//printCombinations(3, 4)
//printCombinations(4, 4)

void printCombinations(int rolls, int dice){
    List combinations = combine(rolls, dice)
    int total = combinations.sum()
    List percents = combinations.collect{ it/total }
    List acumulado = percents.collect()

    for(int i=1; i<acumulado.size(); i++){
        acumulado[i] += acumulado[i-1]
    }

    println combinations
//    println percents
//    println acumulado
}

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

List GF(int rolls, int dice) {

    int max = rolls*dice
    int min = rolls

    List sols = [0]*max

    for(int x=min; x<=max; x++) {

        double a = x**rolls
        a = a * (1 - x**6)
        a = a / ((1- x)**rolls)

        sols[x-1] = a
    }

    return sols

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

    println "testes ok"

}