package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

    }
}


class loadImage {
    private final Map<URI, Reference<Image>> cache;

    public loadImage(){
        cache = new HashMap<URI, Reference<Image>>();
    }

    public Image fetch(URI path){
        Image image = null;

        Reference<Image> imageRef = getImageRefCache(path);

        if(imageRef != null){
            image = imageRef.get();
        } else {
            image = setUpImage(path);
            addImageCache(path, image);
        }

        return image;
    }

    private void addImageCache(URI path, Image image) {
        cache.put(path, new SoftReference<Image>(image));
    }

    private Image setUpImage(URI path) {
        Image image = null;
        try{
            image = ImageIO.read(path.toURL());
        } catch (IOException e) {

        }
        return image;
    }

    private Reference<Image> getImageRefCache(URI path) {
        return cache.get(path);
    }

}