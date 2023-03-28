function solution(nums) {
  const N = nums.length / 2
  const noDup = []
  nums.forEach(num => {
    if(!noDup.length===0 || noDup.includes(num))
      return
    noDup.push(num)
  })
  return N < noDup.length ? N : noDup.length
}
// N마리 중 N/2마리를 가져갈 수 있다.
// 번호가 종류다.
// 3,1,2,3 일 경우, 2마리를 가져갈 수 있다.
// 가장 많은 종류의 폰켓몬을 선택하고싶다.
// answer는 가장 많은 종류의 폰켓몬을 선택하는 경우의
// 폰켓몬의 종류의 수다.
// 1) 중복을 제거한다.
// 2) N/2 > 폰켓몬 종류면 폰켓몬 종류가 답이다.
// 3) N/2 <= 폰켓몬 종류면 N/2가 답이다.
const nums1 = [3, 1, 2, 3]
const nums2 = [3,3,3,2,2,4]
const nums3 = [3,3,3,2,2,2]
console.log("nums1:",solution(nums1)) 
console.log("nums2:",solution(nums2)) 
console.log("nums3:",solution(nums3)) 