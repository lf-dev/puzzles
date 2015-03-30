package Jam2010.round1b.fileFixIt

def filename = "A-large-practice"

Scanner sc = new Scanner(new File(filename + '.in'))
def file = new File(filename + '.out')
def w= file.newWriter()

int T = sc.nextInt()

for(int t = 0 ; t < T ; t++ ) {

    int N = sc.nextInt()
    int M = sc.nextInt()

    Set directories = []

    N.times { n->
        String dir = sc.next()

        String fullDir = ""
        dir.tokenize('/').each { d->
            fullDir += "/${d}"
            directories << fullDir
        }
    }

    int total = 0;
    M.times { m->
        String dir = sc.next()

        String fullDir = ""
        dir.tokenize('/').each { d->
            fullDir += "/${d}"

            if(!directories.contains(fullDir)){
                directories << fullDir
                total++
            }
        }
    }

    w.writeLine("Case #${t+1}: ${total}")
}
w.close()


