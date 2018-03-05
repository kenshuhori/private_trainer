package tokyo.kenshuhori_in.netty4Educate;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MyProtocolDecoder extends ByteToMessageDecoder {

	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception { // (3)
        // ID
        long id = buffer.readLong();

        // Name
        int length = buffer.readInt();
        byte[] byteName = new byte[length];
        buffer.readBytes(byteName);
        String name = new String(byteName, "UTF-8");

        // Age
        int age = buffer.readInt();

        UserInfo info = new UserInfo(id, name, age);
        out.add(info); // (4)
    }

}
