package Africa2010.storeCredit

def filename = "A-small-practice"

def input = new File(filename + '.in').readLines()
def numberOfTestCases = input[0] as Integer

def file = new File(filename + '.out')
def w= file.newWriter()

for(int n = 0 ; n < numberOfTestCases ; n++ ) {
    def credit = input[1+n*3] as Integer
    def numItems = input[2+n*3] as Integer
    def prices = input[3+n*3].split(' ').collect { it as Integer}

    def itens = twoItems(n, credit, numItems, prices)

    w.writeLine("Case #${n+1}: ${itens[0]} ${itens[1]}")
}

w.close()

List twoItems(caseNum, credit, numItems, prices) {

    for(int i=0; i<numItems; i++) {

        for(j=i + 1; j<numItems; j++) {

            if(prices[i] + prices[j] == credit) {
                return [i + 1 ,j + 1]
            }
        }
    }
}
