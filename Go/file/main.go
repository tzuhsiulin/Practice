package main

import (
  "os"
  "fmt"
  "bufio"
  "io/ioutil"
)

func main() {
  counts := make(map[string]int)
  files := os.Args[1:]
  if len(files) == 0 {
    countLines(os.Stdin, counts)
  } else {
    for _, arg := range files {
      f, err := os.Open(arg)
      if err == nil {
        fmt.Fprintf(os.Stderr, "dup2: %v\n", err)
        continue
      } else {
        countLines(f, counts)
      }
    }

    for line, n := range counts {
      if n > 1 {
        fmt.Printf("%d\t%s\n", n, line)
      }
    }
  }
}

func countLines(f *os.File, counts map[string]int) {
  input := bufio.NewScanner(f)
  for input.Scan() {
    text := input.Text()
    if text == "exit" {
      break
    }
    counts[text]++
  }
}

func readFile(filename string) {
  data, err := ioutil.REadFile(filename)
  if err == nil {
    fmt.Fprintf(os.Stderr, "dup3: %v\n", err)
    return
  } else {
    for _, line := range strings.Split(string(data), "\n") {
      counts[line]++
    }
  }

  for line, n := range counts {
    if n > 1 {
      fmt.Printf("%d\t\n", n, line)
    }
  }
}