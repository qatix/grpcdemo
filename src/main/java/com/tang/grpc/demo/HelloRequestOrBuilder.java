// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hello.proto

package com.tang.grpc.demo;

public interface HelloRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helloworld.HelloRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string name = 1;</code>
   */
  String getName();
  /**
   * <code>optional string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional string sex = 2;</code>
   */
  String getSex();
  /**
   * <code>optional string sex = 2;</code>
   */
  com.google.protobuf.ByteString
      getSexBytes();

  /**
   * <code>optional bytes data = 3;</code>
   */
  com.google.protobuf.ByteString getData();
}
