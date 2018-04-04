
#include <jni.h>
#include <stdio.h>
#include "AddJNI.h"

// Implementation of native method add() of TestJNI class

//JNIEXPORT void JNICALL Java_JniExample_justSayHello(JNIEnv *env, jobject obj)
JNIEXPORT jint JNICALL Java_AddJNI_add(JNIEnv *env, jobject thisObj,jint n1,jint n2)

{
	jint res;
	res=n1+n2;
	return res;
}
