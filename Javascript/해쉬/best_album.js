function solution(genres, plays) {
  const genreTotalPlayMap = new Map() // 장르이름과 총 재생횟수가 짝지어진 맵
  const genreSongIndexMap = new Map() // 장르이름과 노래의 인덱스 배열이 짝지어진 맵
  const genreNameArr = []             // 장르이름이 담긴 배열
  for(let index = 0; index<genres.length; index++){
    const genreName = genres[index]
    const playFrequency = plays[index]
    if(genreNameArr.indexOf(genreName) === -1){
      // 새로운 장르라면
      genreNameArr.push(genreName)  // 장르배열에 추가하고
      genreTotalPlayMap.set(genreName, playFrequency) // 장르이름과 재생횟수 맵에 추가하고
      genreSongIndexMap.set(genreName, [index])       // 장르이름과 노래의 인덱스 배열을 추가합니다.
    }else{
      // 새로운 장르가 아니라면
      genreTotalPlayMap.set(genreName, genreTotalPlayMap.get(genreName)+playFrequency)
      // 해당 장르의 총 재생횟수를 더하고
      const songIndexArr = genreSongIndexMap.get(genreName)
      songIndexArr.push(index)
      songIndexArr.sort((beforeIndex, afterIndex) => plays[afterIndex] - plays[beforeIndex])
      genreSongIndexMap.set(genreName, songIndexArr)
      // 지금 인덱스를 해당 장르 노래들 인덱스가 담긴 배열에 추가합니다.
    }
  }
  genreNameArr.sort((beforeIndex, afterIndex) => 
  genreTotalPlayMap.get(afterIndex) - genreTotalPlayMap.get(beforeIndex))
  // 장르 이름을 총 횟수에 따라 내림차순 정렬을 해줍니다.
  let answer = [];
  genreNameArr.forEach((genreName) => {
    const albumIndex = genreSongIndexMap.get(genreName).slice(0,2)
    answer = answer.concat(albumIndex)
  })
  return answer;
}
// 베스트앨범 문제
// 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아서
// 베스트 앨범을 출시
// answer는 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 써놓은 배열
// 노래는 고유 번호로 구분, 베스트 앨범에 추가하는 우선순위는 다음과 같다.
// 1. 속한 노래가 많이 재생된 장르를 먼저 수록
// 2. 장르 내에서 많이 재생된 노래를 먼저 수록
// 3. 장르 내에서 재생 횟수가 같다면, 고유 번호가 낮은 노래를 먼저 수록
// 노래의 장르를 나타내는 String 배열 genres
// 노래별 재생횟수를 나타내는 정수 배열 plays
// 각 배열의 인덱스가 각 노래의 고유번호다.


const genres = ["classic", "pop", "classic", "classic", "pop"]
const plays = [500, 600, 150, 800, 2500]
console.log("정답:",solution(genres,plays))