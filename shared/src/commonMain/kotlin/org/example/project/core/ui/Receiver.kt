package org.example.project.core.ui

interface Receiver<S, I> {

    fun receive(state: S, intent: I): S
}