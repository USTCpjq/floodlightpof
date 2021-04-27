package org.onosproject.floodlightpof.sp.protocol;

import org.onosproject.floodlightpof.protocol.OFMessage;
import org.onosproject.floodlightpof.protocol.OFType;
import org.onosproject.floodlightpof.util.U16;
import org.slf4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;


/*
	Author : eric
	Description : create the sfa create msg
	

	msg pattern:
	
	of_header:
		version(4)
		type
		length
		xid
	sfa_body: 
		SFASt.appid
		SFASt.bitmap
		SFASt.count
		... count of below...
		SFASt.status
		SFASt.data.len
		FSASt.data
		..........
		
		SFAStt.count
		.....count of below...
		SFAStt.param1type
		SFAStt.param1
		SFAStt.param2type
		SFAStt.param2
		SFAStt.op
		..........
		
		SFAAt.count
		.....count of below...
		SFAAt.matchmp
		SFAAt.status
		OFAction
		..........

*/

public class SPCreate extends OFMessage {
    public static int MINIMUM_LENGTH = 136;

    protected SPSt stbody;
    protected SPStt sttbody;
    protected SPAt atbody;
    protected static Logger logger;
    
 
    public SPCreate() {
        super();
        //this.computeLength();
        this.type = OFType.SP_CREATE;
        this.length = U16.t(MINIMUM_LENGTH);
        this.stbody = null;
        this.sttbody = null;
        this.atbody = null;
        
    }

    public SPCreate setST(SPSt st){
    	this.stbody = st;
    	return this;
    }
    public SPCreate setSTT(SPStt stt){
    	this.sttbody = stt;
    	return this;
    }
    public SPCreate setAT(SPAt at){
    	this.atbody = at;
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
        if( this.stbody == null || this.sttbody == null || this.atbody == null)
        	 throw new RuntimeException("SP INIT ERROR! ");
        computeLength();
        stbody.writeTo(data);
        sttbody.writeTo(data);
        atbody.writeTo(data);
    }

    /* (non-Javadoc)
     * @see org.openflow.protocol.OFMessage#computeLength()
     */
    public void computeLength() {
    	if( this.stbody == null || this.sttbody == null || this.atbody == null)
       	 throw new RuntimeException("SP CREATE COMPUTE LEN ERROR! ");
    	this.length = (short) (stbody.getByteLength()+sttbody.getByteLength()+atbody.getByteLength()+8);
    }
    

}
