package tokyo.kenshuhori_in.netty4Educate;

// (1)
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * クライアント側メインクラス
 */
public class MyProtocolClient {

    public static void main(String[] args) throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup(); // (2)

        try {
            Bootstrap bootstrap = new Bootstrap(); // (3)
            bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress("localhost", 9999)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // Downstream(送信)
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("myEncoder", new MyProtocolEncoder());
                        // Upstream(受信)
                        pipeline.addLast("frameDecoder",
                                new LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4));
                        pipeline.addLast("myDecoder", new MyProtocolDecoder());
                        // Application Logic Handler
                        pipeline.addLast("handler", new MyProtocolClientHandler()); // client
                    }
                 });

            // Start the client.
            ChannelFuture future = bootstrap.connect().sync(); // 9999番ポートにconnect

            // Wait until the connection is closed.
            future.channel().closeFuture().sync();
        } finally {
            // (4)
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}