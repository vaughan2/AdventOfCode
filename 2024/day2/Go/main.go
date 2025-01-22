package main

import (
	"bufio"
	"fmt"
	"log"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	pass := 0
	file, err := os.Open("list.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		splittedT, err := sliceAtoi(strings.Split(scanner.Text(), " "))
		if err != nil {

		}
		sameDirection := checkIfAllSorted(splittedT)
		if sameDirection {
			checkAdjacent := checkAdjacent(splittedT)
			if checkAdjacent {
				pass++
			}
		} else {

		}
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
	fmt.Println(pass)
}
func checkAdjacent(arr []int) bool {
	copiedArr := append([]int(nil), arr...)
	failed := true
	// fmt.Println(len(copiedArr))
	for i := range copiedArr {
		if !failed {

		} else {
			//  If this is the last one
			if i == (len(copiedArr) - 1) {
				fmt.Println("Last Line")
			} else {
				var floatLeft float64 = float64(copiedArr[i])
				var floatRight float64 = float64(copiedArr[i+1])
				if math.Abs(floatLeft-floatRight) < 1 || math.Abs(floatLeft-floatRight) > 3 || floatLeft == floatRight {
					failed = false
				} else {
					failed = true
				}
			}
		}
	}
	return failed
}
func checkIfAllSorted(arr []int) bool {
	// Create a copy of the array
	copiedArr := append([]int(nil), arr...)
	// Sort the copied array
	sort.Ints(copiedArr)
	checked := true
	// Compare the original array with the sorted array
	for i := range arr {
		if arr[i] != copiedArr[i] {
			checked = false
		}
	}
	// Compare reversed
	if !checked {
		sort.Sort(sort.Reverse(sort.IntSlice(copiedArr)))
		for i := range arr {
			if arr[i] != copiedArr[i] {
				return false
			}
		}
	}
	return true
}

func sliceAtoi(sa []string) ([]int, error) {
	si := make([]int, 0, len(sa))
	for _, a := range sa {
		i, err := strconv.Atoi(a)
		if err != nil {
			return si, err
		}
		si = append(si, i)
	}
	return si, nil
}
