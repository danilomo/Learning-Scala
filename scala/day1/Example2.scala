
// Declaring a class parameter. This parameter should be passed when
// creating an object using the "new" keyword.
class Global(val i: Int){

	private var m_value = i

	def get_value(): Int = m_value
		
	def set_value(v: Int): Unit = {
		this.m_value = v
	}	
	
	// The apply method is overriden to overload the parenthesis operator "()"
	// and turn the object in a callable function
	def apply(): Int = m_value
	
	def apply( incr: Int ): Int = {
		m_value = m_value + incr
		
		m_value
	}

}

object Global {

	// This is a common pattern in scala: creating a apply method inside a companion object
	// and use the companion object as a factory method for creating new instances
	// e.g.: val l = List( 1, 2, 3 ), val g = Global( 15 )
	def apply( i: Int ) = new Global( i )
	
}


var g1 = Global(100)

println( g1.get_value() )

println( g1() )

println( g1( 50 ) )


