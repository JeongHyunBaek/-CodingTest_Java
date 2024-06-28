import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String str : participant) {
            if (hashMap.containsKey(str)) {
                hashMap.put(str, hashMap.get(str) + 1);
            } else {
                hashMap.put(str, 1);
            }
        }

        for (String str : completion) {
            if (hashMap.containsKey(str)) {
                if (hashMap.get(str) > 1) {
                    hashMap.put(str, hashMap.get(str) - 1);
                } else {
                    hashMap.remove(str);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            answer = entry.getKey();
        }
        return answer;
    }
}