#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL
Java_org_kryptokrona_sdk_TestApp_print(JNIEnv *env, jobject obj)
{
printf("Hello From C World!\n");
return;
}