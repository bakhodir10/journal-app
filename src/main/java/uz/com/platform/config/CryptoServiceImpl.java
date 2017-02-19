package uz.com.platform.config;

import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {
    private static final String ENCODING = "UTF-8";

    @Override
    public String encode(String text) {
        /*try {
            return DatatypeConverter.printBase64Binary(text.getBytes(ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return text;
    }

    @Override
    public String decode(String text) {
        return text;
/*        try {
            return new String(DatatypeConverter.parseBase64Binary(text), ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;*/
    }
}
