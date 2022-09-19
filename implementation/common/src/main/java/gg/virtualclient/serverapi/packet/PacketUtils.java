package gg.virtualclient.serverapi.packet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PacketUtils {

    @SuppressWarnings("deprecation")
    //Older Spigot versions have a really old gson lib in some versions, so we have to use this :/
    private static final JsonParser PARSER = new JsonParser();

    public static ByteBuf createPayloadBuffer(String identifier, JsonObject payload) {
        ByteBuf buffer = Unpooled.buffer();

        JsonObject send = new JsonObject();
        send.addProperty("identifier", identifier);
        send.add("payload", payload);

        writeString(send.toString(), buffer);
        return buffer;
    }

    public static byte[] createPayloadBytes(String identifier, JsonObject payload) {
        ByteBuffer buffer = ByteBuffer.allocate(255);

        JsonObject send = new JsonObject();
        send.addProperty("identifier", identifier);
        send.add("payload", payload);

        writeString(send.toString(), buffer);

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

    @SuppressWarnings("deprecation")
    public static ClientPacket readPacket(ByteBuf buf) {
        //Older Spigot versions have a really old gson lib in some versions, so we have to use this :/
        JsonObject object = PARSER.parse(readString(buf)).getAsJsonObject();

        return new ClientPacket(object.get("identifier").getAsString(), object.get("payload").getAsJsonObject());
    }

    @SuppressWarnings("deprecation")
    public static ClientPacket readPacket(ByteBuffer buf) {
        //Older Spigot versions have a really old gson lib in some versions, so we have to use this :/
        JsonObject object = PARSER.parse(readString(buf)).getAsJsonObject();

        return new ClientPacket(object.get("identifier").getAsString(), object.get("payload").getAsJsonObject());
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

    public static void writeString(String s, ByteBuffer buf) {
        if (s.length() > 32767) {
            throw new RuntimeException(String.format("Cannot send string longer than Short.MAX_VALUE (got %s characters)", s.length()));
        } else {
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            writeVarInt(b.length, buf);
            buf.put(b);
        }
    }


    public static String readString(ByteBuf buf) {
        return readString(buf, 32767);
    }

    public static String readString(ByteBuffer buf) {
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

    public static String readString(ByteBuffer buf, int maxLen) {
        int len = readVarInt(buf);
        if (len > maxLen * 4) {
            throw new RuntimeException(String.format("Cannot receive string longer than %d (got %d bytes)", maxLen * 4, len));
        } else {
            byte[] b = new byte[len];
            buf.get(b);
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

    public static int readVarInt(ByteBuffer input) {
        return readVarInt(input, 5);
    }

    public static int readVarInt(ByteBuffer input, int maxBytes) {
        int out = 0;
        int bytes = 0;

        byte in;
        do {
            in = input.get();
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

    public static void writeVarInt(int value, ByteBuffer output) {
        do {
            int part = value & 127;
            value >>>= 7;
            if (value != 0) {
                part |= 128;
            }

            output.putInt(part);
        } while(value != 0);
    }
}
