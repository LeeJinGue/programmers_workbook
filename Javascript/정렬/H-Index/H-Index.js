function solution(citations) {
  let answer = 0
  let sorted = citations.sort((a,b) => b-a)
  while(sorted[answer] >= answer+1) answer++
  return answer
}
const citation1 = [3, 0, 6, 1, 5]
const citation2 = [1, 2, 5, 9, 12]
const citation3 = [3, 6]
console.log(`citation1 H-index:${solution(citation1)}`) 
console.log(`citation2 H-index:${solution(citation2)}`) 
console.log(`citation3 H-index:${solution(citation3)}`) 
