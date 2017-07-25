
// Creating a high order function (e.g.: a function that takes a function as a parameter)
def myFilter ( list: List[Int], f: (Int) => Boolean ): List[Int] = {
	var l: List[Int] = List()
	
	for( i <- list ) {
	
		// only append the element from the original list
		// if the function passed as argument evaluates to true
		// applied to the element
		if( f(i) ){
			l = i :: l
		}
	}
	
	// using the reverse method to reverse the order of the elements
	// since the elements are appended like a stack
	l.reverse
}

def odd( i: Int ) = i % 2 == 1

val l = List(1, 2, 3, 4, 5 )

// Calling the filter function and passing a named function (created with the "def" keyword)
println( l.filter( odd ) )

// Calling the filter function with an anonymous function (lambda notation)
println( l.filter( (i: Int) => i % 2 == 1 ) )


// Omiting the input parameter type, because the compiler can infer it(since "l" refers to a list of Int's)
println( l.filter( i => i % 2 == 1)  )

// Using the underscore notation
println( l.filter( _ % 2 == 1 ) )

// Using our created function
println( myFilter( l, _ % 2 == 1 ) ) 

