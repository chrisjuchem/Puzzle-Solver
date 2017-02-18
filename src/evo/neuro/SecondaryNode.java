package evo.neuro;

import java.util.HashMap;
import java.util.Map;

import evo.util.Rand;

public class SecondaryNode extends Node {

  private Map<Node, Double> inputs;
  private double selfVal;    //constant input value to this node
  private double selfWeight; //weighted similarly to a single other input

  SecondaryNode(double selfVal,double selfWeight){
    inputs = new HashMap<>();
    this.selfVal = selfVal;
    this.selfWeight = selfWeight;
  }

  SecondaryNode(){
    this(Rand.rand.nextDouble(), Rand.rand.nextDouble());
  }

  void connect(Node n, double weight) {
    inputs.put(n, weight);
  }

  @Override
  void calcVal() {
    double sum = selfVal * selfWeight;
    double potential = selfWeight;
    for (Node n : inputs.keySet()) {
      potential += inputs.get(n);
      sum += inputs.get(n) * n.getVal();
    }
    this.value = sum / potential;
  }
}
