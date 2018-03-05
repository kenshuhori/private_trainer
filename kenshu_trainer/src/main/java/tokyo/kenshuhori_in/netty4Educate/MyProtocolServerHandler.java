package tokyo.kenshuhori_in.netty4Educate;

//(1)
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * サーバ側アプリケーションロジック
 */
public class MyProtocolServerHandler extends SimpleChannelInboundHandler<UserInfo> {
	/**
     * クライアントからメッセージを受信した際に呼び出されるメソッド
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserInfo msg) throws Exception { // (3)
        System.out.println(msg); // 受信メッセージを表示
    }
}
