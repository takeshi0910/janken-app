package com.example.demo.message;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/** 
 * messages.propertiesのメッセージ取得用クラス
 */
@Component
public class Messages {
    
    private final MessageSource messageSource;
    
    public Messages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

	/** messages.propertiesのキーに対応するメッセージを返す */
	public String get(MessageKey key, Object... params) {
		return messageSource.getMessage(key.key(), params, Locale.JAPAN);
	}
}
