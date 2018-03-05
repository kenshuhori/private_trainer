package tokyo.kenshuhori_in.netty3Educate;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class MyProtocolEncoder extends OneToOneEncoder {

	@Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception { // ★2

        if (!(msg instanceof UserInfo)) { // ★3
            return msg;
        }

        UserInfo info = (UserInfo) msg;
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(); // ★4

        // IDの変換 // ★5
        buffer.writeLong(info.getId());

        // Nameの変換 // ★6
        byte[] byteName = info.getName().getBytes("UTF-8");
        buffer.writeInt(byteName.length);
        buffer.writeBytes(byteName);

        // Ageの変換 // ★7
        buffer.writeInt(info.getAge());

        return buffer; // ★8
    }
}
