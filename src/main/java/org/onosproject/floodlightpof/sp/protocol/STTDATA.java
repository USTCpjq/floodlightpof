package org.onosproject.floodlightpof.sp.protocol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.floodlightpof.protocol.OFMatchX;


public class STTDATA{
	
//		protected SFAEventType 	paramlefttype;
//		protected long paramleft;

//		protected SFAEventType 	paramrighttype;
//		protected long paramright;
	    protected OFMatchX paramleft;
	    protected OFMatchX paramright;
		protected SPEventOp opt;
		protected int prestatus;
		protected int nextstatus;
		
		public STTDATA(OFMatchX paramleft, OFMatchX paramright,
                       SPEventOp op, int  prestat, int nextstat){
			try {
				this.paramleft = paramleft.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			try {
				this.paramright = paramright.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			opt = op;
			prestatus = prestat;
			nextstatus = nextstat;
		}
		
		public STTDATA( ){
			paramleft = null;
			paramright = null;
			opt = SPEventOp.OPRATOR_NON;
			prestatus = 0 ;
			nextstatus = 0 ;
		}
		
		public STTDATA setLeft( OFMatchX paramleft){
			try {
				this.paramleft = paramleft.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return this;
		}
		public STTDATA setRight( OFMatchX paramright){
			try {
				this.paramright = paramright.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return this;
		}
		public STTDATA setOp( SPEventOp op){
			opt = op;
			return this;
		}
		public STTDATA setStatus( int prev , int next){
			prestatus = prev;
			nextstatus = next;
			return this;
		}
		
		public void WriteTo(ChannelBuffer data){
			paramleft.writeTo(data);
			paramright.writeTo(data);
			data.writeInt(opt.getValue());
			data.writeInt(prestatus);
			data.writeInt(nextstatus);
			data.writeZero(4);
		}
		
		public short getByteLength(){
			return 96;
		}
		

	
}