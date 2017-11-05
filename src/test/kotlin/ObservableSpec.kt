import com.sun.javafx.scene.shape.ObservableFaceArrayImpl
import io.reactivex.*
import io.reactivex.disposables.Disposable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class ObservableSpec: Spek({
    it("creates Flowable") {
        val observable = Flowable.range(1, 10)

        val values = mutableListOf<Int>()
        observable.subscribe { values.add(it) }

        assertEquals(values.size, 10)
    }

    it("creates Observer with default callbacks") {
        val observable = Observable.fromArray(listOf(1, 2, 3, 4, 5))

        var values = listOf<Int>()
        val observer = object : Observer<List<Int>> {
            override fun onNext(t: List<Int>) {
                values = t
            }

            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) = e.printStackTrace()
            override fun onComplete() = println("completed")
        }
        observable.subscribe(observer)

        assertEquals(values.size, 5)
    }

    it("creates Observable") {
        val observable = Observable.create(
                ObservableOnSubscribe<Int> {
                    it.onNext(1)
                    it.onNext(5)
                    it.onNext(10)
                    it.onComplete()
                }
        )

        val values = mutableListOf<Int>()
        observable.subscribe{ values.add(it) }

        assertEquals(values.size, 3)
    }
})