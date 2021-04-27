package org.onosproject.floodlightpof.sp.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

//import net.floodlightcontroller.core.IOFSwitch;

//import org.openflow.protocol.OFMessage;
import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.floodlightpof.protocol.OFGlobal;
import org.onosproject.floodlightpof.protocol.OFMessage;
//import org.openflow.protocol.OFType;
import org.onosproject.floodlightpof.protocol.OFType;
import org.onosproject.floodlightpof.util.U16;
import org.slf4j.Logger;
import org.onosproject.floodlightpof.protocol.OFMatchX;


/*
	Author : eric
	Description : create the sfa at mode  msg
	
	msg pattern:
	
	of_header:
		version(4)
		type
		length
		xid
	sfa_body:
		SFAAt.tableid
		SFAAt.count
		... count of below...
		SFAAt.modtype
		SFAAt.atdata
		..........
*/


public class SPAtMod extends OFMessage{
	public static int MINIMUM_LENGTH = 16;
    
    protected int appid;
    protected int count;
    protected static Logger logger;

//    public enum SPAtEntryCmd {
//		ENTRY_ADD,
//		ENTRY_MODIFY,
//		ENTRY_DELETE
//	}
//
//	protected byte command;
//    protected byte matchFieldNum;
//    protected byte actionNum;
//    SPAction act;
//    protected OFMatchX matchX;
//    protected List<OFMatchX> matchList;
//    protected List<SPAction> actionList;

    
    public class ATMOD_DATA{
    	SPModType type;
    	ATDATA atdata;
    
    	public ATMOD_DATA(SPModType t , ATDATA a){
    		type = t ;
    		atdata = a;
    	}
    	
    }
  
    protected List<ATMOD_DATA> atdatas;
    
    public SPAtMod(){
    	super();
    	this.type = OFType.SP_AT_MOD;
    	this.length = U16.t(MINIMUM_LENGTH);
    	appid = 0 ;
    	count = 0 ;	
    	atdatas = new ArrayList<ATMOD_DATA>();
    }


    
    public SPAtMod addATMod(int id , SPModType t, ATDATA a){
    	appid = id;
    	atdatas.add(new ATMOD_DATA(t,a));
    	count = atdatas.size();
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
        if( atdatas == null )
        	 throw new RuntimeException("--------SPAtMod  ERROR! ---------");
        computeLength();
        data.writeInt(appid);
        data.writeInt(count);
    	for(ATMOD_DATA tmp : atdatas){
    		data.writeInt(tmp.type.getValue());
    		tmp.atdata.WirteTo(data);
    	}
    

    }
    
//    public boolean sendmsg( IOFSwitch sw ){
//    	 try {
//		     sw.write(this, null);
//		     logger.info("mod msg send !");
//		     return true;
//		 } catch (IOException e) {
//		     logger.error("Failed to write mod msg to siwtch");
//		     return false;
//		 }
//    }

    /* (non-Javadoc)
     * @see org.openflow.protocol.OFMessage#computeLength()
     */
//    @Override
    public void computeLength() {
    	short len = 0;
    	if( atdatas == null )
       	 throw new RuntimeException("-----------SP ATMOD ERROR!-------- ");
    
    	for(ATMOD_DATA tmp : atdatas ){
    			len = (short) (len + 4 + tmp.atdata.getByteLength());
    		}
    	
    	this.length = (short) (len+4+4+8);   		
    	}


}
