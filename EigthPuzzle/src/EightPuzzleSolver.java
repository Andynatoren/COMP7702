import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class EightPuzzleSolver {

    private ArrayList<String> queue = new ArrayList<>();
    private PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    private PriorityQueue<Node2> priorityQueue2 = new PriorityQueue<>();
    private HashSet<String> visitedStates = new HashSet<>();
    private HashMap<String,Node> visitedNodes = new HashMap<>();
    private HashMap<String,Node2> visitedNodes2 = new HashMap<>();



    private HashSet<String> returnNewVisits(ArrayList<String> possibleMoves){
        HashSet<String> newVisits = new HashSet<>();
        for(String move: possibleMoves){
            if(!visitedStates.contains(move)){

                newVisits.add(move);
                visitedStates.add(move);
            }
        }
        return newVisits;
    }

    //Checks and updates the cheapest path to the possible nodes.
    private void updatePriQueue(Node fromNode){
        ArrayList<String> possibleMoves = Moves.getPossibleMoves(fromNode.getState());
        for( String move: possibleMoves){
            Node node = new Node(move,fromNode.getGoalState(),fromNode);
            if(!visitedNodes.containsKey(move)){
            priorityQueue.offer(node);
            visitedNodes.put(move,node);
            }
            else if (node.getCost()< visitedNodes.get(move).getCost()){
                visitedNodes.get(move).setParent(fromNode);
                priorityQueue.offer(node);
            }
        }

    }
    private void updatePriQueue2(Node2 fromNode){
        ArrayList<String> possibleMoves = Moves.getPossibleMoves(fromNode.getState());
        for( String move: possibleMoves){
            Node2 node = new Node2(move,fromNode.getGoalState(),fromNode);
            if(!visitedNodes2.containsKey(move)){
            priorityQueue2.offer(node);
            visitedNodes2.put(move,node);
            }
            else if (node.getCost()< visitedNodes2.get(move).getCost()){
                visitedNodes2.get(move).setParent(fromNode);
                priorityQueue2.offer(node);
            }
        }

    }


    private String solve(String state, String goal_state, String method){
        queue.clear();
        visitedStates.clear();
        System.out.println("Finding solution for 8-puzzle state " +state+ " with goal: " + goal_state + ". Method: " + method);

        //Starting computing time.
        double startTime = System.nanoTime();

        //Checking parity
        if(Moves.haveDifferentParity(state,goal_state)){
            double endTime = System.nanoTime();
            double duration = (endTime - startTime)/1000000  ;
            return ("The parity check shows that there is no solution." + "\n" +
                    "Time used: " + (duration)  + " milliseconds");
        }

        queue.addAll(returnNewVisits(Moves.getPossibleMoves(state)));

        while (queue.size()>0){
            if(state.equals(goal_state)){
                double endTime = System.nanoTime();
                double duration = (endTime - startTime)/1000000  ;
                return "Goal state found in " + visitedStates.size() + " moves. The method used was " + method +".\n" +
                        "Time used: " + duration + " milliseconds.";
            }
            //Adds new visits to the state. returnNewVisits returns states not currently int the queue.
            queue.addAll(returnNewVisits(Moves.getPossibleMoves(state)));
            switch (method.toLowerCase()){
                case "dfs":
                    state = queue.get(queue.size()-1);
                    queue.remove(state);
                case "bfs":
                    state = queue.get(0);
                    queue.remove(0);
            }
        }
        //Calculating computing time.
        double endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000000  ;
        return "in " + visitedStates.size() + " moves, the goal-state was not possible to find. Method: " + method + ".\n" +
                "Time used: " + (duration)  + " milliseconds.";
    }


    private String solveFaster(String state, String goalState){
        priorityQueue.clear();
        visitedStates.clear();
        visitedNodes.clear();

        System.out.println("Finding solution for 8-puzzle state " +state+ " with goal: " + goalState + ". Method: A*." );

        //Starting computing time.
        double startTime = System.nanoTime();

        //Checking parity
        if(Moves.haveDifferentParity(state,goalState)){
            double endTime = System.nanoTime();
            double duration = (endTime - startTime)/1000000  ;
            return ("The parity check shows that there is no solution." + "\n" +
                    "Time used: " + (duration)  + " milliseconds");
        }

        Node startNode = new Node(state,goalState, null);
        updatePriQueue(startNode);
        while (!priorityQueue.isEmpty()){
            Node node = priorityQueue.poll();
            if(node.isGoalState()){
                double endTime = System.nanoTime();
                double duration = (endTime - startTime)/1000000  ;
                int counter = 0;
                while (!node.isRoot()){
                    node = node.getParent();
                    counter++;
                }
                return "Goal state found in " + visitedNodes.size() + " moves. The method used was A*.\n" +
                        "Order found finds goalstate in " + counter + " moves."+"\n"+
                        "Time used: " + duration + " milliseconds.";
            }
            updatePriQueue(node);

        }
        return "Something unexpected happened. Was not able to find goalstate. Visitied: " + visitedStates.size();
    }

    private String solveFastest(String state, String goalState){
        priorityQueue2.clear();
        visitedStates.clear();
        visitedNodes2.clear();

        System.out.println("Finding solution for 8-puzzle state " +state+ " with goal: " + goalState + ". Method: A*." );

        //Starting computing time.
        double startTime = System.nanoTime();

        //Checking parity
        if(Moves.haveDifferentParity(state,goalState)){
            double endTime = System.nanoTime();
            double duration = (endTime - startTime)/1000000  ;
            return ("The parity check shows that there is no solution." + "\n" +
                    "Time used: " + (duration)  + " milliseconds");
        }

        Node2 startNode = new Node2(state,goalState, null);
        updatePriQueue2(startNode);
        while (!priorityQueue2.isEmpty()){
            Node2 node = priorityQueue2.poll();
            if(node.isGoalState()){
                double endTime = System.nanoTime();
                double duration = (endTime - startTime)/1000000  ;
                int counter = 0;
                while (!node.isRoot()){
                    node = node.getParent();
                    counter++;
                }
                return "Goal state found in " + visitedNodes2.size() + " moves. The method used was A* with Manhattan search.\n" +
                        "Order found finds goalstate in " + counter + " moves."+"\n"+
                        "Time used: " + duration + " milliseconds.";
            }
            updatePriQueue2(node);

        }
        return "Something unexpected happened. Was not able to find goalstate. Visitied: " + visitedStates.size();
    }


    public static void main(String[] args) {
        EightPuzzleSolver eightPuzzleSolver = new EightPuzzleSolver();
//
//
//        System.out.println(eightPuzzleSolver.solve("321_87654","_12345678","bfs"));
//
//        System.out.println(eightPuzzleSolver.solve("1348627_5","1238_4765","BFS"));
//        System.out.println(eightPuzzleSolver.solve("1348627_5","1238_4765","DFS"));
//        System.out.println(eightPuzzleSolver.solveFaster("1348627_5","1238_4765"));
//        System.out.println(eightPuzzleSolver.solve("281_43765","1238_4765","BFS"));
//        System.out.println(eightPuzzleSolver.solve("281_43765","1238_4765","DFS"));
//        System.out.println(eightPuzzleSolver.solveFaster("281_43765","1238_4765"));
//        System.out.println(eightPuzzleSolver.solve("281463_75","1238_4765","BFS"));
//        System.out.println(eightPuzzleSolver.solve("281463_75","1238_4765","DFS"));
//        System.out.println(eightPuzzleSolver.solveFaster("281463_75","1238_4765"));

        System.out.println(eightPuzzleSolver.solve("8672543_1","12345678_","BFS"));
        System.out.println(eightPuzzleSolver.solve("8672543_1","12345678_","DFS"));

        System.out.println(eightPuzzleSolver.solveFaster("8672543_1","12345678_"));
        System.out.println(eightPuzzleSolver.solveFastest("8672543_1","12345678_"));
    }
}
