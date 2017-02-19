package uz.com.platform.app.crypto;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CryptoController {
    @RequestMapping(value = "/crypto/object", method = RequestMethod.POST)
    public CryptoValue postTestObject(@RequestBody CryptoValue cryptoValue) {
        cryptoValue.setValue(cryptoValue.getValue() + 1);
        return cryptoValue;
    }

    @RequestMapping(value = "/crypto/array", method = RequestMethod.POST)
    public List<CryptoValue> postTestArray(@RequestBody List<CryptoValue> cryptoValues) {
        return cryptoValues;
    }
}
