import java.util.ArrayList;
import java.util.HashSet;

public class EightPuzzleSolver {

    private ArrayList<String> queue = new ArrayList<>();
    private HashSet<String> visitedStates = new HashSet<>();



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


    private String Solve(String state, String goal_state, String method){
        queue.clear();
        visitedStates.clear();
        System.out.println("Finding solution for 8-puzzle state " +state+ " with goal: " + goal_state + ". Method: " + method);

        //Starting computing time.
        double startTime = System.nanoTime();

        //Checking parity
        if(!Moves.parityCheck(state,goal_state)){
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
            switch (method.toLowerCase()){
                case "dfs":
                    state = queue.get(queue.size()-1);
                    queue.remove(queue.size() -1 );
                    break;
                case "bfs":
                    state = queue.get(0);
                    queue.remove(0);
            }
            queue.addAll(returnNewVisits(Moves.getPossibleMoves(state)));
        }
        //Calculating computing time.
        double endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000000  ;
        return "in " + visitedStates.size() + " moves, the goal-state was not possible to find. Method: " + method + ".\n" +
                "Time used: " + (duration)  + " milliseconds.";
    }


    public static void main(String[] args) {
        EightPuzzleSolver bfs = new EightPuzzleSolver();


        System.out.println(bfs.Solve("321_87654","_12345678","bfs"));

        System.out.println(bfs.Solve("1348627_5","1238_4765","BFS"));
        System.out.println(bfs.Solve("1348627_5","1238_4765","DFS"));
        System.out.println(bfs.Solve("281_43765","1238_4765","BFS"));
        System.out.println(bfs.Solve("281_43765","1238_4765","DFS"));
        System.out.println(bfs.Solve("281463_75","1238_4765","BFS"));
        System.out.println(bfs.Solve("281463_75","1238_4765","DFS"));
    }
}
