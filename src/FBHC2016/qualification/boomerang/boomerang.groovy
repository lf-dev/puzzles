package FBHC2016.qualification.boomerang


//def filename = "sample"
def filename = "boomerang_constellations"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    List stars = []

    N.times {
        stars << [sc.nextInt(), sc.nextInt()]
    }

    int numBoomerangs = numBoomerangs(N, stars)
    w.writeLine("Case #${t + 1}: $numBoomerangs")
    println("Case #${t + 1}: $numBoomerangs")
}
w.close()

long numBoomerangs(int N, List stars) {

    long total = 0
    for (int i = 0; i < N; i++) {

        List star = stars[i]
        Map distances = [:]
        for (int j = 0; j < N; j++) {

            double distance = distance(star, stars[j])

            if (!distances[distance]) {
                distances[distance] = 1
            } else {
                distances[distance]++
            }
        }

        distances.each { k, v ->

            if (v >= 2) {
                total += combinatorial(v)
            }
        }
    }

    return total
}

int combinatorial(int n) {

    return n * (n - 1) / 2

}

double distance(List star1, List star2) {

    int x1 = star1[0]
    int y1 = star1[1]

    int x2 = star2[0]
    int y2 = star2[1]

    int c1 = Math.abs(x1 - x2)
    int c2 = Math.abs(y1 - y2)

    return (c1**2 + c2**2)**0.5
}
