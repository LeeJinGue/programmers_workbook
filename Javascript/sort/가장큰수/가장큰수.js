function solution(numbers) {
  let answer = '';
  numbers.sort((after, before) => {
    if(after === before) return -1
    let [afterStr, beforeStr] = [after.toString(), before.toString()]
    let compareIndex = 0
    while(afterStr[compareIndex] === beforeStr[compareIndex] && compareIndex != 3){
      compareIndex++
      if(afterStr.length === compareIndex) afterStr += afterStr[0]
      if(beforeStr.length === compareIndex) beforeStr += beforeStr[0]
    }
    if(afterStr[compareIndex] === beforeStr[compareIndex]){
      const isBeforeLonger = before.toString().length > after.toString().length
      if(isBeforeLonger){
        return after.toString()[after.toString().length-1] > after.toString()[0] ? -1 : 1
      }else{
        return before.toString()[before.toString().length-1] > before.toString()[0] ? 1 : -1
      }
    }
    return afterStr[compareIndex] <= beforeStr[compareIndex] ? 1 : -1
  })
  numbers.forEach((number) => answer += number)
  if(Number.parseInt(answer) === 0) return "0"
  return answer
}
const numbers1 = [6, 10, 2]
const numbers2 = [3, 30, 34, 5, 9]
const numbers3 = [676, 67, 678, 81,676, 818, 817]
const numbers4 = [1, 11, 110, 1110]
console.log("# solution(numbers1):",solution(numbers1))
console.log("# solution(numbers2):",solution(numbers2))
console.log("# solution(numbers3):",solution(numbers3))
console.log("# solution(numbers4):",solution(numbers4))