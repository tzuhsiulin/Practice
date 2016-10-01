package main

import (
	"fmt"
	"sort"
)

func zero(ptr *[3]int) {
	*ptr = [3]int{}
}

func reserse(s []string) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

func main() {
	// array
	var a [3]int = [3]int{1, 2, 3}
	b := [...]int{1, 2, 3}
	zero(&a)
	for i, v := range a {
		fmt.Printf("%d %d\n", i, v)
	}
	fmt.Println(b)

	// slice
	sliceTest := [...]string{"January", "February", "March", "April", "May", "June", 
		"July", "August", "September", "October", "November", "December"}
	reserse(sliceTest[:2])
	reserse(sliceTest[2:])
	reserse(sliceTest[:])
	fmt.Println(sliceTest)

	var sliceTest2 []int
	fmt.Println(len(sliceTest2))
	sliceTest2 = make([]int, 3, 3)
	copy(sliceTest2, b[0:len(b)])
	fmt.Println(sliceTest2)

	// append
	var runes []rune
	for _, r := range "Hello, 世界" {
		runes = append(runes, r)
	}
	fmt.Printf("%q\n", runes)

	// map
	mapTest := make(map[string]int)
	mapTest["charlie"] = 34
	mapTest["alice"] = 31
	for name, val := range mapTest {
		fmt.Printf("%s\t%d\n", name, val)
	}
	var mapTestKeys []string
	for name := range mapTest {
		mapTestKeys = append(mapTestKeys, name);
	}
	sort.Strings(mapTestKeys)
	for _, key := range mapTestKeys {
		fmt.Printf("%s\t%d\n", key, mapTest[key])
	}
	fmt.Println(mapTest)
	delete(mapTest, "alice")
}
