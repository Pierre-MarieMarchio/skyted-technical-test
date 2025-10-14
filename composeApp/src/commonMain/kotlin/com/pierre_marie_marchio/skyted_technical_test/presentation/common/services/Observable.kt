package com.pierre_marie_marchio.skyted_technical_test.presentation.common.services

class Observable<T>(initialValue: T) {
    private val _observers = mutableListOf<(T) -> Unit>()
    private var _value = initialValue

    var value: T
        get() = _value
        set(newValue) {
            _value = newValue
            _observers.forEach { it(_value) }
        }

    fun observe(observer: (T) -> Unit) {
        _observers += observer
        observer(_value)
    }
}