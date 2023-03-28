function solution(sizes) {
  let maxLength = 0, maxIndex = 0, secondMaxLength = 0
  for (let i = 0; i < sizes.length; i++) {
    // 가장 긴 것을 찾습니다.
    if(sizes[i][0] > maxLength){
      maxIndex = i, maxLength = sizes[i][0]
    }else if(sizes[i][1] > maxLength){
      maxIndex = i, maxLength = sizes[i][1]
    }
  }
  console.log(`maxLength:${maxLength} ,maxIndex:${maxIndex}`)
  for (let i = 0; i< sizes.length; i++){
    // 각 지갑의 짧은 쪽 중 가장 긴 길이를 찾습니다.
    const smLength = sizes[i][0] < sizes[i][1] ? sizes[i][0] : sizes[i][1]
    if(smLength > secondMaxLength) secondMaxLength = smLength
  }
  console.log(`secondMaxLength:${secondMaxLength}`)
  return maxLength * secondMaxLength
}
const sizes1 = [
  [60, 50],
  [30, 70],
  [60, 30],
  [80, 40]
]
console.log("# sizes1 정답:", solution(sizes1))
const sizes2 = [
  [10, 7],
  [12, 3],
  [8, 15],
  [14, 7],
  [5, 15]
]
console.log("# sizes2 정답:", solution(sizes2))
const sizes3 = [
  [14, 4],
  [19, 6],
  [6, 16],
  [18, 7],
  [7, 11]
]
console.log("# sizes3 정답:", solution(sizes3))