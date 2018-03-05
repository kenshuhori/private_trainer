package tokyo.kenshuhori_in.netty3Educate;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class MyProtocolClientHandler extends SimpleChannelHandler {

	/**
     * サーバに接続した際に呼び出されるメソッド
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent event) {
        UserInfo info = new UserInfo(4524, "Kenshu", 25);
        //サーバーに送信
        ctx.getChannel().write(info);
    }

    /**
     * サーバから電文を受信した際に呼び出されるメソッド
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) {
        UserInfo info = (UserInfo) event.getMessage();
        System.out.println(info.toString());
    }
}
