package FBHC2017.round1.fightTheZombies

test()

def filename = "sample"
//def filename = "fight_the_zombies"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    int N = sc.nextInt()
    int R = sc.nextInt()

    List Ns = []
    N.times {
        int X = sc.nextInt()
        int Y = sc.nextInt()
        Ns << new Point(X, Y)
    }

    int min = best(Ns, N, R)

    w.writeLine("Case #${t + 1}: $min")
    println("Case #${t + 1}: $min")
}


w.close()

int best(List Ns, int N, int R) {

    Ns = Ns.sort()

    int bestScore = 0
    Point bestMoveCenter = new Point(0, 0)
    Point bestDestroiCenter = new Point(0, 0)

    for(int i=0; i<N; i++) {

        Point p1 = Ns[i]
        for(int j=i+1; j<N; j++){

            Point p2 = Ns[j]
            List centers = squares(p1, p2, R)

            for(int c=0; c<centers.size(); c++){

                Point center = centers[c]
                List moved = zombiesIntoTheSquare(Ns, center, R)

                for(int k=0; k<N; k++){
                    Point p3 = Ns[k]

                    for( m=k+1; m<N; m++) {
                        Point p4 = Ns[m]

                        List centers2 = squares(p3, p4, R)

                        for(int c2=0; c2<centers2.size(); c2++){

                            Point center2 = centers2[c2]
                            List destroied = zombiesIntoTheSquare(Ns, center2, R)

                            int score = (destroied + moved).unique().size()
                            if(score > bestScore){
                                bestScore = score
                                bestMoveCenter = center
                                bestDestroiCenter = center2
                            }
                        }
                    }
                }
            }
        }
    }

//    println bestScore
//    println bestMoveCenter
//    println bestDestroiCenter

    return bestScore
}

List zombiesIntoTheSquare(List Ns, Point center, double R) {

    List zombies = []
    for(int i=0; i<Ns.size(); i++){
        Point zombie = Ns[i]
        if(intoSquare(center, R, zombie)){
            zombies << zombie
        }
    }
    return zombies
}

boolean intoSquare(Point center, double R, Point p){

    double x = Math.abs(center.x - p.x)
    double y = Math.abs(center.y - p.y)

    return x <= R/2 && y <= R/2
}

List squares(Point p1, Point p2, double R) {

    double d = Point.Distance(p1, p2)

    if(d > maxDistanceForSquare(R)){
        return [p1]
    }

    double dx = Math.abs(p2.x - p1.x)
    double dy = Math.abs(p2.y - p1.y)

    List centers = []

    if(p1.x < p2.x){

        if(p1.y < p2.y) {
            centers << new Point(p1.x + R/2, p1.y + dy -R/2)
            centers << new Point(p1.x + dx - R/2, p1.y + R/2)
        }else {
            centers << new Point(p1.x + R/2, p1.y - dy + R/2)
            centers << new Point(p1.x + dx -R/2, p1.y - R/2)
        }
    }else{

        if(p1.y < p2.y) {
            centers << new Point(p1.x - dx + R/2, p1.y + R/2)
            centers << new Point(p1.x - R/2, p1.y + dy - R/2)
        }else {
            centers << new Point(p1.x - R/2, p1.y - dy + R/2)
            centers << new Point(p1.x - dx + R/2, p1.y - R/2)
        }
    }

    return centers
}

double maxDistanceForSquare(double side){
    return side*(2**0.5)
}

class Point implements Comparable<Point>{
    double x,y;

    Point(double x, double y){
        this.x = x
        this.y = y
    }

    String toString() {
        return "[$x, $y]"
    }

    boolean equals(Object p) {
        double d = Distance(this, p)
        return d < 0.0000001
    }

    int compareTo(Point p) {

        if(this.x == p.x){
            return this.y.compareTo(p.y)
        }
        return this.x.compareTo(p.x)
    }

    static double Distance(Point p1, Point p2) {

        int c1 = Math.abs(p1.x - p2.x)
        int c2 = Math.abs(p1.y - p2.y)

        return (c1**2 + c2**2)**0.5
    }
}

void test(){

    assert intoSquare(new Point(0, 0), 2, new Point(1, 1))
    assert intoSquare(new Point(0, 0), 2, new Point(1, -1))
    assert intoSquare(new Point(0, 0), 2, new Point(-1, 1))
    assert intoSquare(new Point(0, 0), 2, new Point(-1, -1))
    assert intoSquare(new Point(0, 0), 2, new Point(0, 0))
    assert intoSquare(new Point(0, 0), 2, new Point(2, 2)) == false

    List points = [new Point(2, 2), new Point(3, 3), new Point(1, 0), new Point(0, 1)]
    assert points.sort() == [new Point(0, 1), new Point(1, 0), new Point(2, 2), new Point(3, 3)]

    //distancia muito grande
    List sqs = squares(new Point(0, 0), new Point(2, 2), 1)
    assert sqs == [new Point(0, 0)]

    //quina esquerda para direita
    sqs = squares(new Point(0, 0), new Point(2, 2), 2)
    assert sqs.sort() == [new Point(1, 1), new Point(1, 1)]

    //quina direita prar esquerda
    sqs = squares(new Point(2, 2), new Point(0 , 0), 2)
    assert sqs.sort() == [new Point(1, 1), new Point(1, 1)]

    //p1.x < p2.x && p1.y < p2.y
    sqs = squares(new Point(0, 0), new Point(2, 2), 4)
    assert sqs.sort() == [new Point(0, 2), new Point(2, 0)]

    //p1.x > p2.x && p1.y > p2.y
    sqs = squares(new Point(2, 2), new Point(0, 0), 4)
    assert sqs.sort() == [new Point(0, 2), new Point(2, 0)]

    //p1.x < p2.x && p1.y > p2.y
    sqs = squares(new Point(0, 2), new Point(2 ,0), 4)
    assert sqs.sort() == [new Point(0, 0), new Point(2, 2)]

    //p1.x > p2.x && p1.y < p2.y
    sqs = squares(new Point(2, 0), new Point(0 ,2), 4)
    assert sqs.sort() == [new Point(0, 0), new Point(2, 2)]

    List uniques = ([new Point(0, 0),new Point(1, 1)] + [new Point(2, 2), new Point(0, 0)]).unique().sort()
    assert uniques == [new Point(0, 0), new Point(1, 1), new Point(2, 2)]

    List Ns4 = [new Point(8, 5), new Point(3, 6), new Point(9, 2), new Point(4, 5), new Point(3, 2), new Point(1, 8), new Point(2, 8)]
    assert best(Ns4, Ns4.size(), 3) == 6

    List Ns2 = [new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3)]
    assert best(Ns2, Ns2.size(), 1) == 4

    List Ns3 = [new Point(0, 0), new Point(2, 2), new Point(4, 4), new Point(6, 6)]
    assert best(Ns3, Ns3.size(), 1) == 2

    long init = System.currentTimeMillis()
    1.times {
        print "."
        List Ns50 = []
        50.times {
            Ns50 << new Point(it, it)
        }
        assert best(Ns50, Ns50.size(), 1) == 4
    }
    long tempo = System.currentTimeMillis() - init
    println tempo/1000


    println "test ok!"
}
