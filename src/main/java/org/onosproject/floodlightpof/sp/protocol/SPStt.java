package org.onosproject.floodlightpof.sp.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import java.util.ArrayList;
import java.util.List;

public class SPStt {
	
	
	protected int count;
	protected List<STTDATA> sttdatas;
	
	public SPStt(){
		this.count = 0 ;
		this.sttdatas = new ArrayList<STTDATA>();
		sttdatas.clear();
	}
	
	public int getCount(){
		return count;
	}
	
	public SPStt addSttData(STTDATA sttdat){
		sttdatas.add(sttdat);
		count = sttdatas.size();
		return this;
		
	}
	
	public void writeTo(ChannelBuffer data) {
		data.writeInt(count);
		for(STTDATA tmp : sttdatas){
			tmp.WriteTo(data);
		}
	}
	
	public short getByteLength(){
    	short len = 0 ;
    	for( STTDATA tmp : sttdatas){
    		len = (short) (len + tmp.getByteLength());
    	}
    	return (short) (len+4);
    }

	
}