package tokyo.kenshuhori_in.netty3Educate;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * サーバ側アプリケーションロジック
 */
public class EchoServerHandler extends SimpleChannelHandler {

	private String thx = "Thank you Mr.";
    /**
     * クライアントから電文を受信した際に呼び出されるメソッド
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) {
        String msg = (String) event.getMessage(); // 受信電文を取りだす
        ctx.getChannel().write(thx + msg); // クライアントに送信
    }
}