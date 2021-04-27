package org.onosproject.floodlightpof.sp.protocol;

import org.jboss.netty.buffer.ChannelBuffer;

import java.lang.String;
import org.onosproject.floodlightpof.protocol.OFMatchX;

public class ATDATA {
	
	SPAction act;
	int status;
	int len_of_value;
	protected byte[] value;

	public ATDATA(int stats, byte[] value , SPAction at)
	{
		act = at;
		this.value = value;
		len_of_value = value.length;
		status = stats;
	}
	
	public ATDATA(){
		status = 0 ;
		act = null;
	}
	
	public ATDATA setAction( SPAction at){
		act = at ;
		return this;
	}
	
	public ATDATA setStatus( int stats )
	{
		status = stats;
		return this;
	}
	public int getStatus(){
		return status;
	}

	public ATDATA setvalue( byte[] value ){
		this.value = value;
		return this;
	}
	public String getvalue(){
		return new String(value);
	}
	
	public void WirteTo(ChannelBuffer data) {
		act.WriteTo(data);
		data.writeInt(status);
		data.writeInt(len_of_value);
		if (this.value == null) {
			data.writeZero(8);
		} else if (this.value.length > 8) {
			data.writeBytes(this.value, this.value.length - 8, 8);
		} else {
			data.writeBytes(this.value);
			data.writeZero(8 - this.value.length);
		}
	}
	
	public short getByteLength()
	{
		return (short) (act.getByteLength()+ 4 + 4 + 8);
	}

}
