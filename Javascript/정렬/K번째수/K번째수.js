function solution(array, commands) {
  return commands.map((command) => {
    const [startIndex, endIndex, k] = command
    return array.slice(startIndex-1,endIndex).sort((after, before) => after - before)[k-1]
  })
}
const array = [1, 5, 2, 6, 3, 7, 4]	
const commands = [[2, 5, 3], [4, 4, 1], [1, 7, 3]]
console.log("# solution(array,commands):",solution(array,commands))