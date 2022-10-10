import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
        int M = (N * 2);
        
        List<Long> sumList = new ArrayList<>();
        sumList.add((long)0);
        
        for(int i=0; i<N; i++) {
            long sum = sumList.get(i) + (long)queue1[i];
            sumList.add(sum);
        }
        for(int i=0; i<N; i++) {
            long sum = sumList.get(N+i) + (long)queue2[i];
            sumList.add(sum);
        }
        
        long totalSum = sumList.get(M);
        long average = totalSum / 2;
        
        int left = 0;
        int right = N;
        
        int count = 0;
        
        while(true) {
            long prefixSum = sumList.get(right) - sumList.get(left);
            
            if(prefixSum > average) left++;
            else if(prefixSum < average) right++;
            else break;
            
            if(right > M || left >= right) return -1;
            else count++;
        }
        
        return count;
    }
}
