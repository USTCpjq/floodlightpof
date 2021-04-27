package org.onosproject.floodlightpof.protocol.instruction;

import java.nio.channels.Channel;
import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.floodlightpof.util.HexString;


/**
 * Represents an ofp_instruction_GOTO_SP
 * @author chen sun (sunchen2050@163.com)
 */
public class OFInstructionGotoSP extends OFInstruction {
    public static int MINIMUM_LENGTH = 16;
    protected byte bitmap;

    public OFInstructionGotoSP() {
        super.setType(OFInstructionType.GOTO_SP);
        super.setLength((short) 304);
    }

    public OFInstructionGotoSP(byte bitmap) {
        super.setType(OFInstructionType.GOTO_SP);
        super.setLength((short) 304);
        this.bitmap = bitmap;
    }

    /**
     * @return the bitmap
     */
    public byte getBitmap() {
        return bitmap;
    }

    /**
     * @param bitmap the bitmap to set
     */
    public OFInstructionGotoSP setBitmap(byte bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public void readFrom(ChannelBuffer data) {
        super.readFrom(data);
        this.bitmap = data.readByte();
        data.readBytes(7);
        data.readBytes(288);
    }

    public void writeTo(ChannelBuffer data) {
        super.writeTo(data);
        data.writeByte(this.bitmap);
        data.writeZero(7);
        data.writeZero(288);
    }

    @Override
    public int hashCode() {
        final int prime = 347;
        int result = super.hashCode();
        result = prime * result + bitmap;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof OFInstructionGotoSP)) {
            return false;
        }
        OFInstructionGotoSP other = (OFInstructionGotoSP) obj;
        if (bitmap != other.bitmap) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OFInstructionGotoSP [bitmap=" + bitmap + "]";
    }
}

