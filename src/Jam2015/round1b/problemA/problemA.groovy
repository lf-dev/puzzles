package Jam2015.round1b.problemA

//def filename = "sample"
def filename = "A-small-attempt0"
//def filename = "A-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

println T
for(int t = 0 ; t < T ; t++ ) {

   long N = sc.nextLong()
    println N

   long count = countNums(N, 1, 1)

   w.writeLine("Case #${t+1}: ${count}")
}
w.close()

long countNums(long N, long n, long count){

    if(n == N){
        return count
    }
    else if(n >= N){
        return Long.MAX_VALUE
    }
    else {

        long plus = n + 1
        long fliped = flip(n)

        long countFliped
        if(fliped < plus || fliped > N){
            countFliped = Long.MAX_VALUE
        }else {
            countFliped = countNums(N, fliped, count + 1)
        }

        long countPlus = countNums(N, plus, count + 1)
        return [countPlus, countFliped].min()
    }
}

long flip(long n){
    long fliped = n.toString().reverse() as Long
    long refliped = fliped.toString().reverse() as Long

    if(refliped != n){
        return n
    }else {
        return fliped
    }
}
