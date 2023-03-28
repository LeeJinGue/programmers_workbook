function solution(clothes) {
  var answer = 1;
  const clothMap = new Map()
  clothes.forEach(cloth => {
    const name = cloth[0]
    const type = cloth[1]
    if(clothMap.has(type)) clothMap.set(type,clothMap.get(type)+1)
    else clothMap.set(type, 2)
  })
  clothMap.forEach((val, key) => answer*=val)
  return answer-1;
}

// 위장 문제
// 스파이들은 매일 다른 옷을 조합해서 위장합니다.
// [의상 이름, 종류] 배열이 담긴 clothes배열이 주어질 때,
// answer는 서로 다른 옷의 조합의 수입니다.
const clothes1 = [["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]
const clothes2 = [["crow_mask", "face"], ["blue_sunglasses", "face"], ["smoky_makeup", "face"]]
console.log("clothes1:",solution(clothes1))
console.log("clothes2:",solution(clothes2))