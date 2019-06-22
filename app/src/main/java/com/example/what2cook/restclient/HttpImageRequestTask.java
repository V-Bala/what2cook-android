package com.example.what2cook.restclient;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpImageRequestTask extends AsyncTask<Void, Void, List<Drawable>> {

    List<String> imageUrls;

    /**
     * Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    public HttpImageRequestTask(List<String> imageUrls) {
        super();
        this.imageUrls = imageUrls;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param voids The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<Drawable> doInBackground(Void... voids) {
        return LoadImagesIntoDrawableList(this.imageUrls);
    }

    public static List<Drawable> LoadImagesIntoDrawableList(List<String> imageUrls) {
        List<Drawable> drawableList = new ArrayList<>();
        for (String url: imageUrls) {
            Drawable drawable = LoadImageFromWebOperations(url, url);
            drawableList.add(drawable);
        }

        return drawableList;
    }

    public static Drawable LoadImageFromWebOperations(String url, String srcName) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, srcName);
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
