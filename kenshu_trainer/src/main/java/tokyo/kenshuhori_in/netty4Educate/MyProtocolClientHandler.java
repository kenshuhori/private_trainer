package tokyo.kenshuhori_in.netty4Educate;

//(1)
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * クライアント側アプリケーションロジック
 */
public class MyProtocolClientHandler extends ChannelInboundHandlerAdapter {
	/**
     * サーバに接続した際に呼び出されるメソッド
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (3)
        // サーバに送信する情報を生成
        UserInfo info = new UserInfo(100, "taro", 20);
        // サーバに送信
        ctx.writeAndFlush(info); // (4)
    }
}
