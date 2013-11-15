package technique.driver;

import technique.feed.FeedPartition;
import technique.model.ITuple;
import technique.model.Operator;
import technique.model.Tuple;
import technique.service.CollectorProcessor;


/** driver consumes data from a feed partition and inserts it into operators */
public class Driver implements Runnable {
  private FeedPartition fp;
  private DriverNode driverNode;

  public Driver(FeedPartition fp, Operator operator){
    this.fp = fp;
    CollectorProcessor cp = new CollectorProcessor();
    driverNode = new DriverNode(operator, cp);
  }
  
  public void initialize(){
    driverNode.initialize();
  }
  
  /**
   * Begin processing the feed in a thread
   */
  public void run(){
    ITuple t = new Tuple();
    while (fp.next(t)){
      driverNode.getOperator().handleTuple(t);
      t = new Tuple();
    }
  }

  public DriverNode getDriverNode() {
    return driverNode;
  }

  public void setDriverNode(DriverNode driverNode) {
    this.driverNode = driverNode;
  }
  
}