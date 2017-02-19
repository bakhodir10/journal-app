package uz.com.platform.config;

public interface CryptoService {
    String encode(String text);

    String decode(String text);
}
