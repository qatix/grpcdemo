// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hello.proto

package com.tang.grpc.demo;

public final class TangHelloProto {
  private TangHelloProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_HelloRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_HelloRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helloworld_HelloReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helloworld_HelloReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\013hello.proto\022\nhelloworld\"7\n\014HelloReques" +
      "t\022\014\n\004name\030\001 \001(\t\022\013\n\003sex\030\002 \001(\t\022\014\n\004data\030\003 \001" +
      "(\014\"+\n\nHelloReply\022\017\n\007message\030\001 \001(\t\022\014\n\004dat" +
      "a\030\003 \001(\0142\210\001\n\007Greeter\022>\n\010SayHello\022\030.hellow" +
      "orld.HelloRequest\032\026.helloworld.HelloRepl" +
      "y\"\000\022=\n\007SayWhat\022\030.helloworld.HelloRequest" +
      "\032\026.helloworld.HelloReply\"\000B+\n\022com.tang.g" +
      "rpc.demoB\016TangHelloProtoP\001\242\002\002TGb\006proto3"
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
    internal_static_helloworld_HelloRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helloworld_HelloRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_HelloRequest_descriptor,
        new String[] { "Name", "Sex", "Data", });
    internal_static_helloworld_HelloReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helloworld_HelloReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helloworld_HelloReply_descriptor,
        new String[] { "Message", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
