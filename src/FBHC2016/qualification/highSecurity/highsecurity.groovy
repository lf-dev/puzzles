package FBHC2016.qualification.highSecurity


def filename = "sample"
//def filename = "high_security"

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


