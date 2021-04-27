package org.onosproject.floodlightpof.sp.protocol;

//import java.nio.ByteBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.floodlightpof.protocol.OFMatchX;
import java.lang.String;

public class STDATA {
	
	int status;
	int len_of_value;
	protected byte[] value;
	
	public STDATA(int stats, byte[] value)
	{
		status = stats;
		len_of_value = value.length;
		this.value = value;
	}
	
	public STDATA(){
		status = 0 ;
	}
	
	public STDATA setStatus( int stats )
	{
		status = stats;
		return this;
	}
	public int getStatus(){
		return status;
	}
	
	public STDATA setvalue( byte[] value ){
		this.value = value;
		return this;
	}
	public String getvalue(){
		return new String(value);
	}
	
	public void WirteTo(ChannelBuffer data)
	{
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
		return (short) (4 + 4 + 8);
	}

}
