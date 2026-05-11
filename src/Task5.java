int findNextCurrent(int[] summaryDistance, int[] visited) {
    int min = -1;
    int nextCurrent = -1;
    for (int i = 0; i < summaryDistance.length; i++) {
        if (visited[i] == 0 && summaryDistance[i] != -1) {
            if (min == -1 || summaryDistance[i] < min) {
                min = summaryDistance[i];
                nextCurrent = i;
            }
        }
    }
    return nextCurrent;
}

ArrayList<Integer> Dijkstra(int start, int end, int[] summaryDistance, int[] visited, int[][] map) {
    int n = summaryDistance.length;
    ArrayList<ArrayList<Integer>> currentShortestPaths = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        currentShortestPaths.add(new ArrayList<>());
    }

    summaryDistance[start] = 0;
    currentShortestPaths.get(start).add(start);

    int current = findNextCurrent(summaryDistance, visited);
    while (current != -1) {
        for (int nei = 0; nei < n; nei++) {
            int weight = map[current][nei];
            if (visited[nei] == 0 && weight > 0) {  // edge exists
                int newDist = summaryDistance[current] + weight;
                if (newDist < summaryDistance[nei] || summaryDistance[nei] == -1) {
                    summaryDistance[nei] = newDist;
                    // IMPORTANT: copy the path, do not share the same list
                    ArrayList<Integer> newPath = new ArrayList<>(currentShortestPaths.get(current));
                    newPath.add(nei);
                    currentShortestPaths.set(nei, newPath);
                }
            }
        }
        visited[current] = 1;
        current = findNextCurrent(summaryDistance, visited);
    }
    return currentShortestPaths.get(end);
}

void main() {
    ArrayList<String> cityNames = new ArrayList<>(Arrays.asList("Edinburgh","Glasgow","Stirling","Perth","Dundee"));
    int[][] map = {
            {0,70,50,100,0},
            {70,0,50,0,0},
            {50,50,0,40,0},
            {100,0,40,0,60},
            {0,0,0,60,0}
    };
    int[] summaryDistance = {-1,-1,-1,-1,-1};
    int[] visited = {0,0,0,0,0};
    int start = cityNames.indexOf("Edinburgh");
    int end = cityNames.indexOf("Dundee");

    ArrayList<Integer> path = Dijkstra(start, end, summaryDistance, visited, map);
    System.out.print("Shortest path: ");
    for (int idx : path) {
        System.out.print(cityNames.get(idx) + " ");
    }
    System.out.println("\nDistance: " + summaryDistance[end]);
}