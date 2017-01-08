package FBHC2017.qualification.progressPie

test()

//def filename = "sample"
def filename = "progress_pie"

Scanner sc = new Scanner(new File(filename + '.txt'))
def file = new File(filename + '.out')
def w = file.newWriter()

int T = sc.nextInt()

println T
for (int t = 0; t < T; t++) {

    double P = sc.nextDouble()
    int X = sc.nextInt()
    int Y = sc.nextInt()

    String color = isBlack(P, X, Y) ? "black" : "white"

    w.writeLine("Case #${t + 1}: $color")
    println("Case #${t + 1}: $color")
}
w.close()

boolean isBlack(double P, int X, int Y) {

    if(!isIntoCircleRange(X, Y) || P == 0) {
        return false
    }

    if(P == 100) {
        return true;
    }

    int pointQuadrant = getPointQuadrant(X, Y)
    int percentQuadrant = getPercentQuadrant(P)

    if(pointQuadrant < percentQuadrant) {
        return true
    }
    else if(pointQuadrant > percentQuadrant) {
        return false
    }
    //quadrante do ponto == quadrante da porcentagem
    else {

        double alpha = getAlpha(P)

        double a = Math.tan(Math.toRadians(alpha))
        double b = 50 - a*50

        if(isInTheLine(X, Y, a, b)){
            return true
        }
        //Q1 e Q2
        else if(P >0 && P<=50) {
            return isAbove(X, Y, a, b)
        }
        //Q3 e Q4
        else {
            return !isAbove(X, Y, a, b)
        }
    }
}

int getPointQuadrant(int X, int Y) {

    if(X >= 50 && Y >= 50){
        return 1
    }
    else if( X >= 50 && Y < 50) {
        return 2
    }
    else if( X < 50 && Y <= 50) {
        return 3
    }
    else if(X < 50 && Y > 50) {
        return 4
    }
    else {
        println "quadrante de X,Y nao encontrado"
        return 5
    }
}

int getPercentQuadrant(double P) {

    //Q1
    if(P>=0 && P<=25){
        return 1
    }
    //Q2
    else if(P>25 && P<=50){
        return 2
    }
    //Q3
    else if(P>50 && P<=75){
        return  3
    }
    //Q4
    else {
        return 4
    }
}

boolean isInTheLine(int X, int Y, double a, double b) {
    return Y == a*X + b
}

boolean isAbove(int X, int Y, double a, double b) {
    return Y > a*X + b
}

double getAlpha(double P){

    //Q1
    if(P>=0 && P<=25){
        return 90 - 90*P/25
    }
    //Q2
    else if(P>25 && P<=50){
        return -1 * 90*(P-25)/25
    }
    //Q3
    else if(P>50 && P<=75){
        return  90 - 90*(P-50)/25
    }
    //Q4
    else {
        return -1 * 90*(P-75)/25
    }
}

boolean isIntoCircleRange(int X, int Y) {
    return distanceToCenter(X, Y) <= 50
}

double distanceToCenter(int X, int Y) {

    int c1 = Math.abs(X - 50)
    int c2 = Math.abs(Y - 50)

    return (c1**2 + c2**2)**0.5
}

void test() {

    assert distanceToCenter(100, 50) == 50
    assert distanceToCenter(50, 100) == 50
    assert distanceToCenter(50, 50) == 0

    assert isIntoCircleRange(100, 50)
    assert isIntoCircleRange(50, 100)
    assert isIntoCircleRange(50, 50)

    assert getPointQuadrant(50, 50) == 1
    assert getPointQuadrant(55, 55) == 1
    assert getPointQuadrant(55, 45) == 2
    assert getPointQuadrant(45, 45) == 3
    assert getPointQuadrant(45, 55) == 4

    assert getPercentQuadrant(0) == 1
    assert getPercentQuadrant(10) == 1
    assert getPercentQuadrant(35) == 2
    assert getPercentQuadrant(55) == 3
    assert getPercentQuadrant(85) == 4

    assert getAlpha(0) == 90
    assert getAlpha(12.5) == 45
    assert getAlpha(25) == 0
    assert getAlpha(37.5) == -45
    assert getAlpha(50) == -90
    assert getAlpha(62.5) == 45
    assert getAlpha(75) == 0
    assert getAlpha(87.5) == -45
    assert getAlpha(100) == -90

    assert !isBlack(0, 55, 55)
    assert !isBlack(12, 55, 55)
    assert isBlack(13, 55, 55)
    assert !isBlack(99, 99, 99)
    assert isBlack(87, 20, 40)
    assert isBlack(25, 55, 55)
    assert !isBlack(25, 55 ,45)
    assert isBlack(99, 50, 80)
    //Q1
    assert isBlack(99, 55, 80)
    //Q2
    assert isBlack(99, 55, 20)
    //Q3
    assert isBlack(99, 45, 20)
    //Q4
    assert !isBlack(99, 49, 80)
    assert isBlack(99, 20, 60)

    assert isBlack(25, 55, 55)
    assert isBlack(25, 100, 50)
    assert !isBlack(25, 55, 45)

    assert isBlack(50, 55, 45)
    assert isBlack(50, 50, 0)
    assert !isBlack(50, 45, 45)

    assert isBlack(75, 45, 45)
    assert isBlack(75, 0, 50)
    assert !isBlack(75, 45, 55)

    assert isBlack(100, 45, 55)

    println "tests ok"
}
