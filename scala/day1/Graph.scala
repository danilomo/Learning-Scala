/**
* A functional Graph object in Scala, for learning purposes.
* It doesn't contain any mutable fields (var). When adding edges and vertices to
* an existing graph, it creates a new object.
*/
class Graph[V,W](val vertices: List[V], val edges: List[(V,V,W)]) {

	
	// overriding the "toString" method to be able to print the object
	// with the "print" function (instead of showing the hex code of the object's location
	// in memory
	override def toString(): String = vertices.toString() + ", " + edges.toString()


	// this is how you create functional objects in Scala: if you want to modify
	// an object, create a slightly different new object, instead of changing
	// the state of an existing object.
	// It's also an inexpensive operation. You are just creating a new object with a
	// list based on the existent list, adding a new head element (complexity O(1))
	def addVertex(v: V): Graph[V,W] = Graph( v :: this.vertices, this.edges )
	def addEdge(e: (V,V,W)): Graph[V,W] = Graph(this.vertices, e :: this.edges )

	// The for loop construct is actually an expression for doing list comprehension.
	// The first part uses pattern matching for binding values to the variables v1 and v2
	// from the elements of each 3-tuple element (the last element is discarded by using a wildcard
	def neighbors( v: V ): List[V] = {
		for((v1, v2, _) <- edges; if v == v1 ) yield v2
	}

	// retrieving the weight of a edge using filter with an anonymous function
	// this function returns a list of the elements which the function passed as
	// an argument returns true. Then, it returns the third element from the tuple
	// element in the head of the list. In case of a empty list (an inexistent edge)
	// an null pointer exception will be raised
	def weight( e: (V,V) ): W = {
		val l = edges.filter( x => x._1 == e._1 && x._2 == e._2 )
		l.head._3
	}

	// recursive and functional depth first search. it returns a ordered list containing the sequence
	// of nodes visited. for a graph:
	// a ----
	//		| -- b
	//		| -- c
	//		| -- d
	//		(...)
	// dfs1_( a, [] ) = dfs1_( b, [a] ) ++  dfs1_( c, [a] ) ++  dfs1_( d, [a] ) ++ [a]
	// So, applying flatMap for each non-visited neighbor and appending the root node at the end will expand this computation
	// The result will contain some repeated nodes that are removed by the function List.distinct
	def dfs( root: V ): List[V] ={
		def dfs1_( v: V, discovered: Set[V]): List[V] = {
			val dn = discovered + v
			val n = neighbors(v).filter( ! discovered.contains(_))

			n.flatMap( dfs1_( _ , dn) ) ::: List(v)
		}

		 dfs1_( root, Set() ).distinct
	}
}

// the companion object of the Graph class. The apply method is used to define the behavior when using
// the object as a function. This is used to create a factory method Graph( ... ) for creating new graph objects.
// The companion object of a class also can access private fields of its related class
object Graph{
	def apply[V,W](vertices: List[V], edges: List[(V, V, W)]): Graph[V,W] = new Graph(vertices, edges)

	def apply[V,W](vertices: List[V], edges: List[(V, V)], default: W): Graph[V,W]
		= new Graph(vertices, for ( (a,b) <- edges ) yield (a, b, default))
}

object Main extends App{

	val g = Graph[String, Double](
		List( "S", "A", "B", "C", "D", "E", "F", "G" ),
		List(
			("S", "A"),
			("S", "B"),
			("S", "C"),
			("A", "D"),
			("B", "E"),
			("C", "F"),
			("D", "G"),
			("E", "G"),
			("F", "G")
		), 1.0
	)

	g.dfs( "S" ).foreach( x => println( "Visited: " + x))
}
