package Jam2015.qualification.standingOvation

//def filename = "sample"
//def filename = "A-small-attempt1"
def filename = "A-large"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    int Smax = sc.nextInt()
    String audience = sc.next()

    int invite = invite(audience)
    w.writeLine("Case #${t+1}: $invite")

}
w.close()

int invite(String audience) {

    int acum = 0
    int invite = 0
    for(int i=0; i<audience.size(); i++) {

        int num = audience[i] as int

        if(i>acum && num>0){
            invite += i - acum
            acum += invite
        }
        acum += num
    }
    return invite

}