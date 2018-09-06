package com.studynetty.serialization.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * ����protobuf��ʹ��
 * @author Administrator
 *
 */
public class TestProto {
	//���л�
	private static byte[] encoder(RequestProto.Request request){
		return request.toByteArray();
	}
	
	//�����л�
	private static RequestProto.Request decoder(byte[] bytes) throws InvalidProtocolBufferException{
		return RequestProto.Request.parseFrom(bytes);
	}
	
	//������
	private static RequestProto.Request create(){
		RequestProto.Request.Builder build = RequestProto.Request.newBuilder();
		build.setRequestId(1);
		build.setRequestContent("protobuf request");
		
		return build.build();
	}
	
	
	public static void main(String[] args) throws InvalidProtocolBufferException{
		RequestProto.Request request1 = TestProto.create();
		System.out.println("request1:"+request1.toString());
		
		RequestProto.Request request2 = decoder(encoder(request1));
		System.out.println("request2:"+request2.toString());
		
		System.out.println("request1==request2:"+request1.equals(request2));
	}

}
