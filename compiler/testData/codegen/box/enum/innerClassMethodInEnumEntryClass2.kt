// IGNORE_BACKEND: JS_IR
// LANGUAGE_VERSION: 1.2

enum class A {
    X {
        val x = "OK"

        inner class Inner {
            fun foo() = this@X.x
        }

        val z = Inner()

        override val test = z.foo()
    };

    abstract val test: String
}

fun box() = A.X.test