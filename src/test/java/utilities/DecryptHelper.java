package utilities;


import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Base64;

public class DecryptHelper {

    // Email
    // Encryption : base64(decimal(little endian(hex(text))))
    // Decryption : hex(little endian(decimal(base64(text))))
    public static String decryptUsingBase64(String text) {
        return new String(new StringBuilder(new String(DatatypeConverter.parseHexBinary(new String(ByteBuffer.wrap(new BigInteger(new String(Base64.getDecoder().decode(text.getBytes()))).toString(16).getBytes()).array())))).reverse());
    }

    // Password
    // Encryption : base58(decimal(little endian(hex(text))))
    // Decryption : hex(little endian(decimal(base58(text))))
    public static String decryptUsingBase58(String text) {
        return new String(new StringBuilder(new String(DatatypeConverter.parseHexBinary(new String(ByteBuffer.wrap(new BigInteger(new String(Base58.decode(text))).toString(16).getBytes()).array())))).reverse());
    }
}
