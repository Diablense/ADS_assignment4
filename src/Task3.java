int[] visitedNodes={1,0,0,0,0,0,0};
ArrayList<Integer> DFS(int[][] adjacencyList,ArrayList<Integer> visitQueue){
    int currentNode=visitQueue.getLast();
    int[] currentList=adjacencyList[currentNode];

    for(int i=0;i<currentList.length;i++){
        if(visitedNodes[currentList[i]]==0){
            visitQueue.add(currentList[i]);
            visitedNodes[currentList[i]]=1;
            visitQueue=DFS(adjacencyList,visitQueue);
        }
    }

    return visitQueue;
}
boolean isNotInQueue(ArrayList<Integer> visitQueue, int a){
    for(int i:visitQueue){
        if(i == a){
            return false;
        }
    }
    return true;
}
ArrayList<Integer> BFS(int[][] adjacencyList,ArrayList<Integer> visitQueue){
    ArrayList<Integer> visitList=new ArrayList<>(visitQueue);
    while(!visitList.isEmpty()){
        int currentNode=visitList.getFirst();
        int[] currentList=adjacencyList[currentNode];
        for(int i:currentList){
            if(isNotInQueue(visitQueue,i)){
                visitList.add(i);
                visitQueue.add(i);
            }
        }
        visitList.removeFirst();
    }

    return visitQueue;
}
void main(String[] args) {
    int A=0,B=1,C=2,D=3,E=4,F=5,G=6;

    int[][] adjacencyList = {
            {C,B,D},
            {A,C,E,G},
            {A,B,D},
            {C,A},
            {G,F,B},
            {G,E},
            {F,B}
    };
    ArrayList<Integer> visitQueue =new ArrayList<>();
    visitQueue.add(A);
    ArrayList<Integer> DFSResult=DFS(adjacencyList,visitQueue);
    System.out.println("DFS Results:");
    for(int i:DFSResult){
        char a='A';
        a+=i;
        System.out.print(a);
        System.out.print(' ');
    }
    visitQueue=new ArrayList<>();
    visitQueue.add(A);// For some ungodly reasons DFS changes visitQueue in the main function and I can not figure out why
    System.out.println("\n\nBFS Results:");
    ArrayList<Integer> BFSResult=BFS(adjacencyList,visitQueue);
    for(int i:BFSResult){
        char a='A';
        a+=i;
        System.out.print(a);
        System.out.print(' ');
    }
}