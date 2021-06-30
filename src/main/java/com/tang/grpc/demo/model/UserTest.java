package com.tang.grpc.demo.model;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.tang.grpc.demo.user.UserOuterClass;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class UserTest {

    public static void main(String[] args) throws IOException {
        UserOuterClass.User.Builder userBuilder =  UserOuterClass.User.newBuilder();
        userBuilder.setName("zhang")
                .setAge(22)
                .setGender(UserOuterClass.User.Gender.FEMALE);
        UserOuterClass.User user = userBuilder.build();
        System.out.println(user.toString());
        //write data to file
        FileUtils.writeByteArrayToFile(new File("tmp/user.bin"),user.toByteArray(),false);

        //read data from file
        byte[] bdata = FileUtils.readFileToByteArray(new File("tmp/user.bin"));
        System.out.println("pb len:"+bdata.length);
        UserOuterClass.User rUser = UserOuterClass.User.parseFrom(bdata);
        System.out.println(rUser);

        //to json
        String userJson = JsonFormat.printToString(user);
        System.out.println(userJson);
        System.out.println("json len:"+userJson.length());

        //parse from json
        UserOuterClass.User.Builder jUserBuilder =  UserOuterClass.User.newBuilder();
        try {
            JsonFormat.merge(userJson,jUserBuilder);
            System.out.println(jUserBuilder.build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
