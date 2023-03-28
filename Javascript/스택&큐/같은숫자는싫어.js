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

// 문제 설명
// 배열 arr에서 연속적으로 나타나는 숫자를 하나만 남기고 제거합니다.
// 배열 arr 원소의 순서는 유지합니다.
// 문제 풀이
// 1. arr을 순환하면서 원소를 answer에 넣습니다.
// -> answer의 길이가 0이면 원소를 answer에 넣습니다.
// -> 아니라면 answer의 마지막 요소를 꺼내서 arr의 순환중인 요소와 같은지 검사합니다.
// -> 같지 않다면 연속수가 아니므로 꺼낸 요소를 다시 answer에 넣고 순환중인 요소도 answer에 넣습니다.
// -> 같다면 연속수이므로 순환중인 요소만 다시 answer에 넣습니다.
const arr = [1,1,3,3,0,1,1]
const arr2 = [4,4,4,3,3]
console.log("answer:"+solution(arr))
console.log("answer2:"+solution(arr2))