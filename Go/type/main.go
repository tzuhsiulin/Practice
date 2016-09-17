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

	var a float64 = 1.1
	fmt.Println(a == math.NaN())
}
