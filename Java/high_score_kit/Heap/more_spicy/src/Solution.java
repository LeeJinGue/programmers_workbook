import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Solution {
    public int solution(int[] scoville, int K) {

        minHeap heap = new minHeap();

        for(int i: scoville){
            if(i == 0){
                System.out.println(heap.delete());
            }else{
                heap.insert(i);
            }
        }
        int mixNum = 0;
        while(heap.size>2 && heap.getMin() < K){
            
            // System.out.println("heap : "+heap.toString()+", size: "+heap.size);
            int low1=heap.getMin();
            heap.delete();
            int low2=heap.getMin();
            heap.delete();
            // System.out.println("low1: "+low1+", low2: "+low2);
            // System.out.println("heap : "+heap.toString()+", size: "+heap.size);
            heap.insert(low1 + low2*2);
            // System.out.println("heap : "+heap.toString()+", size: "+heap.size);
            mixNum++;
        }
        if(heap.getMin() < K){
            // System.out.println("정답: -1");
            return -1;
        }else{
            // System.out.println("정답: "+mixNum);
            return mixNum;
        }
    }
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        System.out.println("정답: "+sol.solution(scoville, K));
    }
    public static class minHeap{
        private ArrayList<Integer> heap;
        public int size = 1;
        public String toString(){
            return heap.toString();
        }
        
        public minHeap(){
            heap = new ArrayList<>();
            heap.add(0);
        }
        public int getMin(){
            return heap.get(1);
        }
        public void insert(int num){
            heap.add(num);
            int p = heap.size()-1;

            while(p > 1 && heap.get(p/2) > heap.get(p)){
                // System.out.println("swap");
                
                int tmp = heap.get(p/2);
                heap.set(p/2, heap.get(p));
                heap.set(p, tmp);
                p = p/2;

            }
            this.size++;
        }
        public int delete(){
            if(heap.size()-1 <1){
                return -1;
            }
            int deleteItem = heap.get(1);
            heap.set(1, heap.get(heap.size()-1));
            heap.remove(heap.size()-1);

            int pos = 1;
            while((pos *2) < heap.size()){
                int min = heap.get(pos*2);
                int minPos = pos*2;

                if((pos*2 +1) < heap.size() && min > heap.get(pos*2 +1)){
                    min = heap.get(pos *2 +1);
                    minPos = pos*2 +1;
                }
                if(heap.get(pos)<min){
                    break;
                }

                int tmp = heap.get(pos);
                heap.set(pos, heap.get(minPos));
                heap.set(minPos, tmp);
                pos = minPos;
            }
            this.size--;
            return deleteItem;
        }
    }
    public class Heap<E>{
        private final Comparator<? super E> comparator;
        private static final int DEFAULT_CAPACITY = 10;

        private int size;
        private Object[] array;

        public Heap(){
            this(null);
        }
        public Heap(Comparator <? super E> comparator){
            this.array = new Object[DEFAULT_CAPACITY];
            this.size = 0;
            this.comparator = comparator;
        }
        public Heap(int capacity){
            this(capacity, null);
        }
        public Heap(int capacity, Comparator <? super E>comparator) {
            this.array = new Object[capacity];
            this.size = 0;
            this.comparator=comparator;
        }

        private int getParent(int index){
            return index/2;
        }
        private int getLeftChild(int index){
            return index *2;
        }
        private int getRightChild(int index){
            return index*2 +1;
        }
    }
}
