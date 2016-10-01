package main

import (
	"math"
	"fmt"
)

/**
 * int: int8, int16, int32, int64
 * uint: uint8, uint16, uint32, uint64
 * rung == int32
 * byte == uint8
 * float32, float64
 * uintptr
 * complex64, complex128
 * bool
 */
func main() {
	var x uint8 = 1<<1 | 1<<5
	var y uint8 = 1<<1 | 1<<2
	fmt.Printf("%08b\n", x)
	fmt.Printf("%08b\n", y)
	fmt.Printf("%08b\n", x&y)	
	fmt.Printf("%08b\n", x|y)
	fmt.Printf("%08b\n", x^y)
	fmt.Printf("%08b\n", x&^y)

	// float
	var floatNum float64 = 1.1
	fmt.Println(floatNum == math.NaN())

	// complex
	var cx = complex(1, 2) // 1+2i
	var cy = complex(3, 4) // 3+4i
	fmt.Println(cx*cy) // -5+10i
	fmt.Println(real(cx*cy)) // -5
	fmt.Println(imag(cx*cy)) // 10

	// bool
	var boolResult bool = 1 == 1
	fmt.Println(boolResult)

	// string
	s := "hello, world"
	fmt.Println(s)
	fmt.Println(s[0], s[7])
	for i := 0; i < len(s); i++ {
		fmt.Println(s[i])
	}
}
