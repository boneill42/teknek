package technique.daemon;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import technique.deamon.TechniqueDaemon;
import technique.kafka.EmbeddedKafkaServer;

public class TestTechniqueDaemon extends EmbeddedKafkaServer {

  static TechniqueDaemon td = null;
  
  @BeforeClass
  public static void setup(){
    Map<String,String> props = new HashMap<String,String>();
    props.put(TechniqueDaemon.ZK_SERVER_LIST, zookeeperTestServer.getConnectString());
    td = new TechniqueDaemon(props);
    td.init();
  }
  
  @Test
  public void hangAround(){
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  @AfterClass
  public static void shutdown(){
    td.stop();
  }
  
}