// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/response.proto

package com.studynetty.serialization.protobuf;

public final class ResponseProto {
  private ResponseProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ResponseOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 responseId = 1;
    /**
     * <code>required int32 responseId = 1;</code>
     */
    boolean hasResponseId();
    /**
     * <code>required int32 responseId = 1;</code>
     */
    int getResponseId();

    // required int32 requestId = 2;
    /**
     * <code>required int32 requestId = 2;</code>
     */
    boolean hasRequestId();
    /**
     * <code>required int32 requestId = 2;</code>
     */
    int getRequestId();

    // required string responseContent = 3;
    /**
     * <code>required string responseContent = 3;</code>
     */
    boolean hasResponseContent();
    /**
     * <code>required string responseContent = 3;</code>
     */
    java.lang.String getResponseContent();
    /**
     * <code>required string responseContent = 3;</code>
     */
    com.google.protobuf.ByteString
        getResponseContentBytes();
  }
  /**
   * Protobuf type {@code netty.Response}
   */
  public static final class Response extends
      com.google.protobuf.GeneratedMessage
      implements ResponseOrBuilder {
    // Use Response.newBuilder() to construct.
    private Response(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Response(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Response defaultInstance;
    public static Response getDefaultInstance() {
      return defaultInstance;
    }

    public Response getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Response(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              responseId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              requestId_ = input.readInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              responseContent_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.studynetty.serialization.protobuf.ResponseProto.internal_static_netty_Response_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.studynetty.serialization.protobuf.ResponseProto.internal_static_netty_Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.studynetty.serialization.protobuf.ResponseProto.Response.class, com.studynetty.serialization.protobuf.ResponseProto.Response.Builder.class);
    }

    public static com.google.protobuf.Parser<Response> PARSER =
        new com.google.protobuf.AbstractParser<Response>() {
      public Response parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Response(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Response> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 responseId = 1;
    public static final int RESPONSEID_FIELD_NUMBER = 1;
    private int responseId_;
    /**
     * <code>required int32 responseId = 1;</code>
     */
    public boolean hasResponseId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 responseId = 1;</code>
     */
    public int getResponseId() {
      return responseId_;
    }

    // required int32 requestId = 2;
    public static final int REQUESTID_FIELD_NUMBER = 2;
    private int requestId_;
    /**
     * <code>required int32 requestId = 2;</code>
     */
    public boolean hasRequestId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 requestId = 2;</code>
     */
    public int getRequestId() {
      return requestId_;
    }

    // required string responseContent = 3;
    public static final int RESPONSECONTENT_FIELD_NUMBER = 3;
    private java.lang.Object responseContent_;
    /**
     * <code>required string responseContent = 3;</code>
     */
    public boolean hasResponseContent() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string responseContent = 3;</code>
     */
    public java.lang.String getResponseContent() {
      java.lang.Object ref = responseContent_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          responseContent_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string responseContent = 3;</code>
     */
    public com.google.protobuf.ByteString
        getResponseContentBytes() {
      java.lang.Object ref = responseContent_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        responseContent_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      responseId_ = 0;
      requestId_ = 0;
      responseContent_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasResponseId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRequestId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasResponseContent()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, responseId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, requestId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getResponseContentBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, responseId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, requestId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getResponseContentBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.studynetty.serialization.protobuf.ResponseProto.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.studynetty.serialization.protobuf.ResponseProto.Response prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code netty.Response}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.studynetty.serialization.protobuf.ResponseProto.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.studynetty.serialization.protobuf.ResponseProto.internal_static_netty_Response_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.studynetty.serialization.protobuf.ResponseProto.internal_static_netty_Response_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.studynetty.serialization.protobuf.ResponseProto.Response.class, com.studynetty.serialization.protobuf.ResponseProto.Response.Builder.class);
      }

      // Construct using com.studynetty.serialization.protobuf.ResponseProto.Response.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        responseId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        requestId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        responseContent_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.studynetty.serialization.protobuf.ResponseProto.internal_static_netty_Response_descriptor;
      }

      public com.studynetty.serialization.protobuf.ResponseProto.Response getDefaultInstanceForType() {
        return com.studynetty.serialization.protobuf.ResponseProto.Response.getDefaultInstance();
      }

      public com.studynetty.serialization.protobuf.ResponseProto.Response build() {
        com.studynetty.serialization.protobuf.ResponseProto.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.studynetty.serialization.protobuf.ResponseProto.Response buildPartial() {
        com.studynetty.serialization.protobuf.ResponseProto.Response result = new com.studynetty.serialization.protobuf.ResponseProto.Response(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.responseId_ = responseId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.requestId_ = requestId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.responseContent_ = responseContent_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.studynetty.serialization.protobuf.ResponseProto.Response) {
          return mergeFrom((com.studynetty.serialization.protobuf.ResponseProto.Response)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.studynetty.serialization.protobuf.ResponseProto.Response other) {
        if (other == com.studynetty.serialization.protobuf.ResponseProto.Response.getDefaultInstance()) return this;
        if (other.hasResponseId()) {
          setResponseId(other.getResponseId());
        }
        if (other.hasRequestId()) {
          setRequestId(other.getRequestId());
        }
        if (other.hasResponseContent()) {
          bitField0_ |= 0x00000004;
          responseContent_ = other.responseContent_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasResponseId()) {
          
          return false;
        }
        if (!hasRequestId()) {
          
          return false;
        }
        if (!hasResponseContent()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.studynetty.serialization.protobuf.ResponseProto.Response parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.studynetty.serialization.protobuf.ResponseProto.Response) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 responseId = 1;
      private int responseId_ ;
      /**
       * <code>required int32 responseId = 1;</code>
       */
      public boolean hasResponseId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 responseId = 1;</code>
       */
      public int getResponseId() {
        return responseId_;
      }
      /**
       * <code>required int32 responseId = 1;</code>
       */
      public Builder setResponseId(int value) {
        bitField0_ |= 0x00000001;
        responseId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 responseId = 1;</code>
       */
      public Builder clearResponseId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        responseId_ = 0;
        onChanged();
        return this;
      }

      // required int32 requestId = 2;
      private int requestId_ ;
      /**
       * <code>required int32 requestId = 2;</code>
       */
      public boolean hasRequestId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 requestId = 2;</code>
       */
      public int getRequestId() {
        return requestId_;
      }
      /**
       * <code>required int32 requestId = 2;</code>
       */
      public Builder setRequestId(int value) {
        bitField0_ |= 0x00000002;
        requestId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 requestId = 2;</code>
       */
      public Builder clearRequestId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        requestId_ = 0;
        onChanged();
        return this;
      }

      // required string responseContent = 3;
      private java.lang.Object responseContent_ = "";
      /**
       * <code>required string responseContent = 3;</code>
       */
      public boolean hasResponseContent() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string responseContent = 3;</code>
       */
      public java.lang.String getResponseContent() {
        java.lang.Object ref = responseContent_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          responseContent_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string responseContent = 3;</code>
       */
      public com.google.protobuf.ByteString
          getResponseContentBytes() {
        java.lang.Object ref = responseContent_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          responseContent_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string responseContent = 3;</code>
       */
      public Builder setResponseContent(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        responseContent_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string responseContent = 3;</code>
       */
      public Builder clearResponseContent() {
        bitField0_ = (bitField0_ & ~0x00000004);
        responseContent_ = getDefaultInstance().getResponseContent();
        onChanged();
        return this;
      }
      /**
       * <code>required string responseContent = 3;</code>
       */
      public Builder setResponseContentBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        responseContent_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:netty.Response)
    }

    static {
      defaultInstance = new Response(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:netty.Response)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_netty_Response_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_netty_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024proto/response.proto\022\005netty\"J\n\010Respons" +
      "e\022\022\n\nresponseId\030\001 \002(\005\022\021\n\trequestId\030\002 \002(\005" +
      "\022\027\n\017responseContent\030\003 \002(\tB6\n%com.studyne" +
      "tty.serialization.protobufB\rResponseProt" +
      "o"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_netty_Response_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_netty_Response_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_netty_Response_descriptor,
              new java.lang.String[] { "ResponseId", "RequestId", "ResponseContent", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
