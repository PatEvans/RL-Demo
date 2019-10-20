package demo;

import java.util.ArrayList;
import java.util.List;

public class MyTreeNode<T>{
    private T data = null;
    private Float value = null;
    private Integer wins=0;
    private Integer losses=0;
    private Integer draws=0;
    private Integer visitCount = null;
    private List<MyTreeNode> children = new ArrayList<>();
    private MyTreeNode parent = null;

    public MyTreeNode(T data,Float value) {
        this.data = data;
        this.value = value;
        visitCount=0;
        wins=0;
        losses=0;
        draws=0;
    }

    public void addChild(MyTreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data, Float value) {
        MyTreeNode<T> newChild = new MyTreeNode<>(data,value);
        
        this.addChild(newChild);
    }

    public void addChildren(List<MyTreeNode> children) {
        for(MyTreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<MyTreeNode> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }
    public Float getValue() {
        return value;
    }
    public Integer getVisit() {
        return visitCount;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(MyTreeNode parent) {
        this.parent = parent;
    }

    public MyTreeNode getParent() {
        return parent;
    }
    public void newVisit() {
    	visitCount++;
    }

	public void newWin() {
		wins++;
		
		calcNewScore();
	}
	public void newLoss() {
		losses++;
		calcNewScore();
	}
	public void newDraw() {
		draws++;
		calcNewScore();
	}
	private void calcNewScore() {
		//value is mean return
		value=(float) ((float) (wins- losses) -0.1*draws);
	}
	
}