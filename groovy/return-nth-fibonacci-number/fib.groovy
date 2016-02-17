// Single stack frame.  Returns the "Nth" Fibonacci number - aka the Nth number in the sequence.

def fibIter
fibIter = { int step, int endStep, long currVal, long nextVal ->
  if ( step == endStep ) return currVal
  fibIter.trampoline( ++step, endStep, nextVal, currVal+nextVal )
}.trampoline()

def fibonacci = { int seqNum ->
  if ( seqNum < 0 ) println "fail" // needs better error handling
  else fibIter( 0, seqNum, 0, 1 )
}

def x = 100
println "fibonacci($x) = ${fibonacci(x)}"


// Best way to show this is to run the numbers in order.  Maybe instead of just calling "fibonacci(100)" it would be better to call it from a loop and print the results...

StringBuilder result = new StringBuilder()

def y = 10
(0..y).each { num ->
  if ( num ) result << ", "
  result << fibonacci( num )
}

println "fib sequence 0..$y = $result"

// y=10 prints: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55