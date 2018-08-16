import java.io.File;

public class Node implements Comparable<Node> {


    private Node parent;
    private String state;

    public String getGoalState() {
        return goalState;
    }

    private String goalState;
    private int level;


    private Float distance;


    public Node(String state, String goalState, Node parent){
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
        this.distance = Moves.distance(state,goalState);
    }
    public boolean isGoalState(){
        return this.state.equals(this.goalState);
    }
    public boolean isRoot(){
        return this.parent == this;
    }


    public Float getDistance() {
        return distance;
    }

    public int getLevel() {
        return level;
    }
    public float getParentCost(){
        return parent.getLevel();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Node getParent() {
        if(this.isRoot()) System.out.println("Warning: This node has no parent, it's the root node.");
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
        this.level = parent.getLevel() + 1;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.getState() + "lvl: " + level + "Distance: " + distance;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node){
            return this.getState()==  ((Node) obj).getState();
        }
        else{
        return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return (Integer.parseInt(state.replace('_','0')));
    }

    public Float getCost(){
        return this.getDistance()+getLevel();
    }

    @Override
    public int compareTo(Node otherNode) {
        return this.getCost().compareTo(otherNode.getCost());
    }

}
