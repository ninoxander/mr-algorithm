package ninoxit.mr;

import jade.core.Agent;
import ninoxit.mr.behaviours.MRBehaviour;

public class MRAgent extends Agent{
    @Override
    public void setup() {
        addBehaviour(new MRBehaviour());
    }
}