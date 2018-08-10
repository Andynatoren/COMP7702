import java.util.ArrayList;
import java.util.Arrays;

 class Moves {

    private static String move(String state, int index,int move){

        StringBuilder nextMove = new StringBuilder();
        nextMove.append(state);
        char a = state.charAt(index+move);
        nextMove.setCharAt(index, a);
        nextMove.setCharAt((index + move),  '_');

        return nextMove.toString();
    }

    static ArrayList<String> getPossibleMoves(String state){
        ArrayList<String> possibleMoves = new ArrayList<>();
        int index = state.indexOf("_");
        if(!(index==0||index== 3||index==6)) possibleMoves.add(move(state, index, -1));
        if(index>2) possibleMoves.add(move(state, index, -3));
        if(!(index == 2 || index==5 || index == 8) )possibleMoves.add(move(state, index, 1));
        if(index<5) possibleMoves.add(move(state, index, 3));
        return possibleMoves;
    }

    private static int parity(String state){
        int parityNumber = 0;
        ArrayList<String> StringAsList = new ArrayList<>((Arrays.asList(state.split(""))));
        StringAsList.remove("_");
        for (String character : StringAsList){
            int i = Integer.parseInt(character);
            ArrayList<String> tempList = new ArrayList<>(StringAsList.subList(StringAsList.indexOf(character),StringAsList.size()-1));
            for (String compareChar : tempList){
                if(i>Integer.parseInt(compareChar)) parityNumber++;
            }
        }
        return parityNumber % 2;
    }

    static boolean parityCheck(String state, String goalState){
        return parity(state) - parity(goalState) == 0;
    }

//    public static String singleMove(String state, String direction){
//        int index = state.indexOf("_");
//        switch (direction){
//            case "left":
//                if(!(index==0||index== 3||index==6)) {
//                    return move(state, index, -1);
//                }
//                break;
//            case "up":
//                if(index>2){
//                    return move(state, index, -3);
//                }
//                break;
//            case "right":
//                if(!(index == 2 || index==5 || index == 8) ) {
//                    return (move(state, index, 1));
//                }
//                break;
//            case "down":
//                if(index<5) {
//                    return (move(state, index, 3));
//                }
//        }
//        //f for fail, wanted move is not possible.
//        return "f";
//    }

}
