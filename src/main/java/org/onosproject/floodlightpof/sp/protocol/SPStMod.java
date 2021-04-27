package org.onosproject.floodlightpof.sp.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


import org.onosproject.floodlightpof.protocol.OFMessage;
import org.onosproject.floodlightpof.protocol.OFType;
import org.onosproject.floodlightpof.util.U16;
import org.jboss.netty.buffer.ChannelBuffer;

import org.slf4j.Logger;



public class SPStMod extends OFMessage {
	
public static int MINIMUM_LENGTH = 16;
    
    protected int appid;
    protected int count;
    protected static Logger logger;
   
    public class STMOD_DATA{
    	SPModType type;
    	STDATA stdata;
    
    	public STMOD_DATA(SPModType t , STDATA s){
    		type = t ;
    		stdata = s;
    	}
    	
    }
  
    protected List<STMOD_DATA> stdatas;
    
    public SPStMod(){
    	super();
    	this.type = OFType.SP_ST_MOD;
    	this.length = U16.t(MINIMUM_LENGTH);
    	appid = 0 ;
    	count = 0 ;	
    	stdatas = new ArrayList<STMOD_DATA>();
    }
    
    
    public SPStMod addSTMod(int id , SPModType t, STDATA a){
    	appid = id;
    	stdatas.add(new STMOD_DATA(t,a));
    	count = stdatas.size();
    	return this;
    }

    @Override
    public void readFrom(ChannelBuffer data) {
        super.readFrom(data);
        //done nothing 
    }

    @Override
    public void writeTo(ChannelBuffer data) {
        super.writeTo(data);
        if( stdatas == null )
        	 throw new RuntimeException("--------SPStMod  ERROR! ---------");
        computeLength();
        data.writeInt(appid);
        data.writeInt(count);
    	for(STMOD_DATA tmp : stdatas){
    		data.writeInt(tmp.type.getValue());
    		tmp.stdata.WirteTo(data);
    	}
    

    }

    public void computeLength() {
    	short len = 0;
    	if( stdatas == null )
       	 throw new RuntimeException("-----------SP STMOD ERROR!-------- ");
    
    	for(STMOD_DATA tmp : stdatas ){
    			len = (short) (len + 4 + tmp.stdata.getByteLength());
    		}
    	
    	this.length = (short) (len+4+4+8);   		
    	}

}
