// (1)
package tokyo.kenshuhori_in.netty4Educate;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyProtocolEncoder extends MessageToByteEncoder<UserInfo>{
	@Override
    protected void encode(ChannelHandlerContext ctx, UserInfo info, ByteBuf buffer) throws Exception { // (3)
        // IDの変換
        buffer.writeLong(info.getId());

        // Nameの変換
        byte[] byteName = info.getName().getBytes("UTF-8");
        buffer.writeInt(byteName.length);
        buffer.writeBytes(byteName);

        // Ageの変換
        buffer.writeInt(info.getAge());
    }
}
