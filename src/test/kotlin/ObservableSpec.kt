import io.reactivex.Flowable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class ObservableSpec: Spek({
    it("creates Observable") {
        val observable = Flowable.range(1, 10)

        val values = mutableListOf<Int>()
        observable.subscribe { values.add(it) }

        assertEquals(values.size, 10)
    }
})