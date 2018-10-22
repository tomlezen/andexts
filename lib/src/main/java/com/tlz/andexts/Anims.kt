@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.tlz.andexts

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.transition.Transition

/**
 * Created by Tomlezen.
 * Data: 2018/10/22.
 * Time: 9:34.
 */

inline fun Animator.doOnStart(crossinline action: (animation: Animator?) -> Unit): Animator
.AnimatorListener {
    val listener = object : AnimatorListenerAdapter() {

        override fun onAnimationStart(animation: Animator?) {
            action.invoke(animation)
        }
    }
    this.addListener(listener)
    return listener
}

inline fun Animator.doOnEnd(crossinline action: (animation: Animator?) -> Unit): Animator.AnimatorListener {
    val listener = object : AnimatorListenerAdapter() {

        override fun onAnimationEnd(animation: Animator?) {
            action.invoke(animation)
        }
    }
    this.addListener(listener)
    return listener
}

inline fun Animator.doOnCancel(crossinline action: (animation: Animator?) -> Unit): Animator.AnimatorListener {
    val listener = object : AnimatorListenerAdapter() {

        override fun onAnimationCancel(animation: Animator?) {
            action.invoke(animation)
        }
    }
    this.addListener(listener)
    return listener
}

inline fun Animator.doOnRepeat(crossinline action: (animation: Animator?) -> Unit): Animator.AnimatorListener {
    val listener = object : AnimatorListenerAdapter() {

        override fun onAnimationRepeat(animation: Animator?) {
            action.invoke(animation)
        }
    }
    this.addListener(listener)
    return listener
}

@TargetApi(Build.VERSION_CODES.KITKAT)
inline fun Transition.doOnStart(crossinline action: (transition: Transition?) -> Unit): Transition.TransitionListener {
    val listener = object : TransitionListenerAdapter() {
        override fun onTransitionStart(transition: Transition?) {
            action.invoke(transition)
        }
    }
    this.addListener(listener)
    return listener
}

@TargetApi(Build.VERSION_CODES.KITKAT)
inline fun Transition.doOnEnd(crossinline action: (transition: Transition?) -> Unit): Transition.TransitionListener {
    val listener = object : TransitionListenerAdapter() {
        override fun onTransitionEnd(transition: Transition?) {
            action.invoke(transition)
        }
    }
    this.addListener(listener)
    return listener
}

@TargetApi(Build.VERSION_CODES.KITKAT)
inline fun Transition.doOnCancel(crossinline action: (transition: Transition?) -> Unit): Transition
.TransitionListener {
    val listener = object : TransitionListenerAdapter() {
        override fun onTransitionCancel(transition: Transition?) {
            action.invoke(transition)
        }
    }
    this.addListener(listener)
    return listener
}

@TargetApi(Build.VERSION_CODES.KITKAT)
abstract class TransitionListenerAdapter : Transition.TransitionListener {
    override fun onTransitionEnd(transition: Transition?) {

    }

    override fun onTransitionResume(transition: Transition?) {

    }

    override fun onTransitionPause(transition: Transition?) {

    }

    override fun onTransitionCancel(transition: Transition?) {

    }

    override fun onTransitionStart(transition: Transition?) {

    }
}
