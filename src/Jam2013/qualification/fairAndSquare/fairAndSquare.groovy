package Jam2013.qualification.fairAndSquare

//def filename = "sample"
//def filename = "C-small-practice"
def filename = "C-large-practice-1"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

Long min = 1
Long max = 10**14

println "Montando cache entre $min e $max"
//List fairsAndSquares = [1,4,9] + findFes(min, max)
List fairsAndSquares = [1, 4, 9, 121, 484, 10201, 12321, 14641, 40804, 44944, 1002001, 1234321, 4008004, 100020001, 102030201, 104060401, 121242121, 123454321, 125686521, 400080004, 404090404, 10000200001, 10221412201, 12102420121, 12345654321, 40000800004, 1000002000001, 1002003002001, 1004006004001, 1020304030201, 1022325232201, 1024348434201, 1210024200121, 1212225222121, 1214428244121, 1232346432321, 1234567654321, 4000008000004, 4004009004004]

println fairsAndSquares

for(int t = 0 ; t < T ; t++ ) {

    Long A = sc.nextLong()
    Long B = sc.nextLong()

    println "A: $A B: $B"

    int tot = fairsAndSquares.count {
        it>=A && it<= B
    }

    w.writeLine("Case #${t+1}: ${tot}")
}
w.close()

List findFes(Long A, Long B) {

    List fes = []
    Long i = 1
    while(true) {

        for(int j=-1; j<10; j++){

            Long fair = buildFair(i,j)

            if(fair < A || fair > B) {
                continue
            }

            Long square = squarePuss(fair)
            if(square != null && isPalindrome(square)){
                fes << fair
            }
        }

        i++

        if(buildFair(i,-1) > B){
            return fes
        }
    }
}

Long buildFair(Long num, Long middle){

    String s = num
    String m = (middle==-1)?"":middle

    s = s + m + s.reverse()
    return s as Long
}

boolean isPalindrome(Long num) {

    String s = "${num}"

    for(int i=0; i<s.size()/2; i++){
        if(s[i] != s[s.size()-1-i]){
            return false
        }
    }
    return true

}

Long squarePuss(Long square){
    Long a = square**(1/2)
    if(a*a == square) {
        return a
    }
    return null
}

Long square(Long square){
    squarePvt(square, 0, square)
}

Long squarePvt(Long square, Long left, Long right){

    Long num = left + (right - left)/2
    Long numSquare = num*num

    if(numSquare == square) {
        return num
    }else if(left == right-1) {
        return null
    }else if(numSquare > square){
        return squarePvt(square, left, num)
    }else if(numSquare < square){
        return squarePvt(square, num, right)
    }

}

Long count1Digit(Long A, Long B) {

    Long count = 0
    for(int i=0; i<10; i++){

        if(i > B) {
            return count
        }

        if(i >= A) {
            if(i==1 || i == 4 || i == 9){
                count++
            }
        }
    }
    return count
}