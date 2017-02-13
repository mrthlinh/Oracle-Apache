
// ID = 2889176
import org.apache.mina.common.ByteBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

//ID = 2889176
public class Apache_class_9 {
	public abstract class CustomProtocolDecoder extends CumulativeProtocolDecoder{
		byte currentCmd = -1;
		int currentSize = -1;
		boolean isFirst = false;
		protected boolean doDecode(IoSession is, ByteBuffer bb, ProtocolDecoderOutput pdo) throws Exception {
		        if(currentCmd == -1)
		        {
		            currentCmd = bb.get();
//		            currentSize = Packet.getSize(currentCmd);
		            isFirst = true;
		        }
		        while(bb.remaining() > 0)
		        {
		            if(!isFirst)
		            {
		                currentCmd = bb.get();
//		                currentSize = Packet.getSize(currentCmd);
		            }
		            else
		                isFirst = false;
		            //System.err.println(currentCmd + " " + bb.remaining() + " " + currentSize);
		            if(bb.remaining() >= currentSize - 1)
		            {
//		                Packet p = PacketDecoder.decodePacket(bb, currentCmd);
//		                pdo.write(arg0);
		            }
		            else
		            {
		                bb.flip();
		                return false;
		            }
		        }
		        if(bb.remaining() == 0)
		            return true;
		        else
		            return false;
		}
	}
}
