package aidl;
import ImageCallback;
interface ImageLoadService {
    void registerCallBack(ImageCallback callback);
}
