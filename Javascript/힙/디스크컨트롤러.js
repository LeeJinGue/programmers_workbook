// 하드디스크 컨트롤러
// 하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다.
// jobs = [ [작업이 요청되는 시점, 작업의 소요시간], ...]
// 가장 평균을 줄이는 방법으로 작업을 수행했을 때, 
// 작업 수행의 평균 시간을 return 합니다.
// 하드디스크가 작업을 수행하고 있지 않을 때에는
// 먼저 요청이 들어온 작업부터 처리합니다.
// 우선순위 큐. 
// 
class MinHeap {
  constructor() {
    this.heap = [null]
  }
  swapHeap(left, right) {
    let temp = this.heap[left]
    this.heap[left] = this.heap[right]
    this.heap[right] = temp

  }
  heapPush(value) {
    this.heap.push(value)
    let curIndex = this.heap.length - 1
    let parIndex = (curIndex / 2) >> 0
    let leftIndex = curIndex - 1
    // 최소힙. 부모가 가장 작아야한다.
    // 부모노드가 현재 노드보다 큰 지 검사한다.
    // 왼쪽 노드가 더 큰지도 검사한다.
    while (curIndex > 1 && this.heap[curIndex][1] < this.heap[parIndex][1]
      // || this.heap[curIndex][1] < this.heap[leftIndex][1]
      ) {
      // if (this.heap[curIndex][1] < this.heap[leftIndex][1]) {
      //   console.log("# change left")
      //   console.log(`curIndex:${curIndex}, parIndex:${parIndex}, leftIndex:${leftIndex}`)
      //   console.log(`# before change heap:${this.heap}`)
      //   this.swapHeap(curIndex, leftIndex)
      //   curIndex = leftIndex
      //   parIndex = (curIndex / 2) >> 0
      //   leftIndex = curIndex - 1
      //   continue
      // }
      // 부모노드가 현재 노드보다 크면 바꾼다.
      console.log("# change parent")
      console.log(`curIndex:${curIndex}, parIndex:${parIndex}, leftIndex:${leftIndex}`)
      console.log(`# before change heap:${this.heap}`)
      this.swapHeap(curIndex, parIndex)
      curIndex = parIndex
      parIndex = (curIndex / 2) >> 0
      leftIndex = curIndex - 1
    }
  }
}
class PQueue {
  // [ [작업 요청 시점, 작업 소요시간], ...]
  // push할 때마다 
  // Queue: [작업요청시점, 작업 소요시간] 배열
  constructor() {
    this.Queue = []
  }
  push(item) {
    // 큐가 비었으면 그냥 넣습니다.
    if (this.Queue.length === 0) {
      this.Queue.push(item)
    } else {
      // 큐에 뭔가 있다면, 위치를 찾습니다.
      let loc = 0
      let endTime = 0
      for (let index = 0; index < this.Queue.length; index++) {
        console.log("# index:", index)
        if (index === 0) {
          endTime = (this.Queue[index][0] + this.Queue[index][1])
          console.log("# 첫번째 endTime:", endTime)
          continue
        }

        if (item[1] <= this.Queue[index][1]) {
          // 만약 loc-1번째 큐의 종료시간이 item의 시작시간보다 빠르다면,
          console.log(`# endTime:${endTime}, Queue[${index}]:${this.Queue[index]}, item[0]:${item[0]}`)
          if (endTime < item[0]) {
            console.log("너무 느려요")

          } else {
            loc = index
            break
          }

        }
        endTime += this.Queue[index][1]
        loc++
        console.log("#끝")
      }
      console.log("# loc:", loc)
      // 하드웨어가 쉴 수 없으므로 loc+1번째 위치에 item을 위치시킵니다.
      // const endTime = this.Queue[loc-1]
      this.Queue.splice(loc, 0, item)
      console.log("# this.queue:", this.Queue.map((q) => q))
    }
  }
  pop() {
    // 하나만 있으면 그냥 꺼냅니다.
    if (this.Queue.length === 1) return this.Queue.shift()
  }
}

function solution(jobs) {
  let answer = 0;
  const queue = new PQueue()
  const heap = new MinHeap()
  // 0. 요청시간으로 정렬?
  // 1. 작업을 수행하고 있지 않을 때, 먼저 들어온 작업부터 처리합니다.
  // 2. 작업 수행중이라면, 걸리는 시간을 우선순위로 큐에 넣습니다.
  jobs.forEach((job, index) => {
    console.log(`# job${index}:`, job)
    // queue.push(job)
    heap.heapPush(job)
    console.log("# heap:",heap.heap.map((heap) => heap))
  })
  let startTime = 0
  let totTime = 0
  heap.heap.forEach((heapItem, heapIndex) => {
    if(heapIndex === 0) return
    totTime += startTime - heapItem[0] + heapItem[1]
    startTime += heapItem[1]
    // console.log(`# startTime:${startTime}, totTime:${totTime}`)
  })
  // console.log(`# last totTime:${totTime}`)
  answer = totTime / (heap.heap.length-1)

  return answer;
}
const job1 = [
  [0, 3],
  [1, 9],
  [2, 6],
  [24, 4]
]
// 
console.log(solution(job1))