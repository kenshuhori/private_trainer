package tokyo.kenshuhori_in.netty3Educate;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * クライアント側メインクラス
 */
public class EchoClient {

    public static void main(String[] args) {
    	// client
        ChannelFactory factory = new NioClientSocketChannelFactory(
        		Executors.newCachedThreadPool(),
        		Executors.newCachedThreadPool()
        		);

        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                ChannelPipeline pipeline = Channels.pipeline();
                // Downstream(送信)
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("stringEncoder", new StringEncoder());
                // Upstream(受信)
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4));
                pipeline.addLast("stringDecoder", new StringDecoder());
                // Application Logic Handler
                pipeline.addLast("handler", new EchoClientHandler()); // client

                return pipeline;
            }
        });

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("localhost", 9999)); // 9999番ポートにconnect
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        bootstrap.releaseExternalResources();
    }
}