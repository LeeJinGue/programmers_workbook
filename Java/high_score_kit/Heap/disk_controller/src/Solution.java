import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public class ReqComparator implements Comparator<job>{

        @Override
        public int compare(Solution.job o1, Solution.job o2) {
            Integer temp1 = o1.req_time;
            Integer temp2 = o2.req_time;
            
            return temp1.compareTo(temp2);
        }

    }

    public int solution(int[][] jobs) {
        int answer = 0;
        List<job> jobList = new ArrayList<>();
        int average=0;
        ArrayList<Integer> totTimeList = new ArrayList<>();
        int now_time = 0;
        PriorityQueue<job> queue = new PriorityQueue<>();
        for(int[] work:jobs){
            job newJob = new job(work[0], work[1]);
            jobList.add(newJob);
            // queue.add(newJob);
        }
        ReqComparator req = new ReqComparator();
        Collections.sort(jobList,req);
        System.out.println("시간으로 정렬된 잡: ");
        for(job j:jobList){
            System.out.println(j.toString());
        }
        // 총 걸린시간 = (시작시간 - 요청시간) + 걸리는시간
        // answer = (시작시간 - (요청시간의합))+(걸린시간의 합) / 리스트의 개수
        // 시작시간을 모아둔 리스트
        ArrayList<Integer> startTimeList = new ArrayList<>();

        // 첫번째 값을 처리합니다.
        job first = jobList.remove(0);
        now_time += first.req_time+first.taken_time;
        totTimeList.add(now_time+first.taken_time-first.req_time);
        System.out.println(first.toString()+"인 잡이 수행되었습니다.");
        // 1. 요청시간을 기준으로 오름차순으로 작업리스트를 정렬한다.(jobList)
// 2. 맨 앞의 작업을 꺼내서 처리시간만큼 현재시간에 더한다.
// 3. 현재시간보다 빠른 요청시간을 가지고있는 애들을 꺼내서 작업시간이 기준인 우선순위 큐에 넣는다.
// 4. 큐 맨앞에꺼를 꺼내서 작업을 수행한다. 

        // list가 비어있을 때까지 while을 돈다.
        while(!jobList.isEmpty()){
            // 요청시간이 더 늦으면 현재시간을 요청시간으로 만든다. -> 현재시간을 요청시간보다 늦거나 같게 만든다.
            if(jobList.get(0).req_time > now_time) now_time = jobList.get(0).req_time;
            job nowJob = jobList.get(0);
            while(nowJob.req_time <= now_time){
                // 현재시간보다 같거나 일찍 요청된 애들을 큐에 추가하고 리스트에서 지운다. 
                jobList.remove(0);
                queue.add(nowJob);
                if(jobList.isEmpty()){
                    System.out.println("비었음");
                    break;
                }
                nowJob = jobList.get(0);
            }
            System.out.println("ㅡㅡㅡㅡㅡ큐 중간점검ㅡㅡㅡㅡㅡㅡㅡ");
            for(job j:queue){
                System.out.println("큐: "+j.toString());
            }
            nowJob = queue.poll();
            now_time+=nowJob.taken_time;
            totTimeList.add(now_time+nowJob.taken_time-nowJob.req_time);
            System.out.println(nowJob.toString()+"인 잡이 수행되었습니다.");
        }
        System.out.println("while문 끝");
        while(!queue.isEmpty()){
            job j = queue.poll();
            now_time+=j.taken_time;
            totTimeList.add(now_time+j.taken_time-j.req_time);
            System.out.println(j.toString()+"인 잡이 수행되었습니다.");
        }
        // 우선순위 큐를 돌면서 확인
        // while(!queue.isEmpty()){
        //     // 현재 작업을위해 하나 꺼냅니다.
        //     job nowJob = queue.poll();
        //     // 요청시간이 안됐으면 
        //     if(nowJob.req_time > now_time){
        //         // 요청시간이 되도록 만들고
        //         now_time = nowJob.req_time;
        //     }else{
        //         // 아니면 그냥 작업을 수행합니다.
        //     }
        //     // 현재 작업이 걸린 총 시간을 계산합니다.
        //     System.out.print(nowJob.toString() +"인 작업이 수행되었습니다");
        //     int now_take_time = (now_time + nowJob.taken_time) - nowJob.req_time;
        //     System.out.print(", 이 작업이 걸린 시간은 "+now_take_time+"초 입니다.");
        //     totTimeList.add(now_take_time);
        //     // 작업이 수행되었으므로 수행시간만큼 현재시간을 더해줍니다.
        //     now_time += nowJob.taken_time;
        //     System.out.println(" 다음 작업을 진행합니다.");
            
        // }
        for(int i=0; i<totTimeList.size();i++){
            System.out.println(i+"번째 작업이 걸린 총시간은 "+totTimeList.get(i)+"입니다.");
            average+=totTimeList.get(i);
        }
        // 평균을 계산합니다.
        average /= totTimeList.size();
        
        return average;
    }
    public class job implements Comparable<job>{
        
        private int req_time; // 요청시간
        private int taken_time; // 걸리는 시간
        public job(int req_time, int taken_time){
            this.req_time = req_time;
            this.taken_time = taken_time;
        }
        public String toString(){
            String str = "";
            str = "요청시간: " + req_time +", 걸리는시간: "+taken_time;
            return str;
        }
        public int getTotTimeWithStartTime(int start_time){
            // 요청시간~시작시간까지의 시간 + 걸리는시간
            // 시작시간이 더 빠르면 말이 안된다. 
            if(start_time <req_time){
                return -1;
            }
            int tot_time = (start_time - req_time) + taken_time;
            return tot_time;
        }
        @Override
        public int compareTo(Solution.job target) {
           // 현재 work의 요청시간이 빠르면 우선순위가 높다.
            return this.taken_time >= target.taken_time? 1: -1;
        }
    }
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        int[][] jobs = {{0, 3}, {1, 9}, {5, 6}, {3, 1}, {2, 7}};
        System.out.println("정답: "+sol.solution(jobs));
    }
}

// # 아이디어
// 1. 작업이 요청된 게 없을 때에는 먼저 들어온 걸 수행합니다.
// 2. 먼저 들어온 작업이 끝난 후 어떤 작업을 먼저 처리할지 선택해야 합니다.
// 3. 이 때, 각 작업을 선택했을 때 두번째 작업의 시간을 알 수 있습니다.
// 4. 평균 시간을 어떻게 줄여야할까? 결국 선택의 연속인데.. 작업 처리 순서를 정하는 것이라고 할 수 있다. 
// 5. 배열에 정리되어있지는 않을 것이디. 일단 요청시간순으로 정리는 해야될듯
// 6. 맨앞에꺼를 꺼낸다. 
// 평균을 구하는 공식: 각 작업의 시작시간 - 요청시간 + 걸리는시간을 다 더하고 개수만큼 나눈 값.
// 요청시간과 걸리는 시간은 고정, 시작시간이 미정. 시작시간을 어떻게 측정할 수 있을까?
// 이전 작업의 시작시간 + 걸린시간 이걸 최소화. 
// 작업을 고를 때, 현재시간 + 고른 작업의 작업시간이 다음 고른시간이 된다. -> 작업시간이 작은 걸 선택해야 한다.

// 

