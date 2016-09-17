package main

import (
  "fmt"
)

func main() {
  arr := [6]int{1, 2, 3, 4, 5, 6}
  for _, arg := range arr[0:] {
    fmt.Println(arg)
  }
}