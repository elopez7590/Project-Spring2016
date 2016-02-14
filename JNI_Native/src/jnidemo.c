/*
 * jnidemo.c
 *
 *  Created on: Jan 1, 2016
 *      Author: Anthony Giorgio
 */

#include <stdio.h>
#include <jni.h>

/**
 * Print a message to the console
 */
JNIEXPORT jint JNICALL
Java_edu_marist_mscs710_JNIDemo_jnidemo(JNIEnv *env, jobject obj) {

	printf("In JNI code\n");

	return 0;
}

