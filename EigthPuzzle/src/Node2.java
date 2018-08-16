 class Node2 implements Comparable<Node2> {


    private Node2 parent;
    private String state;

     String getGoalState() {
        return goalState;
    }

    private String goalState;
    private int level;


    private Float distance;


     Node2(String state, String goalState, Node2 parent){
        if(parent == null){
            this.parent = this;
            this.level = 0;
        }
        else{
            this.level = parent.getLevel()+1;
            this.parent = parent;
        }
        this.state = state;
        this.goalState = goalState;
        this.distance = Moves.improvedDistance(state,goalState);
    }
     boolean isGoalState(){
        return this.state.equals(this.goalState);
    }
    
     boolean isRoot(){
        return this.parent == this;
    }


     Float getDistance() {
        return distance;
    }

     int getLevel() {
        return level;
    }
     float getParentCost(){
        return parent.getLevel();
    }

     void setLevel(int level) {
        this.level = level;
    }

     Node2 getParent() {
        if(this.isRoot()) System.out.println("Warning: This Node2 has no parent, it's the root Node2.");
        return parent;
    }

     void setParent(Node2 parent) {
        this.parent = parent;
        this.level = parent.getLevel() + 1;
    }

     String getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.getState() + "lvl: " + level + "Distance: " + distance;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node2){
            return this.getState()==  ((Node2) obj).getState();
        }
        else{
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return (Integer.parseInt(state.replace('_','0')));
    }

     Float getCost(){
        return this.getDistance()+getLevel();
    }

    @Override
    public int compareTo(Node2 otherNode2) {
        return this.getCost().compareTo(otherNode2.getCost());
    }
    
}
