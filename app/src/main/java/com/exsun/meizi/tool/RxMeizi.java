package com.exsun.meizi.tool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.yuyh.library.utils.DimenUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by xiaokun on 2017/8/7.
 */

public class RxMeizi
{
    public static Observable<Uri> saveImageAndGetPathObservable(final Context context, final String url, final String title)
    {
        return Observable.create(new ObservableOnSubscribe<Bitmap>()
        {

            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception
            {
                Bitmap bitmap = Glide.with(context).load(url).asBitmap().into(DimenUtils.getScreenWidth()
                        , DimenUtils.getScreenHeight()).get();
                if (bitmap == null)
                {
                    e.onError(new Exception("无法下载到图片"));
                }
                e.onNext(bitmap);
                e.onComplete();
            }
        }).flatMap(new Function<Bitmap, ObservableSource<Uri>>()
        {

            @Override
            public ObservableSource<Uri> apply(Bitmap bitmap) throws Exception
            {
                File file = new File(Environment.getExternalStorageDirectory(), "xiaocai_meizi");
                if (!file.exists())
                {
                    file.mkdir();
                }
                String fileName = title.replace("-", "/") + ".jpg";//除去title中的/
                File imgFile = new File(file, fileName);
                try
                {
                    FileOutputStream outputStream = new FileOutputStream(imgFile);
                    assert bitmap != null;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                Uri uri = Uri.fromFile(imgFile);
                // 通知图库更新
                Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                context.sendBroadcast(scannerIntent);
                return Observable.just(uri);
            }
        });
    }
}
