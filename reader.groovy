println "Hola al dia 2 de adventure of code"
println "Dia 2 de adventure of code"

def prueba = new File ("dia2.txt")
def a1
def a2
def a3
def extra
def val1
def val2
def total2=0
int areatotal=0

prueba.eachLine(){
	
	//obtenemos los valores de cada una de las tres áreas
	a1= it.split("x")[0].toInteger() * it.split("x")[1].toInteger()
	a2= it.split("x")[1].toInteger() * it.split("x")[2].toInteger()
	a3= it.split("x")[0].toInteger() * it.split("x")[2].toInteger()
	
	//obteniendo el valor minimo de las áreas que será el extra
	extra=Math.min( a1, Math.min(a2,a3))

	//obtenemos los valores de las áreas multiplicado por dos
	a1=a1*2
	a2=a2*2
	a3=a3*2

//sumamos al area total a un acumulado
	areatotal=areatotal+a1+a2+a3+extra
	
}

	println "Area total de: "+areatotal

//--------------------------------------------------parte 2

prueba.eachLine(){

	//obteniendo primer valor
	val1=it.split("x")[0].toInteger()*2 + it.split("x")[1].toInteger()*2

	//obteniendo el segundo valor
	val2 = it.split("x")[0].toInteger() * it.split("x")[1].toInteger() * it.split("x")[2].toInteger()

	total2=total2+val1+val2
	
}

println "El resultado de la segunda parte es: "+total2

