package tokyo.kenshuhori_in.netty3Educate;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * クライアント側アプリケーションロジック
 */
public class EchoClientHandler extends SimpleChannelHandler {

    /**
     * サーバに接続した際に呼び出されるメソッド
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent event) {
        ctx.getChannel().write("Hori");
    }

    /**
     * サーバから電文を受信した際に呼び出されるメソッド
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) {
        String msg = (String) event.getMessage();
        System.out.println(msg);
    }
}