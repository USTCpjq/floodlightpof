package org.onosproject.floodlightpof.sp.protocol;

//import java.nio.ByteBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.floodlightpof.protocol.OFMatch20;

import java.util.ArrayList;
import java.util.List;

//import org.openflow.protocol.action.OFAction;


public class SPAt {

	  protected OFMatch20 at_match = new OFMatch20();
	  protected int  counts;
	  protected List<ATDATA> atdatas;
	  
	  
	  public SPAt(){
		  this.counts = 0 ;
		  this.atdatas = new ArrayList<ATDATA>();
		  atdatas.clear();
	  }

	  
	  public int getCounts(){
		  return this.counts;
	  }
	  public SPAt addAtData(ATDATA atdat){
		  atdatas.add(atdat);
		  counts = atdatas.size();
		  return this;
	  }

	public SPAt setmatch(OFMatch20 match20) {
		try {
			at_match = match20.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return this;
	}
	  
	  public void writeTo(ChannelBuffer data) {
		  data.writeInt(counts);
		  at_match.writeTo(data);
	      for(ATDATA tmp : atdatas ){
	    	  tmp.WirteTo(data);
	      }	        
	    }
	  
	  public int getByteLength(){
		  int len = 0;
		  for( ATDATA tmp : atdatas){
			 len = len + tmp.getByteLength();
		  }
		  return len+4+8;
	  }
	  
}