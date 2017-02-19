package uz.com.platform.config;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class SingleLineFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        final String result = P6Util.singleLine(sql);
        return result != null && !result.isEmpty() ? result : P6Util.singleLine(prepared);
    }
}
