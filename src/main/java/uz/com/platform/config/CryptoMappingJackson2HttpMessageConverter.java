package uz.com.platform.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

@Component
public class CryptoMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    @Setter(onMethod = @__({@Autowired}))
    private CryptoService cryptoService;

    @Autowired
    public CryptoMappingJackson2HttpMessageConverter(@Qualifier("hibernateAwareObjectMapper") ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Object o = super.read(CryptoWrapper.class, contextClass, inputMessage);
        if (o instanceof CryptoWrapper) {
            String data = ((CryptoWrapper) o).getData();
            String decrypted = cryptoService.decode(data);
            try {
                return this.objectMapper.readValue(decrypted, Class.forName(type.getTypeName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String data = this.objectMapper.writeValueAsString(object);
        String encrypted = cryptoService.encode(data);
        super.writeInternal(new CryptoWrapper(encrypted), CryptoWrapper.class, outputMessage);
    }
}
