package com.jiwei.baselibrary.inject

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by 18099 on 2018/12/19.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope