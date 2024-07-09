package cache;

import java.util.*;

class Cache {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        for (String city : cities) {
            city = city.toLowerCase();

            if (cacheSize == 0) {
                answer += 5;
                continue;
            }

            if (map.containsKey(city)) {
                map.remove(city);
                map.put(city, 0);
                answer++;
            } else {
                if (map.size() == cacheSize) {
                    String maxKey = "";
                    int maxValue = Integer.MIN_VALUE;

                    for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
                        if (maxValue < entrySet.getValue()) {
                            maxKey = entrySet.getKey();
                            maxValue = entrySet.getValue();
                        }
                    }
                    map.remove(maxKey);
                }
                map.put(city, 0);
                answer += 5;
            }

            for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
                map.put(entrySet.getKey(), entrySet.getValue() + 1);
            }
        }
        return answer;
    }
}