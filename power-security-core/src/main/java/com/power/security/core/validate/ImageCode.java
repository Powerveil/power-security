package com.power.security.core.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author Powerveil
 * @Date 2024/1/23 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode {
    public ImageCode(BufferedImage image, String code, int expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;


    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
