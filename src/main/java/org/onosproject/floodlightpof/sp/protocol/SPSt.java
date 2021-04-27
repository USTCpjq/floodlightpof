package org.onosproject.floodlightpof.sp.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.floodlightpof.protocol.OFMatchX;
import org.onosproject.floodlightpof.protocol.OFMatch20;

import java.util.ArrayList;
import java.util.List;

public class SPSt
{
	protected int appid;
    protected int count;
    protected OFMatch20 st_match = new OFMatch20();
     
	protected List<STDATA> stdatas;
    
    //construct
    public SPSt(){
    	appid = 0;
    	count = 0;
    	stdatas = new ArrayList<STDATA>();
    	stdatas.clear(); 	
    }
    
    //set appid
    public SPSt setAppid(int aid){
    	appid = aid;
    	return this;
    }
    public int getAppid(){
    	return appid;
    }

    
    public SPSt addStData(STDATA stdata)
    {
    	stdatas.add(stdata);
    	count = stdatas.size();
    	return this;
    }

    public SPSt setmatch(OFMatch20 match20) {
        try {
            st_match = match20.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }
  
    public void writeTo(ChannelBuffer data) {
        data.writeInt(appid);
        data.writeInt(count);
        st_match.writeTo(data);

       for( STDATA tmp : this.stdatas){
    	   tmp.WirteTo(data);
       }
        
    }
    
    @Override
    public int hashCode() {
        final int prime = 311;
        int result = super.hashCode();
        result = prime * result + appid;
        result = (int) (prime * result);
        result = prime * result + count;
        result = prime * result + stdatas.hashCode();
        return result;
    }
    
    public short getByteLength(){
    	short len = 0 ;
    	for( STDATA tmp : stdatas){
    		len = (short) (len + tmp.getByteLength());
    	}
    	return (short) (len+4+8+4);
    }
       
}