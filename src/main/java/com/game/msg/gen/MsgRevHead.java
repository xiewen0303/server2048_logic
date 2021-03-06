// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: msgRevHead.proto

package com.game.msg.gen;

public final class MsgRevHead {
  private MsgRevHead() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MsgBaseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:AutoMsg.MsgBase)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 MsgID = 1;</code>
     */
    int getMsgID();

    /**
     * <code>int32 Result = 2;</code>
     */
    int getResult();

    /**
     * <code>bytes Data = 3;</code>
     */
    com.google.protobuf.ByteString getData();

    /**
     * <code>string ErrorMsg = 4;</code>
     */
    java.lang.String getErrorMsg();
    /**
     * <code>string ErrorMsg = 4;</code>
     */
    com.google.protobuf.ByteString
        getErrorMsgBytes();
  }
  /**
   * <pre>
   * [START messages]
   * </pre>
   *
   * Protobuf type {@code AutoMsg.MsgBase}
   */
  public  static final class MsgBase extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:AutoMsg.MsgBase)
      MsgBaseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MsgBase.newBuilder() to construct.
    private MsgBase(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MsgBase() {
      msgID_ = 0;
      result_ = 0;
      data_ = com.google.protobuf.ByteString.EMPTY;
      errorMsg_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private MsgBase(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              msgID_ = input.readInt32();
              break;
            }
            case 16: {

              result_ = input.readInt32();
              break;
            }
            case 26: {

              data_ = input.readBytes();
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              errorMsg_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.msg.gen.MsgRevHead.internal_static_AutoMsg_MsgBase_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.msg.gen.MsgRevHead.internal_static_AutoMsg_MsgBase_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.msg.gen.MsgRevHead.MsgBase.class, com.game.msg.gen.MsgRevHead.MsgBase.Builder.class);
    }

    public static final int MSGID_FIELD_NUMBER = 1;
    private int msgID_;
    /**
     * <code>int32 MsgID = 1;</code>
     */
    public int getMsgID() {
      return msgID_;
    }

    public static final int RESULT_FIELD_NUMBER = 2;
    private int result_;
    /**
     * <code>int32 Result = 2;</code>
     */
    public int getResult() {
      return result_;
    }

    public static final int DATA_FIELD_NUMBER = 3;
    private com.google.protobuf.ByteString data_;
    /**
     * <code>bytes Data = 3;</code>
     */
    public com.google.protobuf.ByteString getData() {
      return data_;
    }

    public static final int ERRORMSG_FIELD_NUMBER = 4;
    private volatile java.lang.Object errorMsg_;
    /**
     * <code>string ErrorMsg = 4;</code>
     */
    public java.lang.String getErrorMsg() {
      java.lang.Object ref = errorMsg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        errorMsg_ = s;
        return s;
      }
    }
    /**
     * <code>string ErrorMsg = 4;</code>
     */
    public com.google.protobuf.ByteString
        getErrorMsgBytes() {
      java.lang.Object ref = errorMsg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        errorMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (msgID_ != 0) {
        output.writeInt32(1, msgID_);
      }
      if (result_ != 0) {
        output.writeInt32(2, result_);
      }
      if (!data_.isEmpty()) {
        output.writeBytes(3, data_);
      }
      if (!getErrorMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, errorMsg_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (msgID_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, msgID_);
      }
      if (result_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, result_);
      }
      if (!data_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, data_);
      }
      if (!getErrorMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, errorMsg_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.game.msg.gen.MsgRevHead.MsgBase)) {
        return super.equals(obj);
      }
      com.game.msg.gen.MsgRevHead.MsgBase other = (com.game.msg.gen.MsgRevHead.MsgBase) obj;

      boolean result = true;
      result = result && (getMsgID()
          == other.getMsgID());
      result = result && (getResult()
          == other.getResult());
      result = result && getData()
          .equals(other.getData());
      result = result && getErrorMsg()
          .equals(other.getErrorMsg());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + MSGID_FIELD_NUMBER;
      hash = (53 * hash) + getMsgID();
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getResult();
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getData().hashCode();
      hash = (37 * hash) + ERRORMSG_FIELD_NUMBER;
      hash = (53 * hash) + getErrorMsg().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.game.msg.gen.MsgRevHead.MsgBase parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.game.msg.gen.MsgRevHead.MsgBase prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * [START messages]
     * </pre>
     *
     * Protobuf type {@code AutoMsg.MsgBase}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:AutoMsg.MsgBase)
        com.game.msg.gen.MsgRevHead.MsgBaseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.msg.gen.MsgRevHead.internal_static_AutoMsg_MsgBase_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.msg.gen.MsgRevHead.internal_static_AutoMsg_MsgBase_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.msg.gen.MsgRevHead.MsgBase.class, com.game.msg.gen.MsgRevHead.MsgBase.Builder.class);
      }

      // Construct using com.game.msg.gen.MsgRevHead.MsgBase.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        msgID_ = 0;

        result_ = 0;

        data_ = com.google.protobuf.ByteString.EMPTY;

        errorMsg_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.msg.gen.MsgRevHead.internal_static_AutoMsg_MsgBase_descriptor;
      }

      public com.game.msg.gen.MsgRevHead.MsgBase getDefaultInstanceForType() {
        return com.game.msg.gen.MsgRevHead.MsgBase.getDefaultInstance();
      }

      public com.game.msg.gen.MsgRevHead.MsgBase build() {
        com.game.msg.gen.MsgRevHead.MsgBase result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.msg.gen.MsgRevHead.MsgBase buildPartial() {
        com.game.msg.gen.MsgRevHead.MsgBase result = new com.game.msg.gen.MsgRevHead.MsgBase(this);
        result.msgID_ = msgID_;
        result.result_ = result_;
        result.data_ = data_;
        result.errorMsg_ = errorMsg_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.msg.gen.MsgRevHead.MsgBase) {
          return mergeFrom((com.game.msg.gen.MsgRevHead.MsgBase)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.msg.gen.MsgRevHead.MsgBase other) {
        if (other == com.game.msg.gen.MsgRevHead.MsgBase.getDefaultInstance()) return this;
        if (other.getMsgID() != 0) {
          setMsgID(other.getMsgID());
        }
        if (other.getResult() != 0) {
          setResult(other.getResult());
        }
        if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
          setData(other.getData());
        }
        if (!other.getErrorMsg().isEmpty()) {
          errorMsg_ = other.errorMsg_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.msg.gen.MsgRevHead.MsgBase parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.msg.gen.MsgRevHead.MsgBase) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int msgID_ ;
      /**
       * <code>int32 MsgID = 1;</code>
       */
      public int getMsgID() {
        return msgID_;
      }
      /**
       * <code>int32 MsgID = 1;</code>
       */
      public Builder setMsgID(int value) {
        
        msgID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 MsgID = 1;</code>
       */
      public Builder clearMsgID() {
        
        msgID_ = 0;
        onChanged();
        return this;
      }

      private int result_ ;
      /**
       * <code>int32 Result = 2;</code>
       */
      public int getResult() {
        return result_;
      }
      /**
       * <code>int32 Result = 2;</code>
       */
      public Builder setResult(int value) {
        
        result_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 Result = 2;</code>
       */
      public Builder clearResult() {
        
        result_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes Data = 3;</code>
       */
      public com.google.protobuf.ByteString getData() {
        return data_;
      }
      /**
       * <code>bytes Data = 3;</code>
       */
      public Builder setData(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        data_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bytes Data = 3;</code>
       */
      public Builder clearData() {
        
        data_ = getDefaultInstance().getData();
        onChanged();
        return this;
      }

      private java.lang.Object errorMsg_ = "";
      /**
       * <code>string ErrorMsg = 4;</code>
       */
      public java.lang.String getErrorMsg() {
        java.lang.Object ref = errorMsg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          errorMsg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string ErrorMsg = 4;</code>
       */
      public com.google.protobuf.ByteString
          getErrorMsgBytes() {
        java.lang.Object ref = errorMsg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          errorMsg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string ErrorMsg = 4;</code>
       */
      public Builder setErrorMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        errorMsg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string ErrorMsg = 4;</code>
       */
      public Builder clearErrorMsg() {
        
        errorMsg_ = getDefaultInstance().getErrorMsg();
        onChanged();
        return this;
      }
      /**
       * <code>string ErrorMsg = 4;</code>
       */
      public Builder setErrorMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        errorMsg_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:AutoMsg.MsgBase)
    }

    // @@protoc_insertion_point(class_scope:AutoMsg.MsgBase)
    private static final com.game.msg.gen.MsgRevHead.MsgBase DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.game.msg.gen.MsgRevHead.MsgBase();
    }

    public static com.game.msg.gen.MsgRevHead.MsgBase getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MsgBase>
        PARSER = new com.google.protobuf.AbstractParser<MsgBase>() {
      public MsgBase parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MsgBase(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MsgBase> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MsgBase> getParserForType() {
      return PARSER;
    }

    public com.game.msg.gen.MsgRevHead.MsgBase getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AutoMsg_MsgBase_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AutoMsg_MsgBase_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020msgRevHead.proto\022\007AutoMsg\"H\n\007MsgBase\022\r" +
      "\n\005MsgID\030\001 \001(\005\022\016\n\006Result\030\002 \001(\005\022\014\n\004Data\030\003 " +
      "\001(\014\022\020\n\010ErrorMsg\030\004 \001(\tB\022\n\020com.game.msg.ge" +
      "nb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_AutoMsg_MsgBase_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AutoMsg_MsgBase_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AutoMsg_MsgBase_descriptor,
        new java.lang.String[] { "MsgID", "Result", "Data", "ErrorMsg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
