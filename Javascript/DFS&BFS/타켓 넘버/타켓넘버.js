function DFS(answer, index, numbers, target){
  if (index == numbers.length){
      if(answer == target) return 1
      else return 0
  }else{
      return DFS(answer+numbers[index], index+1, numbers, target) + 
          DFS(answer-numbers[index], index+1, numbers, target)
  }
}
function solution(numbers, target) {
  return DFS(0, 0, numbers, target)
}

const numbers1 = [1, 1, 1, 1, 1]
const target1 = 3
console.log(`# 답1: ${solution(numbers1,target1)}`)
const numbers2 = [4, 1, 2, 1]
const target2 = 4
console.log(`# 답2: ${solution(numbers2,target2)}`)