function solution(s){
  const stack = []
  for(var index = 0; index<s.length; index++){
    if(s[index] === '(') {
      stack.push(s[index])
      continue
    }
    if(stack.length === 0 || stack.pop() !== '(') return false
  }
  if(stack.length === 0) return true
  return false
}
// 문제 설명
// 주어진 문자열 s가 올바른 괄호인지를 확인하여 true/false를 return합니다.
// 올바른 괄호란 '('문자열로 열렸으면 반드시 짝지어서 ')'문자열로 닫혀야 한다는 의미입니다.

// 문제 풀이
// 1. 문자열 s를 순환합니다.
// 2. 순환중인 문자열이 '('인 경우 stack에 넣고 ')'인 경우 pop해서 '('인지 확인합니다.
// 2-1. pop한 문자열이 '('가 아니라면 false를 return하고 
// 2-2. pop한 문자열이 '('가 맞다면 continue
// 3. 문자열 순환이 끝났을 때 stack이 비어있으면 true, 뭔가 있다면 false를 return합니다.


// 예제 실행 코드
const s1 = "()()"
const s2 = "(())()"
const s3 = ")()("
const s4 = "(()("
console.log("s1:",solution(s1))
console.log("s2:",solution(s2))
console.log("s3:",solution(s3))
console.log("s4:",solution(s4))
