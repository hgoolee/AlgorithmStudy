import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int N = info.length;
        int M = query.length;
        
        Map<String, List<Integer>> map = new HashMap<>();
        
        for(int i=0; i<N; i++) {
            String[] tmp = info[i].split(" ");
            int score = Integer.parseInt(tmp[4]);
            
            insert(map, tmp[0] + tmp[1] + tmp[2] + tmp[3], score);
            
            insert(map, "-" + tmp[1] + tmp[2] + tmp[3], score);
            insert(map, tmp[0] + "-" + tmp[2] + tmp[3], score);
            insert(map, tmp[0] + tmp[1] + "-" + tmp[3], score);
            insert(map, tmp[0] + tmp[1] + tmp[2] + "-", score);
            
            insert(map, "-" + "-" + tmp[2] + tmp[3], score);
            insert(map, "-" + tmp[1] + "-" + tmp[3], score);
            insert(map, "-" + tmp[1] + tmp[2] + "-", score);
            insert(map, tmp[0] + "-" + "-" + tmp[3], score);
            insert(map, tmp[0] + "-" + tmp[2] + "-", score);
            insert(map, tmp[0] + tmp[1] + "-" + "-", score);
            
            insert(map, "-" + "-" + "-" + tmp[3], score);
            insert(map, "-" + "-" + tmp[2] + "-", score);
            insert(map, "-" + tmp[1] + "-" + "-", score);
            insert(map, tmp[0] + "-" + "-" + "-", score);
            
            insert(map, "-" + "-" + "-" + "-", score);
        }
        
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        int[] answer = new int[M];
        
        for(int i=0; i<M; i++) {
            String[] tmp = query[i].split(" ");
            
            String key = tmp[0] + tmp[2] + tmp[4] + tmp[6];
            int score = Integer.parseInt(tmp[7]);
            
            if(map.containsKey(key)) {
                List<Integer> scoreList = map.get(key);
                
                int index = binarySearch(scoreList, score);
                answer[i] = scoreList.size() - index;
            }
        }
        
        return answer;
    }
    
    int binarySearch(List<Integer> scoreList, int score) {
        int N = scoreList.size();
        
        int low = 0;
        int high = N-1;
        
        while(low <= high) {
            int mid = (low+high) / 2;
            
            if(scoreList.get(mid) < score) low = mid+1;
            else high = mid-1;
        }
        
        return low;
    }
    
    void insert(Map<String, List<Integer>> map, String key, int score) {
        if(!map.containsKey(key)) map.put(key, new ArrayList<>());
        map.get(key).add(score);
    }
}
