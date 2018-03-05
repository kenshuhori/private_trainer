package tokyo.kenshuhori_in.netty4Educate;

//(1)
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * サーバ側メインクラス
 */
public class MyProtocolServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (2)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap(); // (3)
            bootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(9999)
                .option(ChannelOption.SO_BACKLOG, 100)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // Downstream(送信)
                        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                        pipeline.addLast("myEncoder", new MyProtocolEncoder());
                        // Upstream(受信)
                        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4));
                        pipeline.addLast("myDecoder", new MyProtocolDecoder());
                        // Application Logic Handler
                        pipeline.addLast("handler", new MyProtocolServerHandler()); // server
                    }
                 });

            // Start the server.
            ChannelFuture future = bootstrap.bind().sync();

            // Wait until the server socket is closed.
            future.channel().closeFuture().sync();
        } finally {
            // (4)
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

            // Wait until all threads are terminated.
            bossGroup.terminationFuture().sync();
            workerGroup.terminationFuture().sync();
        }
    }
}