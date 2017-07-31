package com.yuyh.library.utils.data.safe;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 *
 * @author yuyh.
 * @date 16/4/9.
 */
public class MD5
{
    private static final String TAG = MD5.class.getSimpleName();
    private static final int STREAM_BUFFER_LENGTH = 1024;

    public static MessageDigest getDigest(final String algorithm) throws NoSuchAlgorithmException
    {
        return MessageDigest.getInstance(algorithm);
    }

    public static byte[] md5(String txt)
    {
        return md5(txt.getBytes());
    }

    public static byte[] md5(byte[] bytes)
    {
        try
        {
            MessageDigest digest = getDigest("MD5");
            digest.update(bytes);
            return digest.digest();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] md5(InputStream is) throws NoSuchAlgorithmException, IOException
    {
        return updateDigest(getDigest("MD5"), is).digest();
    }

    public static MessageDigest updateDigest(final MessageDigest digest, final InputStream data) throws IOException
    {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1)
        {
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }
        return digest;
    }

    public static String md5ToString(String string)
    {
        if (TextUtils.isEmpty(string))
        {
            return "";
        }
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[0];
            try
            {
                bytes = md5.digest(string.getBytes("UTF-8"));
                String result = "";
                for (byte b : bytes)
                {
                    String temp = Integer.toHexString(b & 0xff);
                    if (temp.length() == 1)
                    {
                        temp = "0" + temp;
                    }
                    result += temp;
                }
                return result;
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static String md5String(String string)
    {
        if (TextUtils.isEmpty(string))
        {
            return "";
        }
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
            char[] charArray = string.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++)
                byteArray[i] = (byte) charArray[i];
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();

            for (int i = 0; i < md5Bytes.length; i++)
            {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public final static String MD5(String s)
    {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try
        {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
