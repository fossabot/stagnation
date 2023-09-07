// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Management.proto

package proto.management;

/**
 * Protobuf type {@code management.ClanJoinNotification}
 */
public final class ClanJoinNotification extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:management.ClanJoinNotification)
    ClanJoinNotificationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ClanJoinNotification.newBuilder() to construct.
  private ClanJoinNotification(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ClanJoinNotification() {
    username_ = "";
    clanName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ClanJoinNotification();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ClanJoinNotification(
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
          case 10: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            username_ = bs;
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            clanName_ = bs;
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            world_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
    return proto.management.ManagementProtos.internal_static_management_ClanJoinNotification_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return proto.management.ManagementProtos.internal_static_management_ClanJoinNotification_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            proto.management.ClanJoinNotification.class, proto.management.ClanJoinNotification.Builder.class);
  }

  private int bitField0_;
  public static final int USERNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object username_;
  /**
   * <code>required string username = 1;</code>
   * @return Whether the username field is set.
   */
  @java.lang.Override
  public boolean hasUsername() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required string username = 1;</code>
   * @return The username.
   */
  @java.lang.Override
  public java.lang.String getUsername() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        username_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string username = 1;</code>
   * @return The bytes for username.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUsernameBytes() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      username_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CLANNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object clanName_;
  /**
   * <code>required string clanName = 2;</code>
   * @return Whether the clanName field is set.
   */
  @java.lang.Override
  public boolean hasClanName() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>required string clanName = 2;</code>
   * @return The clanName.
   */
  @java.lang.Override
  public java.lang.String getClanName() {
    java.lang.Object ref = clanName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        clanName_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string clanName = 2;</code>
   * @return The bytes for clanName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getClanNameBytes() {
    java.lang.Object ref = clanName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      clanName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int WORLD_FIELD_NUMBER = 3;
  private int world_;
  /**
   * <code>required int32 world = 3;</code>
   * @return Whether the world field is set.
   */
  @java.lang.Override
  public boolean hasWorld() {
    return ((bitField0_ & 0x00000004) != 0);
  }
  /**
   * <code>required int32 world = 3;</code>
   * @return The world.
   */
  @java.lang.Override
  public int getWorld() {
    return world_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasUsername()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasClanName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasWorld()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, username_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, clanName_);
    }
    if (((bitField0_ & 0x00000004) != 0)) {
      output.writeInt32(3, world_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, username_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, clanName_);
    }
    if (((bitField0_ & 0x00000004) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, world_);
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
    if (!(obj instanceof proto.management.ClanJoinNotification)) {
      return super.equals(obj);
    }
    proto.management.ClanJoinNotification other = (proto.management.ClanJoinNotification) obj;

    if (hasUsername() != other.hasUsername()) return false;
    if (hasUsername()) {
      if (!getUsername()
          .equals(other.getUsername())) return false;
    }
    if (hasClanName() != other.hasClanName()) return false;
    if (hasClanName()) {
      if (!getClanName()
          .equals(other.getClanName())) return false;
    }
    if (hasWorld() != other.hasWorld()) return false;
    if (hasWorld()) {
      if (getWorld()
          != other.getWorld()) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasUsername()) {
      hash = (37 * hash) + USERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getUsername().hashCode();
    }
    if (hasClanName()) {
      hash = (37 * hash) + CLANNAME_FIELD_NUMBER;
      hash = (53 * hash) + getClanName().hashCode();
    }
    if (hasWorld()) {
      hash = (37 * hash) + WORLD_FIELD_NUMBER;
      hash = (53 * hash) + getWorld();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static proto.management.ClanJoinNotification parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.management.ClanJoinNotification parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.management.ClanJoinNotification parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.management.ClanJoinNotification parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static proto.management.ClanJoinNotification parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.management.ClanJoinNotification parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(proto.management.ClanJoinNotification prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code management.ClanJoinNotification}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:management.ClanJoinNotification)
      proto.management.ClanJoinNotificationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.management.ManagementProtos.internal_static_management_ClanJoinNotification_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.management.ManagementProtos.internal_static_management_ClanJoinNotification_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.management.ClanJoinNotification.class, proto.management.ClanJoinNotification.Builder.class);
    }

    // Construct using proto.management.ClanJoinNotification.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      username_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      clanName_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      world_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return proto.management.ManagementProtos.internal_static_management_ClanJoinNotification_descriptor;
    }

    @java.lang.Override
    public proto.management.ClanJoinNotification getDefaultInstanceForType() {
      return proto.management.ClanJoinNotification.getDefaultInstance();
    }

    @java.lang.Override
    public proto.management.ClanJoinNotification build() {
      proto.management.ClanJoinNotification result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public proto.management.ClanJoinNotification buildPartial() {
      proto.management.ClanJoinNotification result = new proto.management.ClanJoinNotification(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.username_ = username_;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        to_bitField0_ |= 0x00000002;
      }
      result.clanName_ = clanName_;
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.world_ = world_;
        to_bitField0_ |= 0x00000004;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof proto.management.ClanJoinNotification) {
        return mergeFrom((proto.management.ClanJoinNotification)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(proto.management.ClanJoinNotification other) {
      if (other == proto.management.ClanJoinNotification.getDefaultInstance()) return this;
      if (other.hasUsername()) {
        bitField0_ |= 0x00000001;
        username_ = other.username_;
        onChanged();
      }
      if (other.hasClanName()) {
        bitField0_ |= 0x00000002;
        clanName_ = other.clanName_;
        onChanged();
      }
      if (other.hasWorld()) {
        setWorld(other.getWorld());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      if (!hasUsername()) {
        return false;
      }
      if (!hasClanName()) {
        return false;
      }
      if (!hasWorld()) {
        return false;
      }
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      proto.management.ClanJoinNotification parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (proto.management.ClanJoinNotification) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object username_ = "";
    /**
     * <code>required string username = 1;</code>
     * @return Whether the username field is set.
     */
    public boolean hasUsername() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required string username = 1;</code>
     * @return The username.
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          username_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string username = 1;</code>
     * @return The bytes for username.
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string username = 1;</code>
     * @param value The username to set.
     * @return This builder for chaining.
     */
    public Builder setUsername(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      username_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string username = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUsername() {
      bitField0_ = (bitField0_ & ~0x00000001);
      username_ = getDefaultInstance().getUsername();
      onChanged();
      return this;
    }
    /**
     * <code>required string username = 1;</code>
     * @param value The bytes for username to set.
     * @return This builder for chaining.
     */
    public Builder setUsernameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      username_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object clanName_ = "";
    /**
     * <code>required string clanName = 2;</code>
     * @return Whether the clanName field is set.
     */
    public boolean hasClanName() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required string clanName = 2;</code>
     * @return The clanName.
     */
    public java.lang.String getClanName() {
      java.lang.Object ref = clanName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          clanName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string clanName = 2;</code>
     * @return The bytes for clanName.
     */
    public com.google.protobuf.ByteString
        getClanNameBytes() {
      java.lang.Object ref = clanName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        clanName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string clanName = 2;</code>
     * @param value The clanName to set.
     * @return This builder for chaining.
     */
    public Builder setClanName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      clanName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string clanName = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearClanName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      clanName_ = getDefaultInstance().getClanName();
      onChanged();
      return this;
    }
    /**
     * <code>required string clanName = 2;</code>
     * @param value The bytes for clanName to set.
     * @return This builder for chaining.
     */
    public Builder setClanNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      clanName_ = value;
      onChanged();
      return this;
    }

    private int world_ ;
    /**
     * <code>required int32 world = 3;</code>
     * @return Whether the world field is set.
     */
    @java.lang.Override
    public boolean hasWorld() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>required int32 world = 3;</code>
     * @return The world.
     */
    @java.lang.Override
    public int getWorld() {
      return world_;
    }
    /**
     * <code>required int32 world = 3;</code>
     * @param value The world to set.
     * @return This builder for chaining.
     */
    public Builder setWorld(int value) {
      bitField0_ |= 0x00000004;
      world_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 world = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearWorld() {
      bitField0_ = (bitField0_ & ~0x00000004);
      world_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:management.ClanJoinNotification)
  }

  // @@protoc_insertion_point(class_scope:management.ClanJoinNotification)
  private static final proto.management.ClanJoinNotification DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new proto.management.ClanJoinNotification();
  }

  public static proto.management.ClanJoinNotification getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ClanJoinNotification>
      PARSER = new com.google.protobuf.AbstractParser<ClanJoinNotification>() {
    @java.lang.Override
    public ClanJoinNotification parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ClanJoinNotification(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ClanJoinNotification> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ClanJoinNotification> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public proto.management.ClanJoinNotification getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

