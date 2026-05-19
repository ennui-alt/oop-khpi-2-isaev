#include "ua_khpi_oop_lab16_service_NativeAverageCalculator.h"

JNIEXPORT jdouble JNICALL Java_ua_khpi_oop_lab16_service_NativeAverageCalculator_average
        (JNIEnv *env, jobject object, jintArray values) {
    if (values == nullptr) {
        jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(exceptionClass, "Array cannot be null");
        return 0.0;
    }

    jsize length = env->GetArrayLength(values);

    if (length == 0) {
        jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(exceptionClass, "Array cannot be empty");
        return 0.0;
    }

    jint *elements = env->GetIntArrayElements(values, nullptr);

    long long sum = 0;

    for (jsize i = 0; i < length; i++) {
        sum += elements[i];
    }

    env->ReleaseIntArrayElements(values, elements, JNI_ABORT);

    return static_cast<jdouble>(sum) / length;
}