package aidl;
interface ImageCallback {
    String getImageUrl();
    void onImageLoaded(String url, String path);
    void onImageError(int errorCode);
}
