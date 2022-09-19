package gg.virtualclient.serverapi.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class PacketUtils {


    public static ByteBuf createPayloadBuffer(ClientPacket packet) {
        ByteBuf buffer = Unpooled.buffer();
        writeString(packet.serialize().toString(), buffer);
        return buffer;
    }

    public static byte[] packetToBytes(ClientPacket packet) {
        ByteBuf byteBuf = createPayloadBuffer(packet);

        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        byteBuf.release();
        return bytes;
    }

    public static ClientPacket readPacket(ByteBuf buf) {
        return ClientPacket.fromString(readString(buf));
    }

    public static void writeString(String s, ByteBuf buf) {
        if (s.length() > 32767) {
            throw new RuntimeException(String.format("Cannot send string longer than Short.MAX_VALUE (got %s characters)", s.length()));
        } else {
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            writeVarInt(b.length, buf);
            buf.writeBytes(b);
        }
    }

    public static String readString(ByteBuf buf) {
        return readString(buf, 32767);
    }


    public static String readString(ByteBuf buf, int maxLen) {
        int len = readVarInt(buf);
        if (len > maxLen * 4) {
            throw new RuntimeException(String.format("Cannot receive string longer than %d (got %d bytes)", maxLen * 4, len));
        } else {
            byte[] b = new byte[len];
            buf.readBytes(b);
            String s = new String(b, StandardCharsets.UTF_8);
            if (s.length() > maxLen) {
                throw new RuntimeException(String.format("Cannot receive string longer than %d (got %d characters)", maxLen, s.length()));
            } else {
                return s;
            }
        }
    }


    public static int readVarInt(ByteBuf input) {
        return readVarInt(input, 5);
    }

    public static int readVarInt(ByteBuf input, int maxBytes) {
        int out = 0;
        int bytes = 0;

        byte in;
        do {
            in = input.readByte();
            out |= (in & 127) << bytes++ * 7;
            if (bytes > maxBytes) {
                throw new RuntimeException("VarInt too big");
            }
        } while((in & 128) == 128);

        return out;
    }


    public static void writeVarInt(int value, ByteBuf output) {
        do {
            int part = value & 127;
            value >>>= 7;
            if (value != 0) {
                part |= 128;
            }

            output.writeByte(part);
        } while(value != 0);
    }

}
