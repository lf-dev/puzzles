package Jam2014.qualification.magicTric

//def filename = "sample"
def filename = "A-small-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

println T
for(int t = 0 ; t < T ; t++ ) {

    int ans1 = sc.nextInt()
    sc.nextLine()
    List matrix1 = []
    4.times {
        matrix1 << sc.nextLine()
    }

    int ans2 = sc.nextInt()
    sc.nextLine()
    List matrix2 = []
    4.times {
        matrix2 << sc.nextLine()
    }

    List linha1 = matrix1[ans1-1].split(' ')
    List linha2 = matrix2[ans2-1].split(' ')

    String resp = magic(linha1, linha2)

    w.writeLine("Case #${t+1}: ${resp}")

}
w.close()

String magic(List linha1, List linha2) {

    List nums = []
    linha1.each { l1 ->
        if(linha2.contains(l1)){
            nums << l1
        }
    }

    if(nums.size() == 1) {
        return nums.first()
    }else if(nums.size() > 1){
        return "Bad magician!"
    }else {
        return "Volunteer cheated!"
    }

}