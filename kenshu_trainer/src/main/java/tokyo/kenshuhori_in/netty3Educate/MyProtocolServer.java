package tokyo.kenshuhori_in.netty3Educate;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class MyProtocolServer {
	public static void main(String[] args) {
    	// server
        ChannelFactory factory = new NioServerSocketChannelFactory(
        		Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()
                );

        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                ChannelPipeline pipeline = Channels.pipeline();
                // Downstream(送信)
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("myEncoder", new MyProtocolEncoder());
                // Upstream(受信)
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4));
                pipeline.addLast("myDecoder", new MyProtocolDecoder());
                // Application Logic Handler
                pipeline.addLast("handler", new MyProtocolServerHandler()); // server

                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(4524)); // 4524番ポートでlisten
    }
}
