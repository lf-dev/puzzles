package FBHC2016.round2.problem3

import FBHC2016.round1.laudroMatt.WhashingMachine

go()

def go() {
    def filename = "sample"
//    def filename = ""

    Scanner sc = new Scanner(new File(filename + '.txt'))
    def file = new File(filename + '.out')
    def w = file.newWriter()

    int T = sc.nextInt()

    println T
    for (int t = 0; t < T; t++) {

        int N = sc.nextInt()
        List ladders = []

        N.times { i ->
            ladders << new Ladder(x: sc.nextInt(), h: sc.nextInt(), i: i)
        }

        long resp = cost(ladders,N)
        w.writeLine("Case #${t + 1}: $resp")
        println("Case #${t + 1}: $resp")
    }
    w.close()
}


long cost(List ladders, int N) {

//    PriorityQueue heap = new PriorityQueue(N)
//    ladders.each {
//        heap.add(it)
//    }

   for(int i=0; i<N; i++){
       if(i-1 >= 0){
           ladders[i].prev = ladders[i-1]
       }

       if(i+1 < N){
           ladders[i].next = ladders[i+1]
       }
   }

    ladders.sort{ it.h }

    for(int i=0; i<N; i++){

        Ladder node = ladders[i]
        List dists = []
        while(node.next != null && node.next.h == node.h){
            dists << node.next.x - node.x
            node = node.next
        }

        if(ladders[i].prev != null){
            ladders[i].prev.next = node
        }
        node.prev = ladders[i].prev


    }

    return 0
}

class Ladder implements Comparable<Ladder>{

    long x,h
    Ladder prev, next

    @Override
    int compareTo(Ladder l) {
        return l.h <=> this.h
    }

    String toString(){
        return "x: $x, h: $h"
    }
}

def test(){


}


