function solution(arr){
    const answer = [];
    arr.forEach(element => {
      if(answer.length === 0){
        answer.push(element)
        return
      }
      const last = answer.pop()
      if(last !== element) answer.push(last)
      answer.push(element)
      return
    });
    return answer;
}

// 배열 arr에서 연속적으로 나타나는 숫자를 하나만 남기고 제거합니다.
// 배열 arr 원소의 순서는 유지합니다.
// 1. arr을 순환하면서 원소를 stack에 넣습니다.
// 2. answer의 길이가 0이거나 answer의 마지막 원소가 
const arr = [1,1,3,3,0,1,1]
const arr2 = [4,4,4,3,3]
console.log("answer:"+solution(arr))
console.log("answer2:"+solution(arr2))