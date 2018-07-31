#include<stdio.h>
#include<jni.h>
#include "JNITest.h"

JNIEXPORT void JNICALL Java_JNITest_greet

   (JNIEnv *env, jobject obj)
{
      printf("\n...Welcome to the world of JNI...\n");
      return;
}
