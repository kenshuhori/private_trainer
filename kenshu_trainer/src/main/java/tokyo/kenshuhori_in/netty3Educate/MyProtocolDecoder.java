package tokyo.kenshuhori_in.netty3Educate;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

public class MyProtocolDecoder extends OneToOneDecoder { // ★1

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception { // ★2

		if (!(msg instanceof ChannelBuffer)) { // ★3
			return msg;
		}

		ChannelBuffer buffer = (ChannelBuffer) msg;

		// ID ★4
		long id = buffer.readLong();

		// Name ★5
		int length = buffer.readInt();
		byte[] byteName = new byte[length];
		buffer.readBytes(byteName);
		String name = new String(byteName, "UTF-8");

		// Age ★6
		int age = buffer.readInt();

		UserInfo info = new UserInfo(id, name, age);
		return info; // ★7
	}
}
