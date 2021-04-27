package org.onosproject.floodlightpof.sp.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onlab.packet.IPv4;


public class SPAction {
	
	public enum ActType
	{
        ACT_NON	    	 (0),
	    ACT_OUTPUT    	 (1),
	    ACT_DROP     	 (2),
	    ACT_TOOPENFLOW	 (3),
	    ACT_SETSRCFIELD  (4),
	    ACT_SETDSTFIELD  (5);
		
	    protected int value;

	    ActType(int value) {
	        this.value = value;
	    }

	    /**
	     * @return the
	     * value
	     */
	    public int getValue() {
	        return value;
	    }
	}

	protected ActType type;
	protected int param;
	
	public SPAction(){
		type = ActType.ACT_TOOPENFLOW;
		param = 0 ;
	}
	
	public SPAction(ActType t , int p ){
		type = t ;
		param = p;
	}
	public SPAction(ActType t , String ip ){
		type = t ;
		param = IPv4.toIPv4Address(ip);
	}
	
	public SPAction setAction(ActType t , int p ){
		type = t;
		param = p;
		return this;
	}
	
	public void WriteTo(ChannelBuffer data){
		data.writeInt(type.getValue());
		data.writeInt(param);
	}
	
	public short getByteLength(){
		return 8;
	}
	
	
}
