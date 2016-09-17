package main

import (
  "fmt"
  "os"
  "strings"
  "strconv"
)

func main() {
  fmt.Println("First:")
  fmt.Println(strings.Join(os.Args[1:], " "))

  fmt.Println("Second:")
  fmt.Println(os.Args[1:])

  fmt.Println("Third:")
  fmt.Println(os.Args[0:])

  fmt.Println("Fourth:")
  for i := 0; i < len(os.Args); i++ {
    fmt.Println(strconv.Itoa(i) + ": " + os.Args[i])
  }
}
