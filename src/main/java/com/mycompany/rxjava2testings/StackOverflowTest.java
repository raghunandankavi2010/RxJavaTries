/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rxjava2testings;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

/**
 *
 * @author Raghunandan
 */
public class StackOverflowTest {
    
    public StackOverflowTest()
    {
        
    }
    public static void main(String[] args)
    {
        new StackOverflowTest();
    }
  
    
    public static Observable<Path> listFolder(Path dir, String glob) {
    return Observable.using(new Callable<DirectoryStream<Path>>() {

        public DirectoryStream<Path> call() throws IOException {
            return Files.newDirectoryStream(dir, glob);
        }
    }, new Function<DirectoryStream<Path>, ObservableSource<? extends Path>>() {

        @Override
        public Observable<Path> apply(DirectoryStream<Path> stream) {
            return Observable.fromIterable(stream);
        }
    }, new Consumer<DirectoryStream<Path>>() {

        public void accept(DirectoryStream<Path> stream) throws IOException {
            stream.close();
        }
    });
}
    
}
