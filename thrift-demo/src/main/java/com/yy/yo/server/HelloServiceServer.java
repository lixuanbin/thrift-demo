package com.yy.yo.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.yy.yo.service.Hello;

public class HelloServiceServer {
	/**
	 * ���� Thrift ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ���÷���˿�Ϊ 7911
			TServerSocket serverTransport = new TServerSocket(7911);
			// ����Э�鹤��Ϊ TBinaryProtocol.Factory
			Factory proFactory = new TBinaryProtocol.Factory();
			// ������������ Hello �����ʵ��
			TProcessor processor = new Hello.Processor(new HelloServiceImpl());

			// TServer server = new TSimpleServer(new
			// Args(serverTransport).processor(processor));

			// Use this for a multithreaded server
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
					serverTransport).processor(processor));

			System.out.println("Starting the simple server...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}