function solution(answers) {
  let answer = []
  const pattern1 = [1, 2, 3, 4, 5]
  const pattern2 = [2, 1, 2, 3, 2, 4, 2, 5]
  const pattern3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
  let answer1=0, answer2=0, answer3=0
  for(let i = 0; i<answers.length; i++){
    if(pattern1[i % pattern1.length] === answers[i]) answer1++
    if(pattern2[i % pattern2.length] === answers[i]) answer2++
    if(pattern3[i % pattern3.length] === answers[i]) answer3++
  }
  const maxAnswer = Math.max(answer1, answer2, answer3)
  if(maxAnswer === answer1) answer.push(1)
  if(maxAnswer === answer2) answer.push(2)
  if(maxAnswer === answer3) answer.push(3)
  return answer
}
const answer1 = [1,2,3,4,5]
const answer2 = [1,3,2,4,2]
console.log("# answer1 정답:",solution(answer1))
console.log("# answer2 정답:",solution(answer2))