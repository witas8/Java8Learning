# Java8Learning
Main functionalities that appeared in Java 8

More in code comments!

1. Definition
	Functional programming is kind of declarative programming, not an imperative programming where we define every detail of a code. However, in the declarative programming we say what you want instead of defining all the time the details. For example to create a loop in imperative programming we have to prepare variable, increment and termination (so how to stop the loop), but in the declarative one we don’t have none of them, just write (by following rules) and you reach faster the goal. Moreover, we can pass function as the parameter.


2. Functional Interfaces
	There is useful package “java.util.function” that consists functional interfaces. Each functional interface has a single abstract method, which the lambda expression’s parameter and return type are matched or adapted. Example: Predicate, Supplier, toIntFunction etc.
	A functional interface is an interface that contains only one abstract method. They can have only one functionality to exhibit. BiFunction is a function that instead of taking 1 input it takes 2 inputs and produces 1 output.
	Abstract methods don’t have body, they just have method signature as 
„public abstract int myMethod(int n1, int n2);”


3. Function Interface
- The Java Function interface is one of the most central functional interfaces in Java. The Function interface represents a function (method) that takes a single parameter and returns a single value. Here is how the Function interface definition looks: 
	public interface Function<T,R> {
		public <R> apply(T parameter);
	}


4. Wrapping up
Pure functional programming has a set of rules to follow:
	- use pure functions, so the functions that do not depend on global states
	- no side effects outside the function, so do what you want and do not impact further
	- use higher order function if:
		- the function takes a one or more functions as parameters
		- the function returns another function as result (ex. Combinator pattern)
