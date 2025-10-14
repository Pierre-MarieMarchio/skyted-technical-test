package com.pierre_marie_marchio.skyted_technical_test.presentation.common.services

class Observable<T>(initialValue: T) {
    private val _observers = mutableListOf<(T) -> Unit>()
    private var _value = initialValue

    var value: T
        get() = _value
        set(newValue) {
            _value = newValue
            val observersCopy = _observers.toList()
            observersCopy.forEach { observer ->
                try {
                    observer(_value)
                } catch (e: Exception) {
                    println("Observable: Error notifying observer: ${e.message}")
                }
            }
        }

    fun observe(observer: (T) -> Unit) {
        _observers += observer
        observer(_value)
    }

    fun addObserver(observer: (T) -> Unit) {
        _observers += observer
        observer(_value)
    }

    fun removeAllObservers() {
        _observers.clear()
    }
}