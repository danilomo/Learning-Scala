
// This is a class named "Global". You can create new objects from
// this class using the "new" operator
class Global {

	private var m_value: Int = 1
	
	def get_value(): Int = m_value
		
	def set_value(v: Int): Unit = {
		this.m_value = v
	}
	
	// You can access a private field from the singleton
	// companion object
	def printCompanionField(): Unit = {
		println( Global.privateField )
	}

}

// This is an object named "Global". It doesn't belong to the "Global" class.
// Its class is unique, and no other object from its class can be created.
// Since it has the same name of the previous class, it can access the
// private and protected members of the objects from that class (and vice-versa).
object Global {

	private var privateField: Int = 42

	// All Scala functions must return a value. The "Unit" type is a dummy
	// value equivalent to the void type in C
	def incrObj( m: Global ): Unit = {
		m.m_value = m.m_value + 1
	}	
	
}


var g1 = new Global()
var g2 = new Global()

g1.printCompanionField()

println(g2.get_value())
Global.incrObj( g2 )
println(g2.get_value())

