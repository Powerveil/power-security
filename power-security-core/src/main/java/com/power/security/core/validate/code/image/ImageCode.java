package com.power.security.core.validate.code.image;

import com.power.security.core.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @author Powerveil
 * @Date 2024/1/23 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode extends ValidateCode {
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    private BufferedImage image;

}
