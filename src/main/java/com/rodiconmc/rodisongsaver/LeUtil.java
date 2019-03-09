package com.rodiconmc.rodisongsaver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LeUtil {
    public static void writeShortLE(DataOutputStream out, short value) throws IOException {
        out.writeByte(value & 0xFF);
        out.writeByte((value >>> 8) & 0xFF);
    }

    public static void writeIntLE(DataOutputStream out, int value) throws IOException {
        out.writeByte(value & 0xFF);
        out.writeByte((value >> 8) & 0xFF);
        out.writeByte((value >> 16) & 0xFF);
        out.writeByte((value >> 24) & 0xFF);
    }

    public static void writeString(DataOutputStream out, String string) throws IOException {
        StringBuilder binary = new StringBuilder(Integer.toBinaryString(string.length()));
        LeUtil.writeIntLE(out, string.length());
        for (char c : string.toCharArray()) {
            out.write((byte) c);
            binary.append(Integer.toBinaryString(c));
        }
    }

    public static short readShortLE(DataInputStream in) throws IOException {
        return (short) (in.readByte() | (in.readByte() << 8));
    }

    public static int readIntLE(DataInputStream in) throws IOException {
        return (in.readByte() | (in.readByte() << 8) | (in.readByte() << 16) | (in.readByte() << 24));
    }

    public static String readString(DataInputStream in) throws IOException {
        int length = LeUtil.readIntLE(in);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < length; i++) {
            string.append((char) in.read());
        }
        return string.toString();
    }


}
