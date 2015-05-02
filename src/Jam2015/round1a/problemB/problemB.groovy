package Jam2015.round1a.problemB

//def filename = "sample"
//def filename = "B-small-attempt0"
def filename = "B-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

long init = System.currentTimeMillis()

println T
for(int t = 0 ; t < T ; t++ ) {

    long B = sc.nextLong()
    long N = sc.nextLong()

    sc.nextLine()
    List ms = sc.nextLine().split(' ').collect{ it as Long}

    println ms
    int m = solve(N, B, ms)

    w.writeLine("Case #${t+1}: ${m+1}")
}
w.close()

long solve(long N, long B, List ms) {

    long lcm = lcm(ms)
    println lcm


    long N2
    List M2 = [0] * ms.size()
    if(lcm > 0) {
        long nPorCiclo = 0
        ms.each {
            nPorCiclo += lcm / it
        }

        N2 = N % nPorCiclo
        if(N2 == 0){
            N2 = nPorCiclo
        }
    }
    else{
        N2 = N
        M2 = ms.collect()
    }




    while (true) {
        long min = M2.min()

        for(int i=0; i<M2.size(); i++){

            M2[i] -= min
            if(M2[i] <= 0){
                N2--

                if(N2 == 0){
                    return i
                }else {
                    M2[i] = ms[i]
                }
            }
        }
    }

    return -1
}


private static BigDecimal lcm(long a, long b)
{
    return a * (b / gcd(a, b));
}

private static BigDecimal lcm(List input)
{
    long result = input[0];
    for(int i = 1; i < input.size(); i++) result = lcm(result, input[i]);
    return result;
}

private static BigDecimal gcd(long a, long b)
{
    while (b > 0)
    {
        long temp = b;
        b = a % b; // % is remainder
        a = temp;
    }
    return a;
}